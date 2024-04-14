package edu.neu.neuconnect.dao;

import edu.neu.neuconnect.model.Comment;

public class CommentDAO extends DAO<Comment> {
    protected CommentDAO(Class<Comment> type, String table) {
        super(Comment.class, "Comment");
    }
}
