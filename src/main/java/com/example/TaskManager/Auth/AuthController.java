package com.example.TaskManager.Auth;

import com.example.TaskManager.Security.JwtUtil;
import com.example.TaskManager.User.UserRequestDTO;
import com.example.TaskManager.User.UserResponseDTO;
import com.example.TaskManager.User.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Auth controller that handles user registration and login
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserService userService, PasswordEncoder passwordEncoder, AuthService authService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRequestDTO userRequest) {
        // Encode the password before saving to the database
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        // Save the user
        UserResponseDTO savedUser = userService.createUser(userRequest);

        // Generate a JWT token for the new user
        String token = jwtUtil.generateToken(savedUser.getUsername());

        return ResponseEntity.ok(Map.of("message", "User registered successfully", "token", token));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequestDTO loginRequest) {
        String token = authService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(Map.of("token", token));
    }
}
