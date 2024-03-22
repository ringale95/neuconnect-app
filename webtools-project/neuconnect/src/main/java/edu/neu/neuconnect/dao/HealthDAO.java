package edu.neu.neuconnect.dao;


import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

public class HealthDAO extends DAO {

    @Override
    public List list() throws Exception {
        // This method is not used for health check
        throw new UnsupportedOperationException("Method not supported for health check.");
    }

    @Override
    public List search(Map<String, String> criteria) throws Exception {
        // This method is not used for health check
        throw new UnsupportedOperationException("Method not supported for health check.");
    }

    public boolean checkDatabaseConnection() {
        boolean isConnected = false;
        Session session = null;
        try {
            session = getSession();
            // Execute a simple query to check if the connection is successful
            Query query = session.createNativeQuery("SELECT 1");
            query.uniqueResult();
            isConnected = true;
        } catch (Exception e) {
            // Log any exceptions that occur during the connection check
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return isConnected;
    }
}
