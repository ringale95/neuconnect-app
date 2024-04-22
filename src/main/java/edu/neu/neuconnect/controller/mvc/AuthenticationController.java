package edu.neu.neuconnect.controller.mvc;

import edu.neu.neuconnect.dao.UserDAO;
import edu.neu.neuconnect.model.RoleTypes;
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
import org.springframework.web.servlet.ModelAndView;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AuthenticationController {

    @Autowired
    private UserDAO userDAO;

    @GetMapping
    public String showLanding() {
        return "landing-page";
    }

    @GetMapping("/register")
    public ModelAndView showRegistrationPage(@RequestParam(required = false) String status) {
        Map<String, Object> model = new HashMap<>();
        model.put("status", status);
        return new ModelAndView("register", model);
    }

    @GetMapping("/login")
    public ModelAndView showLoginPage(@RequestParam(value = "url", required = false) String url) {
        Map<String, Object> model = new HashMap<>();
        model.put("url", url);
        return new ModelAndView("login", model);
    }

    @PostMapping("/login-action")
    public void performLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws IOException {
        String url = request.getParameter("url");
        String pageTo = getPageUrlByRole((User) session.getAttribute("user"));
        response.sendRedirect(url.equals("") ? pageTo : url);
        return;
    }

    private String getPageUrlByRole(User user) {
        switch (user.getRole()) {
            case STUDENT:
                return "feed";
            case TRAINER:
                return "services/trainer-dashboard";
            case TUTOR:
                return "services/tutor-dashboard";
            case COUNSELLOR:
                return "services/career-dashboard";
            case AUTHORITY:
                return "authority-dashboard";
            default:
                return "user-dashboard/manage-users";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "logout";
    }

    @PostMapping("/register-action")
    @ResponseBody
    public void createUser(@RequestParam("profilePicture") MultipartFile profilePicture,
            @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
            @RequestParam("username") String username, @RequestParam("password") String password,
            @RequestParam("dob") String dobStr, @RequestParam("gender") String gender,
            @RequestParam("nuId") String nuId,
            @RequestParam("aboutMe") String aboutMe, HttpServletResponse response, HttpSession session)
            throws Exception {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = dateFormat.parse(dobStr);

        User user = new User(firstName, lastName, gender, dob, username, encryptPassword(password), nuId, aboutMe,
                RoleTypes.STUDENT, saveFileAndReturnPath(profilePicture, firstName, lastName));
        User createdUser = userDAO.create(user);
        session.setAttribute("user", createdUser);
        session.setAttribute("userId", createdUser.getId());
        session.setAttribute("role", createdUser.getRole());

        response.sendRedirect("/services");
    }

    private String encryptPassword(String password) {
        byte[] bytes = password.getBytes();
        return new String(Base64.getEncoder().encode(bytes));
    }

    private String saveFileAndReturnPath(MultipartFile profilePicture, String firstName, String lastName) {
        try {
            // Define the directory where you want to save the file
            String directoryPath = "src/main/resources/static/profilePic";

            // Create the directory if it doesn't exist
            Path uploadPath = Path.of(directoryPath);
            Files.createDirectories(uploadPath);

            // Generate a unique filename for the file
            String fileName = "fname_" + firstName + "_lname_" + lastName + "_" + System.currentTimeMillis() + ".png";
            String pathToBeReturned = "/profilePic/" + fileName;
            // Create the file object
            Path filePath = uploadPath.resolve(fileName);

            // Save the file
            Files.copy(profilePicture.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Return the path of the saved file
            return pathToBeReturned;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save the file.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
