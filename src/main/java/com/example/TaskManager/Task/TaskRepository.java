package com.example.TaskManager.Task;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Data access layer
 * contains methods from JpaRepository that can accses the database like findByID
 */
public interface TaskRepository extends JpaRepository<Task, UUID> {

    /**
     * Look through the Tasks table with this id and extract all tasks in assingedTo with this id
     * @param userId
     * @return
     */
    // TO-Do implement this method
    List<Task> findByAssignedTo(UUID userId);
}
