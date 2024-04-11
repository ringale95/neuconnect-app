package edu.neu.neuconnect.dao;

import edu.neu.neuconnect.model.User;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class UserDAO extends DAO{
    public User createUser(User user) throws Exception {
        try {

            // save user object in the database
            begin();
//            byte[] bytes = user.getPassword().getBytes();
            String verificationToken = generateVerificationToken();
//            user.setVerificationToken(verificationToken);
//            ((User) user).setPassword(new String(Base64.getEncoder().encode(bytes)));
            getSession().save(user);
            commit();
            close();

            return user;
        } catch (HibernateException e) {
            rollback();
            // throw new AdException("Could not create user " + username, e);
            throw new Exception("Exception while creating user: " + e.getMessage());
        }
    }

    public List<User> list() throws Exception {
        try {
            // Fetch all user objects from the database
            begin();
            Query query = getSession().createQuery("from User");
            List<User> userList = query.list();
            commit();
            close();

            return userList;
        } catch (HibernateException e) {
            rollback();
            // throw new AdException("Could not fetch user list", e);
            throw new Exception("Exception while getting user list: " + e.getMessage());
        }
    }

    public User getUserByID(long id) throws Exception {
        try {
            // Fetch user object from the database based on id
            begin();
            User user = getSession().get(User.class, id);
            commit();
            close();

            return user;
        } catch (HibernateException e) {

            rollback();
            // throw new AdException("Could not fetch user with id: " + id, e);
            throw new Exception("Exception while fetching user with id: " + id + ", " + e.getMessage());
        }

    }

    @Override
    public List search(Map<String, String> criteria) throws Exception {
        return null;
    }

    public boolean authenticateUser(String username, String password) {
        try {
            begin();
            Query query = getSession().createQuery("FROM User WHERE username = :username AND password = :password");
            query.setParameter("username", username);
            query.setParameter("password", Base64.getEncoder().encodeToString(password.getBytes()));
            User user = (User) query.uniqueResult();
            commit();
            close();

            return user != null;
        } catch (HibernateException e) {
            rollback();
            // Handle the exception, log it, or throw a custom exception
            return false;
        }
    }

    private String generateVerificationToken() {
        return UUID.randomUUID().toString();
    }


    public boolean verifyUser(String token) {
        try {
            begin();
            Query query = getSession().createQuery("FROM User WHERE verificationToken = :token");
            query.setParameter("token", token);
            User user = (User) query.uniqueResult();
            if (user != null) {
//                user.setVerified(true); // Mark user as verified
                getSession().update(user); // Update user entity in database
                commit();
                return true;
            }
        } catch (HibernateException e) {
            // Handle exception...
        }
        return false;
    }
}
