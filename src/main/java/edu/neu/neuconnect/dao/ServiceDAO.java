package edu.neu.neuconnect.dao;

import edu.neu.neuconnect.controller.rest.options.FilterOption;
import edu.neu.neuconnect.controller.rest.options.PaginationOption;
import edu.neu.neuconnect.controller.rest.options.SortOption;
import edu.neu.neuconnect.model.*;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceDAO extends DAO {
    public ServiceDAO() {
        super(ServiceRequest.class, "ServiceRequest");
    }

    public long newIndividualRequests(long authorId, String description, String title, int karma, long serverId,
            ServiceType type, Certificate certificate) throws Exception {
        try {
            begin();
            IndividualRequest serviceRequest = new IndividualRequest();
            serviceRequest.setTitle(title);
            serviceRequest.setDescription(description);
            User author = (getSession().get(User.class, authorId));
            User server = getSession().get(User.class, serverId);
            serviceRequest.setAuthor(author);
            serviceRequest.setServer(server);
            serviceRequest.setKarma(karma);
            serviceRequest.setType(type);
            serviceRequest.setStatus(ServiceRequestStatus.ASSIGNED);
            getSession().save(certificate);
            serviceRequest.setCertificateAttached(certificate);
            getSession().save(serviceRequest);
            long requestId = serviceRequest.getId();
            commit();
            close();
            return requestId;
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Exception while creating post: " + e.getMessage());
        }
    }

    public int getPageCount(PaginationOption option, long userId) throws Exception {
        try {
            begin();
            Query<ServiceRequest> query = getPaginatedQuery(option, userId);
            int pageCount = query.getResultList().size();
            commit();
            close();
            return (int) Math.ceil((double) pageCount / option.getPageSize());
        } catch (HibernateException e) {
            rollback();
            throw new RuntimeException(e);
        }
    }

    public List<ServiceRequest> pagination(PaginationOption options, long userId) throws Exception {
        int firstResult = (options.getPageNumber() - 1) * options.getPageSize();
        try {
            begin();
            Query<ServiceRequest> query = getPaginatedQuery(options, userId);
            query.setFirstResult(firstResult);
            query.setMaxResults(options.getPageSize());
            User loggedInUser = getSession().get(User.class, userId);
            List result = setBtnStatus(query.getResultList(), loggedInUser);
            commit();
            close();
            return result;
        } catch (HibernateException e) {
            rollback();
            throw new RuntimeException(e);
        }
    }

    private List setBtnStatus(List<ServiceRequest> resultList, User loggedInUser) {

        for (ServiceRequest service : resultList) {

            if (service instanceof IndividualRequest) {

                IndividualRequest isr = (IndividualRequest) service;

                if (isr.getStatus().equals(ServiceRequestStatus.UNASSIGNED))
                    isr.setShowAssignedBtn(true);

                else if (isr.getStatus().equals(ServiceRequestStatus.INPROGRESS)
                        && (loggedInUser.equals(isr.getAuthor())))
                    service.setShowCompleteBtn(true);

                else if (isr.getServer().equals(loggedInUser)
                        && isr.getStatus().equals(ServiceRequestStatus.ASSIGNED)) {
                    service.setShowInProgressBtn(true);
                }

            } else if (service instanceof MultipleServiceRequest) {

                MultipleServiceRequest msr = (MultipleServiceRequest) service;

                if (!(msr.getParticipants().contains(loggedInUser))
                        && (msr.getStatus().equals(ServiceRequestStatus.UNASSIGNED)
                                || msr.getStatus().equals(ServiceRequestStatus.PENDING)))
                    msr.setShowEnrollBtn(true);

                else if (msr.getStatus().equals(ServiceRequestStatus.PENDING) && msr.getAuthor().equals(loggedInUser))
                    msr.setShowInProgressBtn(true);

                else if (msr.getStatus().equals(ServiceRequestStatus.INPROGRESS)
                        && msr.getAuthor().equals(loggedInUser))
                    msr.setShowCompleteBtn(true);
            }

        }

        return resultList;

    }

    private Query<ServiceRequest> getPaginatedQuery(PaginationOption options, long userId) throws Exception {

        FilterOption filterOption = options.getFilterOption();
        SortOption sortOption = options.getSortOption();
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(ServiceRequest.class);
        Root<ServiceRequest> root = criteriaQuery.from(ServiceRequest.class);

        if (filterOption == null || filterOption.isEmpty())
            criteriaQuery.select(root);
        else
            criteriaQuery.select(root).where(getPredicateByFilterOption(criteriaBuilder, filterOption, root, userId));
        criteriaQuery.orderBy(getOrderBySortOption(criteriaBuilder, sortOption, root, userId));

        Query<ServiceRequest> query = getSession().createQuery(criteriaQuery);

        return query;
    }

    private List<Order> getOrderBySortOption(CriteriaBuilder criteriaBuilder, SortOption sortOption,
            Root<ServiceRequest> root, long userId) {
        List<Order> orders = new ArrayList<>();
        if (sortOption == null || sortOption.getKey() == null || sortOption.getType() == null) {
            orders.add(criteriaBuilder.desc(root.get("createdAt")));
            return orders;
        }
        if (sortOption.getType().equals("ASC")) {
            orders.add(criteriaBuilder.asc(root.get(sortOption.getKey())));
        } else
            orders.add(criteriaBuilder.desc(root.get(sortOption.getKey())));
        return orders;
    }

    private Predicate getPredicateByFilterOption(CriteriaBuilder criteriaBuilder, FilterOption filterOption,
            Root<ServiceRequest> root, long userId) {
        Join<ServiceRequest, User> authorJoin = root.join("author");

        Predicate fnameMatch = criteriaBuilder.like(authorJoin.get("fname"), "%" + filterOption.getSearchKey() + "%");
        Predicate lnameMatch = criteriaBuilder.like(authorJoin.get("lname"), "%" + filterOption.getSearchKey() + "%");
        Predicate usernameMatch = criteriaBuilder.like(authorJoin.get("username"),
                "%" + filterOption.getSearchKey() + "%");
        Predicate orExpression = criteriaBuilder.or(fnameMatch, lnameMatch, usernameMatch);

        if (filterOption.getType() != null) {
            Predicate serviceType = criteriaBuilder.equal(root.get("type"), filterOption.getType());
            orExpression = criteriaBuilder.and(orExpression, serviceType);
        }
        if (filterOption.getKarmaMin() != -999) {
            Predicate karmin = criteriaBuilder.greaterThanOrEqualTo(root.get("karma"), filterOption.getKarmaMin());
            orExpression = criteriaBuilder.and(orExpression, karmin);
        }

        if (filterOption.getKarmaMax() != -999) {
            Predicate karmax = criteriaBuilder.lessThanOrEqualTo(root.get("karma"), filterOption.getKarmaMax());
            orExpression = criteriaBuilder.and(orExpression, karmax);
        }

        return orExpression;

    }

    public ServiceRequest createServiceRequestByType(String serviceType, String title, String description, int karma,
            long userId, ServiceType type) {
        try {
            begin();
            ServiceRequest request = createServiceRequestBasedOnType(serviceType, title, description, karma, type);
            User user = getSession().get(User.class, userId);
            request.setAuthor(user);
            request.setStatus(ServiceRequestStatus.UNASSIGNED);
            getSession().save(request);
            commit();
            close();
            return request;
        } catch (HibernateException e) {
            rollback();
            throw new RuntimeException(e);
        }
    }

    private ServiceRequest createServiceRequestBasedOnType(String serviceType, String title, String description,
            int karma, ServiceType type) {
        if (serviceType.equals("Individual"))
            return new IndividualRequest(title, description, karma, type);
        else
            return new MultipleServiceRequest(title, description, karma, type);

    }

    public void assignUserToService(long id, long userId) {
        try {
            begin();
            User loggedInUser = getSession().get(User.class, userId);
            ServiceRequest req = getSession().get(ServiceRequest.class, id);
            IndividualRequest isr = (IndividualRequest) req;
            isr.setServer(loggedInUser);
            isr.setStatus(ServiceRequestStatus.ASSIGNED);
            commit();
            close();
        } catch (HibernateException e) {
            rollback();
            throw new RuntimeException(e);
        }

    }

    public ServiceRequest getById(long id) {
        try {
            begin();
            ServiceRequest req = getSession().get(ServiceRequest.class, id);
            commit();
            close();
            return req;
        } catch (HibernateException e) {
            rollback();
            throw new RuntimeException(e);
        }
    }

    public void enrollUserToService(long id, long userId) {
        try {
            begin();
            User loggedInUser = getSession().get(User.class, userId);
            ServiceRequest req = getSession().get(ServiceRequest.class, id);
            MultipleServiceRequest msr = (MultipleServiceRequest) req;
            msr.addParticipant(loggedInUser);
            msr.setStatus(ServiceRequestStatus.PENDING);
            commit();
            close();
        } catch (HibernateException e) {
            rollback();
            throw new RuntimeException(e);
        }
    }

    public void completeService(long id, long attribute) {
        try {
            begin();
            ServiceRequest req = getSession().get(ServiceRequest.class, id);
            req.setStatus(ServiceRequestStatus.COMPLETED);
            commit();
            close();
        } catch (HibernateException e) {
            rollback();
            throw new RuntimeException(e);
        }
    }

    public void inProgressService(long id, long attribute) {
        try {
            begin();
            ServiceRequest req = getSession().get(ServiceRequest.class, id);
            req.setStatus(ServiceRequestStatus.INPROGRESS);
            commit();
            close();
        } catch (HibernateException e) {
            rollback();
            throw new RuntimeException(e);
        }
    }

    public List<IndividualRequest> getAssignedRequestByUserId(long userId) throws Exception {
        try {
            // Fetch all user objects from the database
            begin();
            Query query = getSession().createQuery("FROM ServiceRequest");
            User user = getSession().get(User.class, userId);
            List<IndividualRequest> list = filterRequestByServer(user, query.list());
            commit();
            close();
            return list;
        } catch (HibernateException e) {
            rollback();
            // throw new AdException("Could not fetch user list", e);
            throw new Exception("Exception while getting user list: " + e.getMessage());
        }
    }

    private List<IndividualRequest> filterRequestByServer(User user, List<ServiceRequest> list) {
        List<IndividualRequest> tempListOfRequests = new ArrayList<>();
        for (ServiceRequest req : list) {
            if (req instanceof IndividualRequest) {
                IndividualRequest isr = (IndividualRequest) req;
                if (isr.getServer() != null
                        && isr.getServer().equals(user) && isr.getStatus().equals(ServiceRequestStatus.ASSIGNED))
                    tempListOfRequests.add(isr);
            }
        }
        return tempListOfRequests;
    }

    public ServiceRequest approveRequest(long service_id) {
        try {
            begin();
            ServiceRequest req = getSession().get(ServiceRequest.class, service_id);
            req.setStatus(ServiceRequestStatus.COMPLETED);
            commit();
            close();
            return req;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    public ServiceRequest rejectRequest(long service_id) {
        try {
            begin();
            ServiceRequest req = getSession().get(ServiceRequest.class, service_id);
            req.setStatus(ServiceRequestStatus.COMPLETED);
            commit();
            close();
            return req;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

}
