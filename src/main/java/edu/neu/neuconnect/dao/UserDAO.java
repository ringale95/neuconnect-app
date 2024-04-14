    package edu.neu.neuconnect.dao;

    import edu.neu.neuconnect.controller.rest.options.PaginationOption;
    import edu.neu.neuconnect.model.User;
    import org.hibernate.HibernateException;
    import org.hibernate.query.Query;
    import org.springframework.stereotype.Repository;

    import java.util.Base64;
    import java.util.List;

    @Repository
    public class UserDAO extends DAO<User>{
        public UserDAO() {
            super(User.class, "User");
        }

        public User updateUser(User user) throws Exception {
            try {

                // save user object in the database
                begin();
                User existingUser = (User) getSession().get(User.class, user.getId());
                existingUser.setUsername(user.getUsername());
                existingUser.setGender(user.getGender());
                existingUser.setDob(user.getDob());
                existingUser.setNuid(user.getNuid());
                existingUser.setFname(user.getFname());
                existingUser.setLname(user.getLname());
                existingUser.setUsername(user.getUsername());
                byte[] bytes = user.getPassword().getBytes();
                existingUser.setPassword(new String(Base64.getEncoder().encode(bytes))); // Encode the new password inline
                getSession().merge(existingUser);
                commit();
                close();
                return existingUser;
            } catch (HibernateException e) {
                rollback();
                throw new Exception("Exception while creating user: " + e.getMessage());
            }
        }



        public boolean authenticateUser(String username, String password) {
            try {
                begin();
                Query query = getSession().createQuery("FROM User WHERE username = :username");
                query.setParameter("username", username);
                User user = (User) query.uniqueResult();
                commit();
                close();

                if (user != null) {
                    // Decode the stored password and compare it with the provided password
                    byte[] decodedPasswordBytes = Base64.getDecoder().decode(user.getPassword());
                    String decodedPassword = new String(decodedPasswordBytes);
                    boolean isAuthenticated = decodedPassword.equals(password);
                    if (!isAuthenticated) {
                        System.out.println("Password does not match for user: " + username);
                    }
                    return isAuthenticated;
                } else {
                    System.out.println("User not found for username: " + username);
                    return false;
                }
            } catch (HibernateException e) {
                rollback();
                System.out.println("Error authenticating user: " + e.getMessage());
                // Handle the exception, log it, or throw a custom exception
                return false;
            }
        }

        public User getUserByUsername(String username) {
            try {
                begin();
                Query query = getSession().createQuery("FROM User WHERE username = :username");
                query.setParameter("username", username);
                User user = (User) query.uniqueResult();
                commit();
                close();
                return user;
            } catch (HibernateException e) {
                rollback();
                throw new RuntimeException(e);
            }
        }
    }
