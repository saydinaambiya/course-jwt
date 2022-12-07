package com.enigmacamp.jwt.controller.interceptor;

import com.enigmacamp.jwt.controller.UrlMappings;
import com.enigmacamp.jwt.exception.UnathorizedException;
import com.enigmacamp.jwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class HeaderInterceptor implements HandlerInterceptor {
    @Autowired
    RestTemplate restTemplate;

    @Value("${service.authentication}")
    String authServiceUrl;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if (request.getRequestURI().contains(UrlMappings.TOKEN_URL)) {
//            return true;
//        }
//        return jwtUtil.validateToken(request.getHeader("my-header"));
//    }
        try {
            String tokenHeader = request.getHeader("Authorization");
            String[] tokenBearer = tokenHeader.split(" ");
            restTemplate.getForEntity(authServiceUrl+"?token="+tokenBearer[1], String.class);
            return true;
        }catch (HttpClientErrorException exception){
            if (exception.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
                throw new UnathorizedException(exception.getMessage());
            }
            throw new RuntimeException(exception.getMessage());
        }catch (RestClientException e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        }
}
