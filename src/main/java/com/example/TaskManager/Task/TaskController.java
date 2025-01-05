package com.example.TaskManager.Task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Endpoint for creating a task
     *
     */
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task savedTask = taskService.saveTask(task);
        return ResponseEntity.ok(savedTask);
    }

    /**
     * Endpoint for getting a list of tasks assigned to
     */
    @GetMapping("assigned/{userId}/")
    public ResponseEntity<List<Task>> getTasksAssignedTo(@PathVariable UUID userId) {
        List<Task> taskList = taskService.getTasksAssignedTo(userId);

        return ResponseEntity.ok(taskList);
    }

    /**
     * Endpoint for getting a task by task id
     */
    @GetMapping("{id}/")
    public ResponseEntity<Task> getTaskByTaskId(@PathVariable UUID taskId) {
        Optional<Task> task = taskService.getTaskById(taskId);

        return task.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()); // build or TaskNotFoundException ?>
    }
}
