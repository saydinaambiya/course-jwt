package com.enigmacamp.jwt.configuration;

import com.enigmacamp.jwt.controller.interceptor.HeaderInterceptor;
import com.enigmacamp.jwt.controller.interceptor.SimpleInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    SimpleInterceptor simpleInterceptor;

    @Autowired
    HeaderInterceptor headerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(simpleInterceptor);
        registry.addInterceptor(headerInterceptor);
    }
}
