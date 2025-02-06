package com.example.TaskManager.Task;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * This class is for receiving data from the api
 * It will contain data from the entire task entity
 * We still use a DTO because
 * 1. it avoid breaking changes to the database
 * 2. more flexibility/stability
 * 3. allows custom api responses
 */
public class TaskResponseDTO {

    private UUID id;
    private String title;
    private String description;
    private String priority;
    private String status;
    private LocalDateTime deadline;

    private UUID assignedTo; // The ID of the assigned user
    private String assignedToUsername; // The username of the assigned user

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructors
    public TaskResponseDTO() {}

    public TaskResponseDTO(UUID id, String title, String description, String priority, String status, LocalDateTime deadline, UUID assignedTo, String assignedToUsername, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.deadline = deadline;
        this.assignedTo = assignedTo;
        this.assignedToUsername = assignedToUsername;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public UUID getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(UUID assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getAssignedToUsername() {
        return assignedToUsername;
    }

    public void setAssignedToUsername(String assignedToUsername) {
        this.assignedToUsername = assignedToUsername;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
