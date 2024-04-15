package edu.neu.neuconnect.dao;

import edu.neu.neuconnect.model.Post;
import org.springframework.stereotype.Service;

@Service
public class PostDAO extends DAO<Post>{
    public PostDAO() {
        super(Post.class, "Post");
    }
}
