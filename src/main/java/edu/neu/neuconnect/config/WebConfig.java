package edu.neu.neuconnect.config;

import edu.neu.neuconnect.dao.UserDAO;
import edu.neu.neuconnect.filter.AuthFilter;
import edu.neu.neuconnect.filter.AuthMVCFilter;
import edu.neu.neuconnect.filter.CheckAdmin;
import edu.neu.neuconnect.filter.ValidLoginFilter;
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
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<AuthMVCFilter> authenticationMVCFilter() {
        FilterRegistrationBean<AuthMVCFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthMVCFilter());
        registrationBean.addUrlPatterns("/user-dashboard/*");
        registrationBean.addUrlPatterns("/feed");
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

}