    package edu.neu.neuconnect.dao;

    import edu.neu.neuconnect.controller.rest.options.FilterOption;
    import edu.neu.neuconnect.controller.rest.options.PaginationOption;
    import edu.neu.neuconnect.model.Certificate;
    import edu.neu.neuconnect.model.RoleTypes;
    import edu.neu.neuconnect.model.User;
    import org.hibernate.HibernateException;
    import org.hibernate.query.Query;
    import org.springframework.stereotype.Repository;

    import javax.persistence.criteria.CriteriaBuilder;
    import javax.persistence.criteria.CriteriaQuery;
    import javax.persistence.criteria.Predicate;
    import javax.persistence.criteria.Root;
    import java.util.Base64;
    import java.util.List;

    @Repository
    public class UserDAO extends DAO<User>{
        public UserDAO() {
            super(User.class, "User");
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

        public User partialUpdate(User user) throws Exception {
            // save user object in the database
            try {
                // save user object in the database
                begin();
                User existingUser = (User) getSession().get(User.class, user.getId());
                existingUser.setUsername(user.getUsername());
                existingUser.setGender(user.getGender());
                existingUser.setNuid(user.getNuid());
                existingUser.setFname(user.getFname());
                existingUser.setLname(user.getLname());
                existingUser.setRole(user.getRole());
                getSession().merge(existingUser);
                commit();
                close();
                return existingUser;
            } catch (HibernateException e) {
                rollback();
                throw new Exception("Exception while creating user: " + e.getMessage());
            }
        }

        public int getPageCount(PaginationOption options) {
            try {
                begin();
                Query<User> query = getPaginationQuery(options);
                int pageCount = query.getResultList().size();
                commit();
                close();
                return (int) Math.ceil((double)pageCount / options.getPageSize());
            } catch (HibernateException e) {
                rollback();
                throw new RuntimeException(e);
            }
        }

        @Override
        public List<User> pagination(PaginationOption options){
            try {
                begin();
                Query<User> query = getPaginationQuery(options);
                query.setFirstResult((options.getPageNumber() - 1) * options.getPageSize());
                query.setMaxResults(options.getPageSize());
                List pageCount = query.getResultList();
                commit();
                close();
                return pageCount;
            } catch (HibernateException e) {
                rollback();
                throw new RuntimeException(e);
            }
        }

        private Query<User> getPaginationQuery(PaginationOption options) {
            // Filter Options
            FilterOption filterOption = options.getFilterOption();

            // Build Criteria Query
            CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);

            // Supply Predicates
            criteriaQuery.select(root).where(getPredicateByFilterOption(criteriaBuilder, filterOption, root));
            Query<User> query = getSession().createQuery(criteriaQuery);

            return query;
        }


        private Predicate getPredicateByFilterOption(CriteriaBuilder criteriaBuilder, FilterOption filterOption, Root<User> root) {
            Predicate fnameMatch = criteriaBuilder.like(root.get("fname"), "%" + filterOption.getSearchKey() + "%");
            Predicate lnameMatch = criteriaBuilder.like(root.get("lname"), "%" + filterOption.getSearchKey() + "%");
            Predicate usernameMatch = criteriaBuilder.like(root.get("username"), "%" + filterOption.getSearchKey() + "%");
            Predicate orExpression = criteriaBuilder.or(fnameMatch, lnameMatch, usernameMatch);

            if (filterOption.isAdmin()) {
                Predicate isAdmin = criteriaBuilder.equal(root.get("role"), RoleTypes.ADMIN);
                orExpression = criteriaBuilder.and(orExpression, isAdmin);
            } else if (filterOption.isStudent()) {
                Predicate isStudent = criteriaBuilder.equal(root.get("role"), RoleTypes.STUDENT);
                orExpression = criteriaBuilder.and(orExpression, isStudent);
            } else if (filterOption.isAuthority()) {
                Predicate isAuthority = criteriaBuilder.equal(root.get("role"), RoleTypes.AUTHORITY);
                orExpression = criteriaBuilder.and(orExpression, isAuthority);
            }

            return orExpression;
        }

        public void addCertificateToUser(long id, Certificate certificate) throws Exception {
            try {
                begin();
                User existingUser = getSession().get(User.class, id);
                existingUser.addCertificate(certificate);
                getSession().merge(existingUser);
                commit();
                close();
            } catch (HibernateException e) {
                rollback();
                throw new Exception("Exception while creating user: " + e.getMessage());
            }
        }

        public boolean isVerifiedAsTrainer(long userId) throws Exception {
            try {
                begin();
                User existingUser = getSession().get(User.class, userId);
                boolean isVerified = isCertificateListFitnessVerified(existingUser.getCertificates());
                commit();
                close();
                return isVerified;
            } catch (HibernateException e) {
                rollback();
                throw new Exception("Exception while creating user: " + e.getMessage());
            }
        }

        private boolean isCertificateListFitnessVerified(List<Certificate> certificates) {
            if(certificates.size() == 0)
                return false;
            for(Certificate certificate : certificates){
                if(certificate.isApprovedAsFitness())
                    return true;
            }
            return false;
        }

        public long getAuthorityId() {
            try {
                begin();
                Query query = getSession().createQuery("FROM User WHERE role = :type");
                query.setParameter("type", RoleTypes.AUTHORITY);
                User user = (User) query.uniqueResult();
                commit();
                close();
                return user.getId();
            } catch (HibernateException e) {
                rollback();
                throw new RuntimeException(e);
            }
        }

        public boolean isVerifiedAsTutor(long userId) throws Exception {
            try {
                begin();
                User existingUser = getSession().get(User.class, userId);
                boolean isVerified = isCertificateListTutorVerified(existingUser.getCertificates());
                commit();
                close();
                return isVerified;
            } catch (HibernateException e) {
                rollback();
                throw new Exception("Exception while creating user: " + e.getMessage());
            }
        }

        private boolean isCertificateListTutorVerified(List<Certificate> certificates) {
            if(certificates.size() == 0)
                return false;
            for(Certificate certificate : certificates){
                if(certificate.isApprovedAsTutor())
                    return true;
            }
            return false;
        }

        public Object isVerifiedAsCareerConsultant(long userId) throws Exception {
            try {
                begin();
                User existingUser = getSession().get(User.class, userId);
                boolean isVerified = isCertificateListCareerConsultantVerified(existingUser.getCertificates());
                commit();
                close();
                return isVerified;
            } catch (HibernateException e) {
                rollback();
                throw new Exception("Exception while creating user: " + e.getMessage());
            }
        }

        private boolean isCertificateListCareerConsultantVerified(List<Certificate> certificates) {
            if(certificates.size() == 0)
                return false;
            for(Certificate certificate : certificates){
                if(certificate.isApprovedAsCareerConsultant())
                    return true;
            }
            return false;
        }
    }
