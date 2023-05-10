package com.project.rcstore.controller;

import com.project.rcstore.exception.InformationExistException;
import com.project.rcstore.model.User;
import com.project.rcstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth/users/")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // accepting userObject
    // http://localhost:8080/auth/users/register
    @PostMapping(path = "/register/")
    public User createUser(@RequestBody User userObject) {
        if (!userRepository.existsByEmailAddress(userObject.getEmailAddress())) {
            return userRepository.save(userObject);
        } else {
            throw new InformationExistException("User email already exist");
        }
    }

    // complete after JWT token added
    // http://localhost:8080/auth/users/login
    // @PostMapping(path = "/login/")
}
