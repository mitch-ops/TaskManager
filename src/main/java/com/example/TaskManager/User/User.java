package com.example.TaskManager.User;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public class User {

    private int id;
    private String username;
    private String password;
    private String email;

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
