package edu.neu.neuconnect.config;

import edu.neu.neuconnect.dao.UserDAO;
import edu.neu.neuconnect.filter.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Autowired
    private UserDAO userDAO;

    @Bean
    public FilterRegistrationBean<AuthFilter> authenticationFilter() {
        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthFilter());
        registrationBean.addUrlPatterns("/api/users/*");
        registrationBean.addUrlPatterns("/api/posts/*");
        registrationBean.addUrlPatterns("/api/notifications/*");
        registrationBean.addUrlPatterns("/api/login/*");

        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<AuthMVCFilter> authenticationMVCFilter() {
        FilterRegistrationBean<AuthMVCFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthMVCFilter());
        registrationBean.addUrlPatterns("/user-dashboard/*");
        registrationBean.addUrlPatterns("/feed");
        registrationBean.addUrlPatterns("/create-post");
        registrationBean.addUrlPatterns("/pushes");
        registrationBean.addUrlPatterns("/logout");
        registrationBean.addUrlPatterns("/services/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<ValidLoginFilter> validLoginFilter() {
        FilterRegistrationBean<ValidLoginFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ValidLoginFilter(userDAO));
        registrationBean.addUrlPatterns("/login-action");
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<CheckAdmin> checkAdmin() {
        FilterRegistrationBean<CheckAdmin> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CheckAdmin());
        registrationBean.addUrlPatterns("/user-dashboard/manage-users");
        registrationBean.setOrder(2);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<CheckTrainer> checkTrainer() {
        FilterRegistrationBean<CheckTrainer> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CheckTrainer());
        registrationBean.addUrlPatterns("/services/trainer-dashboard");
        registrationBean.setOrder(2);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<CheckTutor> checkTutor() {
        FilterRegistrationBean<CheckTutor> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CheckTutor());
        registrationBean.addUrlPatterns("/services/tutor-dashboard");
        registrationBean.setOrder(2);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<CheckConsultant> checkCounsellor() {
        FilterRegistrationBean<CheckConsultant> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CheckConsultant());
        registrationBean.addUrlPatterns("/services/career-dashboard");
        registrationBean.setOrder(2);
        return registrationBean;
    }

}