package edu.neu.neuconnect.filter;

import edu.neu.neuconnect.dao.UserDAO;
import edu.neu.neuconnect.model.RoleTypes;
import edu.neu.neuconnect.model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class ValidLoginFilter implements Filter {

    private UserDAO userDAO;

    public ValidLoginFilter(UserDAO userDAO){
        this.userDAO = userDAO;
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
            throw new ServletException("Only HTTP requests are supported");
        }
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;


        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (req.getSession(false) != null && userDAO.authenticateUser(username, password)) {
            // Fetch User from DB
            User exisitingUser = userDAO.getUserByUsername(username);
            //Get HttpSession from HttpServletRequest
            HttpSession session = req.getSession(true);
            session.setAttribute("user", exisitingUser);
            session.setAttribute("userId", exisitingUser.getId());
            session.setAttribute("role", exisitingUser.getRole() != null ? exisitingUser.getRole() : RoleTypes.STUDENT);
            chain.doFilter(request, response);
            return;
        }

        res.sendRedirect("/login?url=" + req.getParameter("url"));
        return;
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
