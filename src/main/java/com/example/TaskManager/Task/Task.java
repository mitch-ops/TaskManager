package com.example.TaskManager.Task;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Task object
 */
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String taskTitle;

    private String taskDescription;

    @Column(nullable = false)
    private String priorityLevel;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private UUID createdBy;

    private UUID assignedTo;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Task() {

    }

    public Task(String taskTitle, UUID createdBy, String status, String priorityLevel) {
        this.taskTitle = taskTitle;
        this.createdBy = createdBy;
        this.status = status;
        this.priorityLevel = priorityLevel;
    }

    // Getters and setters
    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }




}
