package com.enigmacamp.jwt.controller;

import com.enigmacamp.jwt.exception.UnathorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(UrlMappings.COURSE_URL)
public class CourseController {
    @GetMapping
    public ResponseEntity getAllCourse() {
        return ResponseEntity.ok("Get all courses");
    }

    @GetMapping("/{id}")
    public ResponseEntity getCourseById(@PathVariable("id") String id) {
        return ResponseEntity.ok("Get course with id " + id);
    }
}
