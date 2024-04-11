package edu.neu.neuconnect.controller.mvc;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/neu-connect")
public class LandingController {

    @GetMapping
    public ModelAndView homepage(HttpSession session) throws Exception {
        Object userLoggedIn = session.getAttribute("loggedInUser");
        if(userLoggedIn != null) {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView("homepage",model);
        }
        else
            return new ModelAndView("redirect:/neuconnect/login");
    }

}
