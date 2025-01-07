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
     * Spring data JPA automatically generates this query based on the naming convention
     * findBy indicates a query method
     * AssignedTo matches the assignedTo property of the Task entity
     * So we get the JPQL query:
     * SELECT t FROM Task t WHERE t.assignedTo = :userId
     * where t is the alias for the Task entity
     * @param userId
     * @return
     */
    List<Task> findByAssignedTo(UUID userId);
}
