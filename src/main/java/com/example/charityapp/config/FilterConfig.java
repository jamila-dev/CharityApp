package com.example.charityapp.config; // Adjust this package to where you place the file

import com.example.charityapp.security.JwtAuthenticationFilter; // Adjust this import to your JwtAuthenticationFilter's location
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<JwtAuthenticationFilter> jwtAuthenticationFilterRegistration(JwtAuthenticationFilter filter) {
        FilterRegistrationBean<JwtAuthenticationFilter> registration = new FilterRegistrationBean<>(filter);
        registration.setEnabled(false); // Crucial: This disables auto-registration of the filter
        return registration;
    }
}