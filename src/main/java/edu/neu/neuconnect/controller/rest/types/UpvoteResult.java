package edu.neu.neuconnect.controller.rest.types;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpvoteResult {
    private long postId;
    private int currentUpvotes;
}
