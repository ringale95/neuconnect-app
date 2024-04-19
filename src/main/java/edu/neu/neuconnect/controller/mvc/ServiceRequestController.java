package edu.neu.neuconnect.controller.mvc;

import edu.neu.neuconnect.controller.rest.options.SortOption;
import edu.neu.neuconnect.dao.UserDAO;
import edu.neu.neuconnect.model.ServiceRequest;
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

    @GetMapping("/services-list")
    public ModelAndView getServicesList(){
        Map<String, Object> model = new HashMap<>();
        model.put("types", ServiceType.values());
        model.put("sortBys", SortOption.getSortOptions());
        return new ModelAndView("services/servicRequest-list", model);
    }

    @GetMapping("/tutor-dashboard")
    public ModelAndView getTutorDashboard(HttpSession session) throws Exception {
        Map<String, Object> model = new HashMap<>();
        User user = (User) session.getAttribute("user");
        model.put("user", user);
        model.put("verifiedTrainer", userDao.isVerifiedAsTutor((long)session.getAttribute("userId")));
        model.put("serviceTypes", ServiceType.getTutorTypes());
        return new ModelAndView("services/trainer-dashboard",model);
    }

    @GetMapping("/tutoring-list")
    public ModelAndView getTutoringList(){
        Map<String, Object> model = new HashMap<>();
        model.put("types", ServiceType.values());
        model.put("sortBys", SortOption.getSortOptions());
        return new ModelAndView("services/servicRequest-list", model);
    }

    @GetMapping("/career-dashboard")
    public ModelAndView getCareerDashboard(HttpSession session) throws Exception {
        Map<String, Object> model = new HashMap<>();
        User user = (User) session.getAttribute("user");
        model.put("user", user);
        model.put("verifiedTrainer", userDao.isVerifiedAsCareerConsultant((long)session.getAttribute("userId")));
        model.put("serviceTypes", ServiceType.getCareerConsultantTypes());
        return new ModelAndView("services/trainer-dashboard",model);
    }

    @GetMapping("/career-list")
    public ModelAndView getCareerList(){
        Map<String, Object> model = new HashMap<>();
        model.put("types", ServiceType.values());
        model.put("sortBys", SortOption.getSortOptions());
        return new ModelAndView("services/servicRequest-list", model);
    }
}
