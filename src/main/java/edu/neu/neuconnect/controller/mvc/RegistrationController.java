package edu.neu.neuconnect.controller.mvc;

import edu.neu.neuconnect.dao.UserDAO;
import edu.neu.neuconnect.model.User;
import edu.neu.neuconnect.service.EmailService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/neu-connect")
public class RegistrationController {

    private final UserDAO userDAO;
    private final EmailService emailService;

    @Autowired
    public RegistrationController(UserDAO userDAO, EmailService emailService) {
        this.userDAO = userDAO;
        this.emailService = emailService;
    }

    @GetMapping("/register")
    public ModelAndView showRegistrationPage(@RequestParam(required = false) String status) {
        Map<String, Object> model = new HashMap<>();
        model.put("status", status);
        return new ModelAndView("register", model);
    }


}
