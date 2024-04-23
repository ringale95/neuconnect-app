package edu.neu.neuconnect.controller.rest;

import edu.neu.neuconnect.controller.rest.options.PaginationOption;
import edu.neu.neuconnect.controller.rest.types.CommentResponse;
import edu.neu.neuconnect.controller.rest.types.PaginationResponseType;
import edu.neu.neuconnect.controller.rest.types.UpvoteResult;
import edu.neu.neuconnect.dao.CommentDAO;
import edu.neu.neuconnect.dao.PostDAO;
import edu.neu.neuconnect.dao.UserDAO;
import edu.neu.neuconnect.model.Comment;
import edu.neu.neuconnect.model.Post;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        postDAO.create(post);
        return post;
    }

    @GetMapping("/{id}")
    public Post getPostByID(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
        try {
            return postDAO.getById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deletePostById(@PathVariable long id) {
        try {
            postDAO.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting user with ID: " + id, e);
        }
    }

    @PatchMapping("/{id}")
    @ResponseBody
    public ResponseEntity updatePost(@PathVariable long id, @RequestBody Post post) {
        try {
            post.setId(id);
            Post updatePost = postDAO.partialUpdate(post);
            return ResponseEntity.ok().body(updatePost);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating post with ID: " + id);
        }
    }

    @PostMapping("/{id}/comment")
    public Comment addComment(@PathVariable int id, @RequestBody Comment comment, HttpSession session)
            throws Exception {
        long userId = (long) session.getAttribute("userId");
        Comment addedComment = commentDAO.create(comment, id, userId);
        return addedComment;
    }

    @GetMapping("/{id}/comment")
    public CommentResponse getComments(@PathVariable long id)
            throws Exception {
        System.out.println("Post ID: " + id);
        List<Comment> comments = postDAO.getComments(id);
        return new CommentResponse(comments, id);
    }

    @PostMapping("/fetch")
    public ResponseEntity paginationPost(@RequestBody PaginationOption option, HttpSession session)
            throws Exception {
        List<Post> records = postDAO.pagination(option, (long) session.getAttribute("userId"));
        int pageCount = postDAO.getPageCount(option, (long) session.getAttribute("userId"));
        return ResponseEntity.status(HttpStatus.OK)
                .body(new PaginationResponseType<Post>(records, option.getPageNumber(), pageCount));
    }

    @GetMapping("{id}/upvote")
    public UpvoteResult upvotePost(@PathVariable long id, HttpSession session) throws Exception {
        long userId = (long) session.getAttribute("userId");
        this.postDAO.upvotePost(id, userId);
        return new UpvoteResult(id, this.postDAO.getById(id).getUpvotes());
    }

    @GetMapping("{id}/downvote")
    public UpvoteResult downvotePost(@PathVariable long id, HttpSession session) throws Exception {
        long userId = (long) session.getAttribute("userId");
        this.postDAO.downvotePost(id, userId);
        return new UpvoteResult(id, this.postDAO.getById(id).getUpvotes());
    }
}
