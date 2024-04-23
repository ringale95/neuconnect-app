package edu.neu.neuconnect.controller.mvc;

import edu.neu.neuconnect.controller.rest.options.FilterOption;
import edu.neu.neuconnect.controller.rest.options.PaginationOption;
import edu.neu.neuconnect.controller.rest.options.SortOption;
import edu.neu.neuconnect.dao.ServiceDAO;
import edu.neu.neuconnect.dao.UserDAO;
import edu.neu.neuconnect.model.Certificate;
import edu.neu.neuconnect.model.IndividualRequest;
import edu.neu.neuconnect.model.RoleTypes;
import edu.neu.neuconnect.model.ServiceRequest;
import edu.neu.neuconnect.model.User;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user-dashboard")
public class UserDashboardController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ServiceDAO serviceDAO;

    @GetMapping
    public ModelAndView handleGet() {
        return new ModelAndView("user-dashboard/user-dashboard");
    }

    @GetMapping("/manage-users")
    public ModelAndView manageUsers() throws Exception {
        Map<String, Object> model = new HashMap<>();
        List<User> users = userDAO.pagination(new PaginationOption(12, 1, new FilterOption(), new SortOption()));
        model.put("users", users);
        model.put("pageNumber", 1);
        model.put("totalPages",
                userDAO.getPageCount(new PaginationOption(12, 1, new FilterOption(), new SortOption())));
        return new ModelAndView("user-dashboard/manage-users/manage-users", model);
    }

    @GetMapping("/manage-users/{id}/edit")
    public ModelAndView editUser(@PathVariable long id) throws Exception {
        Map<String, Object> model = new HashMap<>();
        User user = userDAO.getById(id);
        model.put("user", user);
        model.put("roles", RoleTypes.values());
        return new ModelAndView("user-dashboard/manage-users/edit/user-edit", model);
    }

    @GetMapping("/user-profile/{id}")
    public ModelAndView userProfile(@PathVariable long id) throws Exception {
        Map<String, Object> model = new HashMap<>();
        User user = userDAO.getById(id);
        model.put("user", user);
        model.put("roles", RoleTypes.values());
        return new ModelAndView("user-profile", model);
    }

    @GetMapping("/authority-dashboard")
    public ModelAndView authorityDashboard(HttpSession session) throws Exception {
        Map<String, Object> model = new HashMap<>();
        List<IndividualRequest> request = serviceDAO.getAssignedRequestByUserId((long) session.getAttribute("userId"));
        model.put("requests", request);
        return new ModelAndView("user-dashboard/authority-dashboard", model);
    }

    @GetMapping("/{cert_id}/certificates")
    public void getCertificate(@PathVariable long cert_id, HttpSession session, HttpServletResponse response)
            throws Exception {
        // Assuming userDAO is an instance of your DAO class
        Certificate certificate = userDAO.getCertificateById(cert_id);

        if (certificate != null) {
            String filePath = certificate.getPath(); // Assuming getPath() returns the file path
            downloadFile(filePath, response);
        } else {
            throw new Exception("Certificate not found with ID: " + cert_id);
        }
    }

    private void downloadFile(String filePath, HttpServletResponse response) throws Exception {
        File file = new File(filePath);
        if (file.exists()) {
            // Set the content type and headers for the response
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");

            // Copy the file content to the response output stream
            try (FileInputStream fis = new FileInputStream(file);
                    BufferedInputStream bis = new BufferedInputStream(fis)) {
                ServletOutputStream os = response.getOutputStream();
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = bis.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
                os.flush();
            } catch (IOException e) {
                throw new Exception("Error occurred while reading the file", e);
            }
        } else {
            throw new FileNotFoundException("File not found: " + filePath);
        }
    }

    @GetMapping("/{service_id}/services/approve")
    public void approveCertificate(@PathVariable long service_id, HttpSession session, HttpServletResponse response)
            throws Exception {
        // Assuming userDAO is an instance of your DAO class
        ServiceRequest serviceRequest = serviceDAO.approveRequest(service_id);
        Certificate certificate = ((IndividualRequest) serviceRequest).getCertificateAttached();
        certificate.setVerified(true);
        userDAO.saveCertificate(certificate);
        response.sendRedirect("/user-dashboard/authority-dashboard");
        return;
    }

    @GetMapping("/{service_id}/services/reject")
    public void rejectCertificate(@PathVariable long service_id, HttpSession session, HttpServletResponse response)
            throws Exception {
        // Assuming userDAO is an instance of your DAO class
        ServiceRequest serviceRequest = serviceDAO.rejectRequest(service_id);
        response.sendRedirect("/user-dashboard/authority-dashboard");
        return;

    }

}
