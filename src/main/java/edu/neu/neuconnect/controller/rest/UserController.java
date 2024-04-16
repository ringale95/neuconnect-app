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

import java.util.Base64;
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
            byte[] bytes = user.getPassword().getBytes();
            user.setPassword(new String(Base64.getEncoder().encode(bytes)));
            return userDAO.create(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/{id}")
    @ResponseBody
    public User getUserByID(@PathVariable long id, HttpServletRequest request){
        try {
            return userDAO.getById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteUserById(@PathVariable long id) {
        try {
            userDAO.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting user with ID: " + id, e);
        }
    }

    @PatchMapping("/{id}")
    @ResponseBody
    public ResponseEntity updateUser(@PathVariable long id, @RequestBody User user) {
        try {
            user.setId(id);
            User updatedUser = userDAO.partialUpdate(user);
            return ResponseEntity.ok().body(updatedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user with ID: " + id);
        }
    }

    @PostMapping("/fetch")
    @ResponseBody
    public ResponseEntity pagination(@RequestBody PaginationOption options){
        List<User> records = userDAO.pagination(options);
        int pageCount = userDAO.getPageCount(options);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new PaginationResponseType<User>(records, options.getPageNumber(), pageCount));
    }

    @GetMapping("/all")
    @ResponseBody
    public List all() throws Exception {
        return userDAO.list();
    }
}
