package edu.neu.neuconnect.controller.mvc;

import edu.neu.neuconnect.dao.UserDAO;
import edu.neu.neuconnect.model.User;
import jakarta.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static edu.neu.neuconnect.dao.DAO.getSession;

@Controller
@RequestMapping("/neu-connect")
public class AuthenticationController {

    private final UserDAO userDAO;
    @Autowired
    public AuthenticationController(UserDAO userDAO){
        this.userDAO = userDAO;
    }
    @GetMapping("/login")
    public ModelAndView showLoginPage(@RequestParam(required = false) String status){
        Map<String, Object> model = new HashMap<>();
        model.put("status", status);
        return new ModelAndView("login",model );
    }

}
