package edu.neu.neuconnect.dao;

import edu.neu.neuconnect.model.Comment;
import edu.neu.neuconnect.model.Post;
import edu.neu.neuconnect.model.User;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

@Service
public class CommentDAO extends DAO<Comment> {
    protected CommentDAO() {
        super(Comment.class, "Comment");
    }

    public Comment create(Comment entity, long id, long userId) throws Exception {
        try {
            begin();
            getSession().save(entity);
            Post post = getSession().get(Post.class, id);
            User user = getSession().get(User.class, userId);
            entity.setAuthor(user);
            post.addComment(entity);
            commit();
            close();
            return entity;
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Exception while creating post: " + e.getMessage());
        }

    }
}
