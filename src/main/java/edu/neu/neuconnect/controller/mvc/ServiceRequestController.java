package edu.neu.neuconnect.controller.mvc;

import edu.neu.neuconnect.controller.rest.options.SortOption;
import edu.neu.neuconnect.dao.NotificationDAO;
import edu.neu.neuconnect.dao.ServiceDAO;
import edu.neu.neuconnect.dao.UserDAO;
import edu.neu.neuconnect.model.IndividualRequest;
import edu.neu.neuconnect.model.MultipleServiceRequest;
import edu.neu.neuconnect.model.ServiceRequest;
import edu.neu.neuconnect.model.ServiceType;
import edu.neu.neuconnect.model.User;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/services")
public class ServiceRequestController {

    @Autowired
    private UserDAO userDao;

    @Autowired
    private ServiceDAO serviceDAO;

    @Autowired
    private NotificationDAO notificationDAO;

    @GetMapping
    public String getServiceRequestPage() {
        return "services/services-landing";
    }

    @GetMapping("/trainer-dashboard")
    public ModelAndView getTrainerDashboard(HttpSession session) throws Exception {
        Map<String, Object> model = new HashMap<>();
        User user = (User) session.getAttribute("user");
        model.put("user", user);
        model.put("verifiedTrainer", userDao.isVerifiedAsTrainer((long) session.getAttribute("userId")));
        model.put("serviceTypes", ServiceType.getFitnessTypes());
        return new ModelAndView("services/trainer-dashboard", model);
    }

    @GetMapping("/services-list")
    public ModelAndView getServicesList() {
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
        model.put("verifiedTrainer", userDao.isVerifiedAsTutor((long) session.getAttribute("userId")));
        model.put("serviceTypes", ServiceType.getTutorTypes());
        return new ModelAndView("services/trainer-dashboard", model);
    }

    @GetMapping("/tutoring-list")
    public ModelAndView getTutoringList() {
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
        model.put("verifiedTrainer", userDao.isVerifiedAsCareerConsultant((long) session.getAttribute("userId")));
        model.put("serviceTypes", ServiceType.getCareerConsultantTypes());
        return new ModelAndView("services/trainer-dashboard", model);
    }

    @GetMapping("/career-list")
    public ModelAndView getCareerList() {
        Map<String, Object> model = new HashMap<>();
        model.put("types", ServiceType.values());
        model.put("sortBys", SortOption.getSortOptions());
        return new ModelAndView("services/servicRequest-list", model);
    }

    @GetMapping("/session-form")
    public String getSessionForm() {
        return "services/session-form";
    }

    @PostMapping("/session-form")
    public void createSession(@RequestParam("serviceType") String type,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("karma") int karma, HttpSession session, HttpServletResponse response) throws Exception {
        long userId = (long) session.getAttribute("userId");
        serviceDAO.createServiceRequestByType(type, title, description, karma, userId);
        notificationDAO.push(userId, "For user - " + userId + ", Service Request of type: " + type
                + " added succesfully and is currently Unassigned!");
        response.sendRedirect("trainer-dashboard");
    }

    @GetMapping("/service-status/{id}")
    public ModelAndView getSummaryOfService(@PathVariable long id) throws Exception {
        Map<String, Object> model = new HashMap<>();
        ServiceRequest req = (ServiceRequest) serviceDAO.getById(id);
        model.put("req", req);
        model.put("servers", getServersList(id));
        return new ModelAndView("services/service-status", model);
    }

    private List<User> getServersList(long id) throws Exception {
        ServiceRequest service = this.serviceDAO.getById(id);
        if (service instanceof IndividualRequest) {
            IndividualRequest isr = (IndividualRequest) service;
            List<User> ls = new ArrayList<>();
            ls.add(isr.getServer());
            return ls;
        } else {
            MultipleServiceRequest msr = (MultipleServiceRequest) service;
            return msr.getParticipants();
        }
    }

    @GetMapping("/{id}/assign")
    public void setAssigned(@PathVariable long id, HttpSession session, HttpServletResponse res) throws Exception {
        this.serviceDAO.assignUserToService(id, (long) session.getAttribute("userId"));
        res.sendRedirect("/services/service-status/" + id);
        return;
    }

    @GetMapping("/{id}/enroll")
    public void setEnroll(@PathVariable long id, HttpSession session, HttpServletResponse res)
            throws Exception {
        this.serviceDAO.enrollUserToService(id, (long) session.getAttribute("userId"));
        res.sendRedirect("/services/service-status/" + id);
        return;
    }

    @GetMapping("/{id}/complete")
    public void setCompleted(@PathVariable long id, HttpSession session, HttpServletResponse res)
            throws Exception {
        this.serviceDAO.completeService(id, (long) session.getAttribute("userId"));
        res.sendRedirect("/services/service-status/" + id);
        return;
    }

    @GetMapping("/{id}/in-progress")
    public void setInprogress(@PathVariable long id, HttpSession session, HttpServletResponse res)
            throws Exception {
        this.serviceDAO.inProgressService(id, (long) session.getAttribute("userId"));
        res.sendRedirect("/services/service-status/" + id);
        return;
    }

}
