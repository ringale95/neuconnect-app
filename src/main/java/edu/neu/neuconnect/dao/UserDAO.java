    package edu.neu.neuconnect.dao;

    import edu.neu.neuconnect.controller.rest.options.PaginationOption;
    import edu.neu.neuconnect.model.User;
    import org.hibernate.HibernateException;
    import org.hibernate.query.Query;
    import org.springframework.stereotype.Repository;

    import java.util.Base64;
    import java.util.List;
    import java.util.Map;

    @Repository
    public class UserDAO extends DAO{
        public User createUser(User user) throws Exception {
            try {

                // save user object in the database
                begin();
                byte[] bytes = user.getPassword().getBytes();
                ((User) user).setPassword(new String(Base64.getEncoder().encode(bytes)));
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


        public void deleteUserById(long id) throws Exception {
            try {
                begin();
                Query query = getSession().createQuery("delete User WHERE id = :ID");
                query.setParameter("ID", id);
                int result = query.executeUpdate();
                commit();
                close();
            } catch (HibernateException e) {
                rollback();
                throw new Exception("Error deleting user with ID: " + id, e);
            }
        }

        public List<User> pagination(PaginationOption options) {
            int firstResult = (options.getPageNumber() - 1) * options.getPageSize();
            try {
                begin();
                Query query = getSession().createQuery("FROM User");
                query.setFirstResult(firstResult);
                query.setMaxResults(options.getPageSize());
                List<User> users = query.list();
                commit();
                close();
                return users;
            } catch (HibernateException e) {
                rollback();
                throw new RuntimeException(e);
            }
        }
    }
