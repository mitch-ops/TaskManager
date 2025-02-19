package com.example.TaskManager.Task;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Api layer for the Task service
 */
@RestController
@RequestMapping(path = "/api/tasks/")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Endpoint for creating a task
     * Only admins can assign tasks
     */
    @PostMapping("/assign")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TaskResponseDTO> createTask(@Valid @RequestBody TaskRequestDTO task) {
        TaskResponseDTO savedTask = taskService.createTask(task);
        return ResponseEntity.ok(savedTask);
    }

    /**
     * Endpoint for getting a list of tasks assigned to
     */
    @GetMapping("assigned/{userId}/")
    public ResponseEntity<List<TaskResponseDTO>> getTasksAssignedTo(@PathVariable UUID userId) {
        List<TaskResponseDTO> taskList = taskService.getTasksAssignedTo(userId);

        return ResponseEntity.ok(taskList);
    }

    /**
     * Endpoint for getting a task by task id
     */
    @GetMapping("{id}/")
    public ResponseEntity<TaskResponseDTO> getTaskByTaskId(@PathVariable UUID taskId) {
        TaskResponseDTO task = taskService.getTaskById(taskId);

        return ResponseEntity.ok(task);
    }
}
