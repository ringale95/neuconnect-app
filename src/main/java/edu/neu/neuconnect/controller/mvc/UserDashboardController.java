package edu.neu.neuconnect.controller.mvc;

import edu.neu.neuconnect.controller.rest.options.FilterOption;
import edu.neu.neuconnect.controller.rest.options.PaginationOption;
import edu.neu.neuconnect.controller.rest.options.SortOption;
import edu.neu.neuconnect.dao.UserDAO;
import edu.neu.neuconnect.model.RoleTypes;
import edu.neu.neuconnect.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user-dashboard")
public class UserDashboardController {

    @Autowired
    private UserDAO userDAO;

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
}
