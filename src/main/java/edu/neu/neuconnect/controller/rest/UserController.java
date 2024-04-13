package edu.neu.neuconnect.controller.rest;

import edu.neu.neuconnect.dao.UserDAO;
import edu.neu.neuconnect.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class UserController {

    private final UserDAO userDAO;
    @Autowired
    public UserController(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @PostMapping("/create")
    @ResponseBody
    public User createUser(@RequestBody User user){
        try {
            return userDAO.createUser(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/users/all")
    @ResponseBody
    public List<User> getAllUsers(){
        try {
            return userDAO.list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/users/{id}")
    @ResponseBody
    public User getUserByID(@PathVariable long id, HttpServletRequest request){
        try {
            return userDAO.getUserByID(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/auth")
    @ResponseBody
    public void auth(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        try {
            if (userDAO.authenticateUser(user.getUsername(), user.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute("username", user.getUsername());
                session.setAttribute("userId", user.getId());
                response.setStatus(HttpStatus.OK.value());
            } else {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/users/delete/{id}")
    @ResponseBody
    public void deleteUserById(@PathVariable long id, HttpServletResponse response) {
        try {
            userDAO.deleteUserById(id);
            response.setStatus(HttpStatus.OK.value());
        } catch (Exception e) {
            throw new RuntimeException("Error deleting user with ID: " + id, e);
        }
    }

    @PutMapping("/users/update")
    @ResponseBody
    public ResponseEntity updateUser(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
             User u = userDAO.updateUser(user);
            return ResponseEntity.ok().body(u);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting user with ID: " + user.getId(), e);
        }
    }

    @GetMapping("/logout")
    @ResponseBody
    public void logout(HttpServletRequest request, HttpServletResponse response){
        request.getSession().invalidate();
        response.setStatus(HttpStatus.OK.value());
    }
}
