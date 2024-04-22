package edu.neu.neuconnect.controller.mvc;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.neu.neuconnect.model.User;
import jakarta.servlet.http.HttpSession;

@Controller
public class FeedController {

    @GetMapping("/feed")
    public ModelAndView showFeed(HttpSession session) {
        Map<String, Object> model = new HashMap<>();
        User user = (User) session.getAttribute("user");
        model.put("user", user);
        return new ModelAndView("feed/feed-list", model);
    }

    @GetMapping("/create-post")
    public String createPost() {
        return "feed/post-form";
    }
}
