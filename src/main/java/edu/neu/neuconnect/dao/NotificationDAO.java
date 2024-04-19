package edu.neu.neuconnect.dao;

import edu.neu.neuconnect.controller.rest.options.FilterOption;
import edu.neu.neuconnect.controller.rest.options.PaginationOption;
import edu.neu.neuconnect.model.Notification;
import edu.neu.neuconnect.model.NotificationStatus;
import edu.neu.neuconnect.model.RoleTypes;
import edu.neu.neuconnect.model.User;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.List;

@Service
public class NotificationDAO extends DAO<Notification>{
    public NotificationDAO() {
        super(Notification.class, "Notification");
    }

    public void push(long id, String message) throws Exception {
        try {
            begin();
            Notification notification = new Notification();
            User user = getSession().get(User.class, id);
            notification.setUser(user);
            notification.setMessage(message);
            notification.setNotificationStatus(NotificationStatus.GENERATED);
            getSession().save(notification);
            commit();
            close();
        }
        catch (HibernateException e) {
            rollback();
            throw new Exception("Exception while creating user: " + e.getMessage());
        }
    }

    public int getPageCount(PaginationOption option, long userId) throws Exception {
        try {
            begin();
            Query<Notification> query = getPaginatedQuery(option, userId);
            int pageCount = query.getResultList().size();
            commit();
            close();
            return (int) Math.ceil((double)pageCount / option.getPageSize());
        } catch (HibernateException e) {
            rollback();
            throw new RuntimeException(e);
        }
    }

    public List<Notification> pagination(PaginationOption options, long userId) throws Exception {
        int firstResult = (options.getPageNumber() - 1) * options.getPageSize();
        try {
            begin();
            Query<Notification> query = getPaginatedQuery(options, userId);
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

    private Query<Notification> getPaginatedQuery(PaginationOption options, long userId) throws Exception {

        FilterOption filterOption = options.getFilterOption();
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Notification.class);
        Root<Notification> root = criteriaQuery.from(Notification.class);

        criteriaQuery.select(root).where(getPredicateByFilterOption(criteriaBuilder, filterOption, root, userId));
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createdAt")));
        Query<Notification> query = getSession().createQuery(criteriaQuery);

        return query;
    }

    private Expression<Boolean> getPredicateByFilterOption(CriteriaBuilder criteriaBuilder, FilterOption filterOption, Root<Notification> root, long userId) throws Exception {
        Predicate predicate = criteriaBuilder.equal(root.get("user"), getUserById(userId));;

        if(filterOption != null && filterOption.getStatus() != null)
            switch(filterOption.getStatus()){
                case GENERATED:
                    Predicate isGenerated = criteriaBuilder.equal(root.get("notificationStatus"), NotificationStatus.GENERATED);
                    predicate = criteriaBuilder.and(predicate,isGenerated);
                    break;
                case SENT:
                    Predicate isSent = criteriaBuilder.equal(root.get("notificationStatus"), NotificationStatus.SENT);
                    predicate = criteriaBuilder.and(predicate,isSent);
                    break;
                case READ:
                    Predicate isRead = criteriaBuilder.equal(root.get("notificationStatus"), NotificationStatus.READ);
                    predicate = criteriaBuilder.and(predicate,isRead);
                    break;
            }

        return predicate;
    }

    private User getUserById(long userId) throws Exception {
        try {
            // Fetch user object from the database based on id
            User entity = getSession().get(User.class, userId);
            return entity;
        } catch (HibernateException e) {

            rollback();
            // throw new AdException("Could not fetch user with id: " + id, e);
            throw new Exception("Exception while fetching user with id: " + userId + ", " + e.getMessage());
        }
    }


}
