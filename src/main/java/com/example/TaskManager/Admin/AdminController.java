package com.example.TaskManager.Admin;

import com.example.TaskManager.User.Role;
import com.example.TaskManager.User.User;
import com.example.TaskManager.User.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class will use user repository
 * The current purpose is to allow an existing admin to promote users
 *
 * Currently to become an admin you run this SQL command:
 * UPDATE users SET role = 'ADMIN' WHERE username = 'adminUser';
 *
 * But in the future, whenever a user creates a group, they become an admin, but there
 * are no groups currently
 */
@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    private final UserRepository userRepository;

    public AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PutMapping("/promote/{username}")
    public ResponseEntity<?> promoteToAdmin(@PathVariable String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setRole(Role.ADMIN);
        userRepository.save(user);
        return ResponseEntity.ok("User promoted to admin!");
    }
}
