package com.example.TaskManager.User;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * Business layer
 * Componenet is a bean
 * This contains all the business logic it can do with a user repostory
 * Most of thsese functions will be called from UserController from my understanding
 */
@Service
public class UserService {

    // Autowired means we don't have to instantiate, and we use the one already made.
//    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    /**
     * Get a user by ID and return as DTO
     */
    public UserResponseDTO getUserById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        return toResponseDTO(user);
    }

    /**
     * Create a new user
     * @param userRequest
     * @return the response
     */
    public UserResponseDTO createUser(UserRequestDTO userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
//        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setPassword(userRequest.getPassword());

        // Assign role (default to "USER" if not provided)
        Role role = roleRepository.findByRoleName(userRequest.getRole())
                .orElseGet(() -> roleRepository.findByRoleName("USER")
                        .orElseThrow(() -> new RuntimeException("Default role 'USER' not found")));

        user.setRole(role);

        User savedUser = userRepository.save(user);
        return toResponseDTO(savedUser);
    }

    private UserResponseDTO toResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole() != null ? user.getRole().getRoleName() : "USER"); // Default to "User" role

        return dto;
    }

    /**
     * Authenticat: compare hashed password with user input
     * @param username
     * @param rawPassword
     * @return ture or false if it matched
     */
//    public boolean authenticate(String username, String rawPassword) {
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new EntityNotFoundException("User not found"));
//
//        return passwordEncoder.matches(rawPassword, user.getPassword());
//    }

    /**
     * This will prepopulate the roles table with each being a USER
     */
    @PostConstruct
    public void initializeRoles() {
        if (roleRepository.findByRoleName("USER").isEmpty()) {
            Role userRole = new Role("USER");
            userRole.setRoleName("USER");
            roleRepository.save(userRole);
        }
    }


}
