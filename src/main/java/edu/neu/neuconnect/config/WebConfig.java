package edu.neu.neuconnect.config;

import edu.neu.neuconnect.filter.AuthFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean<AuthFilter> authenticationFilter() {
        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthFilter());
        registrationBean.addUrlPatterns("/api/users/*");
        registrationBean.addUrlPatterns("/api/posts/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }

}