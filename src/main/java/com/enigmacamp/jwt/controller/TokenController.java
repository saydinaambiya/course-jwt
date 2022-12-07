package com.enigmacamp.jwt.controller;

import com.enigmacamp.jwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UrlMappings.TOKEN_URL)
public class TokenController {
    @Autowired
    JwtUtil jwtUtil;

    @GetMapping
    public ResponseEntity getToken(){
        return ResponseEntity.ok(jwtUtil.generateToken("JohnDoe"));
    }
}
