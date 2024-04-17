package edu.neu.neuconnect.dao;

import edu.neu.neuconnect.controller.rest.options.PaginationOption;
import edu.neu.neuconnect.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class DAO<T> {

    final Class<T> type;

    final String table;

    private static final Logger log = Logger.getAnonymousLogger();
    private static final ThreadLocal sessionThread = new ThreadLocal();
    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    protected DAO(Class<T> type, String table) {
        this.type = type;
        this.table = table;
    }

    public static Session getSession() {
        Session session = (Session) DAO.sessionThread.get();

        if (session == null) {
            session = sessionFactory.openSession();
            DAO.sessionThread.set(session);
        }
        return session;
    }

    protected void begin() {
        getSession().beginTransaction();
    }

    protected void commit() {
        getSession().getTransaction().commit();
    }

    protected void rollback() {
        try {
            getSession().getTransaction().rollback();
        } catch (HibernateException e) {
            log.log(Level.WARNING, "Cannot rollback", e);
        }
        try {
            getSession().close();
        } catch (HibernateException e) {
            log.log(Level.WARNING, "Cannot close", e);
        }
        DAO.sessionThread.set(null);
    }

    public static void close() {
        getSession().close();
        DAO.sessionThread.set(null);
    }

    public T getById(long id) throws Exception {
        try {
            // Fetch user object from the database based on id
            begin();
            T entity = getSession().get(type, id);
            commit();
            close();

            return entity;
        } catch (HibernateException e) {

            rollback();
            // throw new AdException("Could not fetch user with id: " + id, e);
            throw new Exception("Exception while fetching user with id: " + id + ", " + e.getMessage());
        }
    }

    public T create(T entity) throws Exception {
        try{
            begin();
            getSession().save(entity);
            commit();
            close();
            return entity;
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Exception while creating post: " + e.getMessage());
        }

    }

    public List<T> list() throws Exception {
        try {
            // Fetch all user objects from the database
            begin();
            Query query = getSession().createQuery("FROM " + table);
            List<T> list = query.list();
            commit();
            close();
            return list;
        } catch (HibernateException e) {
            rollback();
            // throw new AdException("Could not fetch user list", e);
            throw new Exception("Exception while getting user list: " + e.getMessage());
        }
    }

    public void deleteById(long id) throws Exception {
        try {
            begin();
            Query query = getSession().createQuery("delete " + table + " WHERE id = :ID");
            query.setParameter("ID", id);
            int result = query.executeUpdate();
            commit();
            close();
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Error deleting user with ID: " + id, e);
        }
    }

    public List<T> pagination(PaginationOption options) {
        int firstResult = (options.getPageNumber() - 1) * options.getPageSize();
        try {
            begin();
            Query query = getSession().createQuery("FROM " + table);
            query.setFirstResult(firstResult);
            query.setMaxResults(options.getPageSize());
            List<T> ls = query.list();
            commit();
            close();
            return ls;
        } catch (HibernateException e) {
            rollback();
            throw new RuntimeException(e);
        }
    }

    public void update(User existingUser) throws Exception {
        try {
            begin();
            getSession().merge(existingUser);
            commit();
            close();
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Exception while creating user: " + e.getMessage());
        }
    }
}