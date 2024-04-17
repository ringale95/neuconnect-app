package edu.neu.neuconnect.controller.rest;

import edu.neu.neuconnect.controller.rest.options.PaginationOption;
import edu.neu.neuconnect.controller.rest.types.PaginationResponseType;
import edu.neu.neuconnect.dao.NotificationDAO;
import edu.neu.neuconnect.dao.UserDAO;
import edu.neu.neuconnect.model.Certificate;
import edu.neu.neuconnect.model.ServiceType;
import edu.neu.neuconnect.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/users")
public class UserController {
    private final UserDAO userDAO;
    private final NotificationDAO notificationDAO;

    @Autowired
    public UserController(UserDAO userDAO, NotificationDAO notificationDAO){
        this.userDAO = userDAO;
        this.notificationDAO = notificationDAO;
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

    @PostMapping("/{id}/certificates")
    @ResponseBody
    public ResponseEntity createCertificate(@RequestParam("file") MultipartFile file, @RequestParam("type") ServiceType type, @PathVariable long id) throws Exception {
        Certificate certificate = new Certificate(false, saveFileAndReturnPath(file,id, type), type);
        userDAO.addCertificateToUser(id, certificate);
        notificationDAO.push(id, "For user - "+ id + ", Certificate for type " + type + " added succesfully and is sent for verification!");
      //  serviceDAO.newRequest(id, "Review Certificate for USER:" + id + " of type:" + type, userDAO.getAuthorityId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    private String saveFileAndReturnPath(MultipartFile file, long id, ServiceType type) {
        try {
            // Define the directory where you want to save the file
            String directoryPath = "src/main/resources/static/certificates";

            // Create the directory if it doesn't exist
            Path uploadPath = Path.of(directoryPath);
            Files.createDirectories(uploadPath);

            // Generate a unique filename for the file
            String fileName =  "id_" + id + "_" + type.name() + "_" + System.currentTimeMillis() + ".pdf";

            // Create the file object
            Path filePath = uploadPath.resolve(fileName);

            // Save the file
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Return the path of the saved file
            return filePath.toString();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save the file.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}

