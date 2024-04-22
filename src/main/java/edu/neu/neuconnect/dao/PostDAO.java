package edu.neu.neuconnect.dao;

import edu.neu.neuconnect.controller.rest.options.FilterOption;
import edu.neu.neuconnect.controller.rest.options.PaginationOption;
import edu.neu.neuconnect.model.Post;
import edu.neu.neuconnect.model.User;

import java.util.List;

import org.springframework.stereotype.Service;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import javax.persistence.criteria.*;

@Service
public class PostDAO extends DAO<Post> {
    public PostDAO() {
        super(Post.class, "Post");
    }

    public int getPageCount(PaginationOption option, long userId) throws Exception {
        try {
            begin();
            Query<Post> query = getPaginatedQuery(option, userId);
            int pageCount = query.getResultList().size();
            commit();
            close();
            return (int) Math.ceil((double) pageCount / option.getPageSize());
        } catch (HibernateException e) {
            rollback();
            throw new RuntimeException(e);
        }
    }

    public List<Post> pagination(PaginationOption options, long userId) throws Exception {
        int firstResult = (options.getPageNumber() - 1) * options.getPageSize();
        try {
            begin();
            Query<Post> query = getPaginatedQuery(options, userId);
            query.setFirstResult(firstResult);
            query.setMaxResults(options.getPageSize());
            User loggedInUser = getSession().get(User.class, userId);
            List result = setLikedForEachPost(query.getResultList(), loggedInUser);
            commit();
            close();
            return result;
        } catch (HibernateException e) {
            rollback();
            throw new RuntimeException(e);
        }
    }

    private List setLikedForEachPost(List<Post> resultList, User loggedInUser) {
        for (Post post : resultList)
            if (post.isLikedBy(loggedInUser))
                post.setLiked(true);
        return resultList;
    }

    private Query<Post> getPaginatedQuery(PaginationOption options, long userId) throws Exception {

        FilterOption filterOption = options.getFilterOption();
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Post.class);
        Root<Post> root = criteriaQuery.from(Post.class);
        criteriaQuery.select(root);

        if (!(filterOption == null || filterOption.isEmpty()))
            criteriaQuery.where(getPredicateByFilterOption(criteriaBuilder, filterOption, root, userId));

        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createdAt")));
        Query<Post> query = getSession().createQuery(criteriaQuery);

        return query;

    }

    private Predicate getPredicateByFilterOption(CriteriaBuilder criteriaBuilder, FilterOption filterOption,
            Root<Post> root, long userId) {
        Join<Post, String> tagJoin = root.join("tags");
        Predicate tagMatch = criteriaBuilder.like(tagJoin, "%" + filterOption.getSearchKey() + "%");
        return tagMatch;
    }

    public Post partialUpdate(Post post) throws Exception {
        try {
            begin();
            Post existingPost = (Post) getSession().get(Post.class, post.getId());
            existingPost.setTitle(post.getTitle());
            existingPost.setContent(post.getContent());
            getSession().merge(existingPost);
            commit();
            close();
            return existingPost;
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Exception while creating user: " + e.getMessage());
        }
    }

    public void upvotePost(long id, long userId) throws Exception {
        try {
            // Fetch user object from the database based on id
            begin();
            Post entity = getSession().get(Post.class, id);
            User user = getSession().get(User.class, userId);
            entity.addUserToLikedBy(user);
            entity.upvote();
            commit();
            close();

        } catch (HibernateException e) {

            rollback();
            // throw new AdException("Could not fetch user with id: " + id, e);
            throw new Exception("Exception while fetching user with id: " + id + ", " + e.getMessage());
        }
    }

    public void downvotePost(long id, long userId) throws Exception {
        try {
            // Fetch user object from the database based on id
            begin();
            Post entity = getSession().get(Post.class, id);
            entity.downvote();
            commit();
            close();

        } catch (HibernateException e) {

            rollback();
            // throw new AdException("Could not fetch user with id: " + id, e);
            throw new Exception("Exception while fetching user with id: " + id + ", " + e.getMessage());
        }
    }
}
