package com.example.demo.security.presentation.controller;


import com.example.demo.security.application.UserService;
import com.example.demo.security.data.User;
import com.example.demo.security.presentation.dto.Registration;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void register(@Validated @RequestBody Registration registration) {
        this.userService.register(
                registration.username,
                registration.password,
                registration.firstName,
                registration.lastName
        );
    }
    @PostMapping("/loadByUsername")
    public User loadUserByUsername(String username){return userService.loadUserByUsername(username);}

}
