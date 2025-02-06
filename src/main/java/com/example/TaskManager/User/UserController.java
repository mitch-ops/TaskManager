package com.example.TaskManager.User;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

/**
 * Api layer
 */
@RestController
@RequestMapping(path = "/api/users/")
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // methods

    /**
     * Create a new user
     * @param userRequest
     * @return the respone user
     */
    @PostMapping // Defualt mapping is /api/users
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequest) {
        UserResponseDTO savedUser = userService.createUser(userRequest);
        return ResponseEntity.ok(savedUser);
    }

    /**
     * Get a user by ID
     * @param id
     * @return the response user
     */
    @GetMapping("{id}/")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable UUID id) {
        UserResponseDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
}
