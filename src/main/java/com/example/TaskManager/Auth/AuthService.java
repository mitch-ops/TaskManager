package com.example.TaskManager.Auth;

import com.example.TaskManager.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Auth service that will generate JTW tokens and authenticate a user
 */
@Service
public class AuthService {
//    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public String authenticate(String username, String password) {
        System.out.println("Starting authentication for user: " + username);

        // 🔍 Load the user manually for debugging
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        System.out.println("Stored Hashed Password: " + userDetails.getPassword());
        System.out.println("Entered Password: " + password);
        System.out.println("Password Matches: " + passwordEncoder.matches(password, userDetails.getPassword()));

        // 🔍 Make sure passwords match
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            System.out.println("Authentication successful!");
        } catch (Exception e) {
            System.out.println("Authentication failed: " + e.getMessage());
            return null; // Or throw an exception
        }

        System.out.println("🔹 Loaded UserDetails: " + authentication.getPrincipal());

        String token = jwtUtil.generateToken(userDetails.getUsername());

        return token;
    }



}
