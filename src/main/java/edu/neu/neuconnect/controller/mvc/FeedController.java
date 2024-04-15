package edu.neu.neuconnect.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FeedController {

    @GetMapping("/feed")
    public String showFeed(){
        return "feed/feed-list";
    }
}
