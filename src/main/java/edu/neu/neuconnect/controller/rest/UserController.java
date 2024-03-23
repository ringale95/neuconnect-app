package edu.neu.neuconnect.controller.rest;

import edu.neu.neuconnect.dao.UserDAO;
import edu.neu.neuconnect.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/users")
public class UserController {

    private final UserDAO userDAO;
    @Autowired
    public UserController(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @PostMapping
    @ResponseBody
    public User createUser(@RequestBody User user){
        try {
            return userDAO.createUser(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping
    @ResponseBody
    public List<User> getAllUsers(){
        try {
            return userDAO.list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public User getUserByID(@PathVariable long id){
        try {
            return userDAO.getUserByID(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
