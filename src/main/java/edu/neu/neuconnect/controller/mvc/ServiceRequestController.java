package edu.neu.neuconnect.controller.mvc;

import edu.neu.neuconnect.dao.UserDAO;
import edu.neu.neuconnect.model.ServiceType;
import edu.neu.neuconnect.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/services")
public class ServiceRequestController {

    @Autowired
    private UserDAO userDao;
    @GetMapping
    public String getServiceRequestPage(){
        return "services/services-landing";
    }

    @GetMapping("/trainer-dashboard")
    public ModelAndView getTrainerDashboard(HttpSession session) throws Exception {
        Map<String, Object> model = new HashMap<>();
        User user = (User) session.getAttribute("user");
        model.put("user", user);
        model.put("verifiedTrainer", userDao.isVerifiedAsTrainer((long)session.getAttribute("userId")));
        model.put("serviceTypes", ServiceType.getFitnessTypes());
        return new ModelAndView("services/trainer-dashboard",model);
    }
}
