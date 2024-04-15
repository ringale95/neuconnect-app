package edu.neu.neuconnect.dao;

import edu.neu.neuconnect.model.Comment;
import org.springframework.stereotype.Service;

@Service
public class CommentDAO extends DAO<Comment> {
    protected CommentDAO() {
        super(Comment.class, "Comment");
    }
}
