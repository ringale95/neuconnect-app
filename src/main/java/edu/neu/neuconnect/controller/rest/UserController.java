package edu.neu.neuconnect.controller.rest;

import edu.neu.neuconnect.controller.rest.options.PaginationOption;
import edu.neu.neuconnect.controller.rest.types.PaginationResponseType;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/users")
public class UserController {
    private final UserDAO userDAO;
    @Autowired
    public UserController(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @PostMapping()
    @ResponseBody
    public User createUser(@RequestBody User user){
        try {
            return userDAO.createUser(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/{id}")
    @ResponseBody
    public User getUserByID(@PathVariable long id, HttpServletRequest request){
        System.out.println("Printing get id" +id);
        try {
            return userDAO.getUserByID(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteUserById(@PathVariable long id) {
        try {
            userDAO.deleteUserById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting user with ID: " + id, e);
        }
    }

    @PatchMapping("/{id}")
    @ResponseBody
    public ResponseEntity updateUser(@PathVariable long id, @RequestBody User user) {
        try {
            user.setId(id);
            User updatedUser = userDAO.updateUser(user);
            return ResponseEntity.ok().body(updatedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user with ID: " + id);
        }
    }

    @PostMapping("/fetch")
    @ResponseBody
    public ResponseEntity pagination(@RequestBody PaginationOption options){
        List<User> records = userDAO.pagination(options);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new PaginationResponseType(records, options.getPageNumber(), options.getPageSize()));
    }
}
