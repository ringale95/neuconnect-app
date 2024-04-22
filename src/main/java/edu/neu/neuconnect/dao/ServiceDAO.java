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
            ServiceType type) throws Exception {
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
            List result = query.getResultList();
            commit();
            close();
            return result;
        } catch (HibernateException e) {
            rollback();
            throw new RuntimeException(e);
        }
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
        if (filterOption.getType() != null) {
            Predicate karmin = criteriaBuilder.greaterThanOrEqualTo(root.get("karma"), filterOption.getKarmaMin());
            orExpression = criteriaBuilder.and(orExpression, karmin);
        }

        if (filterOption.getType() != null) {
            Predicate karmax = criteriaBuilder.lessThanOrEqualTo(root.get("karma"), filterOption.getKarmaMax());
            orExpression = criteriaBuilder.and(orExpression, karmax);
        }

        return orExpression;

    }

}
