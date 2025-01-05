package com.example.TaskManager.User;

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
    @PostMapping // Defualt mapping is /api/users
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("{id}/")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        // look at database with that id
        Optional<User> user = userService.getUserById(id);
        // return the response code
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
