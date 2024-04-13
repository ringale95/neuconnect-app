package edu.neu.neuconnect.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuthFilter extends OncePerRequestFilter {
    private List<String> excludedPaths;
    public AuthFilter(){
        this.excludedPaths = new ArrayList<>();
        excludedPaths.add("/api/users");
        excludedPaths.add("/api/logout");
        excludedPaths.add("/api/auth");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
            throw new ServletException("Only HTTP requests are supported");
        }
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        //Get HttpSession from HttpServletRequest
        HttpSession session = req.getSession(false);

        //Check if session is valid
        if (session == null) {
            res.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return excludedPaths.contains(path);
    }
}
