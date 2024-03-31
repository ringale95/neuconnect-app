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
@RequestMapping("/neuconnect")
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

    @PostMapping("/register")
    public ModelAndView registerUser(@RequestParam("fname") String fname,
                                     @RequestParam("lname") String lname,
                                     @RequestParam("nuid") String nuid,
                                     @RequestParam("dob") String dobStr,
                                     @RequestParam("gender") String gender,
                                     @RequestParam("username") String username,
                                     @RequestParam("password") String password,
                                     HttpSession session) {

        // Parse the date string to a Date object
        Date dob = null;
        try {
            dob = new SimpleDateFormat("yyyy-MM-dd").parse(dobStr);
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle parsing exception
            return new ModelAndView("redirect:/neuconnect/register?status=FAILED");
        }

        // Create a new User object with the parsed data
        User user = new User();
        user.setFname(fname);
        user.setLname(lname);
        user.setNuid(nuid);
        user.setDob(dob);
        user.setGender(gender);
        user.setUsername(username);
        user.setPassword(password);

        // Save the user to the database
        try {
            userDAO.createUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle database exception
            return new ModelAndView("redirect:/neuconnect/register?status=FAILED");
        }

        // Send verification email to the user
        String verificationLink = "http://localhost:8080/neuconnect/verify?token=" + user.getVerificationToken();
        emailService.sendVerificationEmail(user.getUsername(), verificationLink);

        // Registration successful, redirect to homepage
        session.setAttribute("loggedInUser", user);
        return new ModelAndView("redirect:/neuconnect");
    }
}
