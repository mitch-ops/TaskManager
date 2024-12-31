package com.example.TaskManager.User;

import jakarta.persistence.*;
import jdk.jfr.Unsigned;

import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //https://stackoverflow.com/questions/47676403/spring-generatedvalue-annotation-usage, Could also use a SquenceGeneartor
    private UUID id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    // A single role can have many users, since we are at the users table it is many to one
    // JoinColumn specifies the foreign key, which links to the roles table
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    public User() {

    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
