package com.kalyon.pvportalbackend.controller;

import com.kalyon.pvportalbackend.model.Users;
import com.kalyon.pvportalbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/test")
@CrossOrigin("http://localhost:4200")
public class TestController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Users>> getUsers(){
        return ResponseEntity.ok(userRepository.findAll());
    }
}
