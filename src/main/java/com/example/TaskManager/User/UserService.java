package com.example.TaskManager.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

/**
 * Business layer
 * Componenet is a bean
 */
@Component
public class UserService {

    // Autowired means we don't have to instantiate, and we use the one already made.
    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
