package com.cy.store.config;

import com.cy.store.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class LoginInterceptorConfig implements WebMvcConfigurer {
    HandlerInterceptor loginInterceptor = new LoginInterceptor();
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> pattern = new ArrayList<>();
        pattern.add("/bootstrap3/**");
        pattern.add("/css/**");
        pattern.add("/js/**");
        pattern.add("/images/**");
        pattern.add("/web/login.html");
        pattern.add("/index.html");
        pattern.add("/web/register.html");
        pattern.add("/web/product.html");
        pattern.add("/users/register");
        pattern.add("/users/login");
        pattern.add("/districts/**");
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns(pattern);
    }
}
