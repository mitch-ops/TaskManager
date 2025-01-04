package com.example.TaskManager.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

/**
 * Business layer
 * Componenet is a bean
 * This contains all the business logic it can do with a user repostory
 * Most of thsese functions will be called from UserController from my understanding
 */
@Component
public class UserService {

    // Autowired means we don't have to instantiate, and we use the one already made.
    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
