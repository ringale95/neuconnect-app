package edu.neu.neuconnect.controller.mvc;

import edu.neu.neuconnect.dao.UserDAO;
import edu.neu.neuconnect.model.User;
import jakarta.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static edu.neu.neuconnect.dao.DAO.getSession;

@Controller
@RequestMapping("/neuconnect")
public class AuthenticationController {

    private final UserDAO userDAO;
    @Autowired
    public AuthenticationController(UserDAO userDAO){
        this.userDAO = userDAO;
    }
    @PostMapping("/login")
    @ResponseBody
    public ModelAndView onLogin(@RequestParam String username, @RequestParam String password,  HttpSession session) {
        if (authenticateUser(username, password)) {
            User user = new User();
            user.setUsername(username); // Setting username for loggedInUser
            // Optionally, you can set other user details as needed
            session.setAttribute("loggedInUser", user);
            return new ModelAndView("redirect:/neuconnect");
        } else {
            Map<String, Object> model = new HashMap<>();
            model.put("status", "FAILED");
            return new ModelAndView("login", model);
        }
    }

    @GetMapping("/login")
    public ModelAndView showLoginPage(@RequestParam(required = false) String status){
        Map<String, Object> model = new HashMap<>();
        model.put("status", status);
        return new ModelAndView("login",model );
    }

    public boolean authenticateUser(String username, String password){
        try {
            Query query = getSession().createQuery("FROM User WHERE username = :username AND password = :password");
            query.setParameter("username", username);
            query.setParameter("password", Base64.getEncoder().encodeToString(password.getBytes()));
            User user = (User) query.uniqueResult();

            return user != null;
        } catch (HibernateException e) {
            userDAO.rollback();
            // Handle the exception, log it, or throw a custom exception
            return false;
        }
    }
}
