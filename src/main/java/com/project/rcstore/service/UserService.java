package com.project.rcstore.service;

import com.project.rcstore.exception.InformationExistException;
import com.project.rcstore.model.User;
import com.project.rcstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(@RequestBody User userObject) {
        if (!userRepository.existsByEmailAddress(userObject.getEmailAddress())) {
            return userRepository.save(userObject);
        } else {
            throw new InformationExistException("User email already exist");
        }
    }

    public User findUserByEmailAddress(String emailAddress) {
        return userRepository.findUserByEmailAddress(emailAddress);
    }

    // add loginUser once JWT
}
