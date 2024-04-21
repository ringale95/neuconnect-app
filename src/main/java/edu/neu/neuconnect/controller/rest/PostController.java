package edu.neu.neuconnect.controller.rest;

import edu.neu.neuconnect.dao.CommentDAO;
import edu.neu.neuconnect.dao.PostDAO;
import edu.neu.neuconnect.dao.UserDAO;
import edu.neu.neuconnect.model.Comment;
import edu.neu.neuconnect.model.Post;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostDAO postDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private CommentDAO commentDAO;

    @PostMapping()
    public Post createPost(@RequestBody Post post, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        long userId = (Long) request.getSession().getAttribute("userId");
        post.setAuthor(userDAO.getById(userId));
        return postDAO.create(post);
    }

    @GetMapping("/{id}")
    public Post getPostByID(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
        try {
            return postDAO.getById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/{id}/comment")
    public Post addComment(@PathVariable int id, @RequestBody Comment comment, HttpSession session) throws Exception {
        Comment addedComment = commentDAO.create(comment);
        Post existingPost = postDAO.getById(id);
        return existingPost;
    }
}
