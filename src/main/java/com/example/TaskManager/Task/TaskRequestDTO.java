package com.example.TaskManager.Task;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * This class is for sending data to the api
 * so we don't send unncecessary data from
 * the full Task entity like created at.
 */
public class TaskRequestDTO {

    @NotBlank
    private String title;

    private String description;

    @NotBlank
    private String priority; // Enum: Low, Medium, High

    @NotBlank
    private String status; // Enum: To-Do, In Progress, Done

    private String assignedTo; // The ID of the user assigned to the task

    // Constructors
    public TaskRequestDTO() {}

    public TaskRequestDTO(String title, String description, String priority, String status, LocalDateTime deadline, String assignedTo) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.assignedTo = assignedTo;
    }

    // Getters and Setters
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

//    public LocalDateTime getDeadline() {
//        return deadline;
//    }
//
//    public void setDeadline(LocalDateTime deadline) {
//        this.deadline = deadline;
//    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }
}
