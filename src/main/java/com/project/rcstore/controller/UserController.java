package com.project.rcstore.controller;

import com.project.rcstore.model.User;
import com.project.rcstore.model.request.LoginRequest;
import com.project.rcstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth/users/")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    // http://localhost:8080/auth/users/register
    @PostMapping(path = "/register/")
    public User createUser(@RequestBody User userObject) {
        return userService.createUser(userObject);
    }


    // http://localhost:8080/auth/users/login
    @PostMapping(path = "/login/")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        return userService.loginUser(loginRequest);
    }

}
