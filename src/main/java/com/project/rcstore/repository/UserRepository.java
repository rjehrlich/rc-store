package com.project.rcstore.repository;

import com.project.rcstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    //return if the user exists in the db or not
    boolean existsByEmailAddress(String emailAddress);

    //if I am able to successfully logged in, need a new User object
    User findUserByEmailAddress(String emailAddress);
}
