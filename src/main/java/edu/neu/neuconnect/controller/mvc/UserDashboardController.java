package edu.neu.neuconnect.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user-dashboard")
public class UserDashboardController {

    @GetMapping
    public ModelAndView handleGet(){
        return new ModelAndView("user-dashboard/user-dashboard");
    }

    @GetMapping("/manage-users")
    public ModelAndView manageUsers(){
        return new ModelAndView("user-dashboard/manage-users");
    }
}
