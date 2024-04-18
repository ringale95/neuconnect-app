package edu.neu.neuconnect.dao;

import edu.neu.neuconnect.model.IndividualRequest;
import edu.neu.neuconnect.model.ServiceRequest;
import edu.neu.neuconnect.model.ServiceRequestStatus;
import edu.neu.neuconnect.model.User;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

@Service
public class ServiceDAO extends DAO{
    public ServiceDAO() {
        super(ServiceRequest.class, "ServiceRequest");
    }

    public long newIndividualRequests(long authorId, String description, String title, int karma, long serverId) throws Exception {
        try{
            begin();
            IndividualRequest serviceRequest = new IndividualRequest();
            serviceRequest.setTitle(title);
            serviceRequest.setDescription(description);
            User author = (getSession().get(User.class, authorId));
            User server = getSession().get(User.class, serverId);
            serviceRequest.setAuthor(author);
            serviceRequest.setServer(server);
            serviceRequest.setKarma(karma);
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
}
