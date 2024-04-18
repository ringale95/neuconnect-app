package edu.neu.neuconnect.controller.rest;

import edu.neu.neuconnect.dao.UserDAO;
import edu.neu.neuconnect.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {
    private final UserDAO userDAO;
    @Autowired
    public AuthController(UserDAO userDAO){
        this.userDAO = userDAO;
    }
    @PostMapping("/auth")
    public void auth(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        try {
            if (userDAO.authenticateUser(user.getUsername(), user.getPassword())) {
                // Fetch User from DB
                User exisitingUser = userDAO.getUserByUsername(user.getUsername());

                HttpSession session = request.getSession();
                session.setAttribute("user", exisitingUser);
                session.setAttribute("userId", exisitingUser.getId());

                response.setStatus(HttpStatus.OK.value());
            } else {
                // Handle authentication failure
                System.out.println("Unauthorized!");
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
            }
        } catch (Exception e) {
            // Handle exception
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/login/info")
    public Long loginInformation(HttpSession session){
        return (Long) session.getAttribute("userId");
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response){
        request.getSession().invalidate();
        response.setStatus(HttpStatus.OK.value());
    }
}
