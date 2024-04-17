package edu.neu.neuconnect.controller.mvc;

import edu.neu.neuconnect.dao.UserDAO;
import edu.neu.neuconnect.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AuthenticationController {

    @Autowired
    private UserDAO userDAO;

    @GetMapping
    public String showLanding(){
        return "landing-page";
    }

    @GetMapping("/register")
    public ModelAndView showRegistrationPage(@RequestParam(required = false) String status){
        Map<String, Object> model = new HashMap<>();
        model.put("status", status);
        return new ModelAndView("register", model);
    }

    @GetMapping("/login")
    public ModelAndView showLoginPage(@RequestParam(value = "url", required = false) String url) {
        Map<String, Object> model = new HashMap<>();
        model.put("url", url);
        return new ModelAndView("login", model);
    }


    @PostMapping("/login-action")
    public void performLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String url = request.getParameter("url");
        response.sendRedirect(url.equals("") ? "user-dashboard": url);
        return;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
       return "logout";
    }

}
