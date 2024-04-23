package edu.neu.neuconnect.controller.rest.types;

import java.util.List;
import edu.neu.neuconnect.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentResponse {
    private List<Comment> records;
    private long postId;
}