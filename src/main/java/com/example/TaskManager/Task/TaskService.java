package com.example.TaskManager.Task;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Business layer for Task
 */
@Component
public class TaskService {

    @Autowired
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

//    public Optional<Task> getTaskById(UUID uuid) {
//        return taskRepository.findById(uuid);
//    }

    /**
     * This function saves a task into the database by using save from jpa repository
     * @param task to be saved
     * @return Task saved
     */
    public TaskResponseDTO createTask(TaskRequestDTO task) {
        // Convert DTO to entity
        Task taskEntity = new Task();
        // set all the attributes
        taskEntity.setTaskTitle(task.getTitle());
        taskEntity.setPriorityLevel(task.getPriority());
        taskEntity.setTaskDescription(task.getDescription());
        taskEntity.setStatus(task.getStatus());
        taskEntity.setAssignedTo(task.getAssignedTo());

        // Save to database
        Task savedTask = taskRepository.save(taskEntity);

        // Convert entity to DTO for response
        return toResponseDTO(savedTask);
    }

    public TaskResponseDTO getTaskById(UUID id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Task not found"));
        return toResponseDTO(task);
    }

    private TaskResponseDTO toResponseDTO(Task task) {
        TaskResponseDTO dto = new TaskResponseDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTaskTitle());
        dto.setPriority(task.getPriorityLevel());
        dto.setStatus(task.getStatus());
        dto.setDescription(task.getTaskDescription());
        dto.setCreatedAt(task.getCreatedAt());
        dto.setUpdatedAt(task.getUpdatedAt());

//        // Fetch user details via UserClient
//        if (task.getAssignedTo() != null) {
//            UserDTO user = userClient.getUserById(task.getAssignedTo());
//            dto.setAssignedTo(task.getAssignedTo());
//            dto.setAssignedToUsername(user.getUsername());
//        }

        return dto;
    }

    /**
     * This method gets a list of all tasks assinged to a user
     * @param uuid of a user
     * @return the list of tasks for that user
     */
    public List<TaskResponseDTO> getTasksAssignedTo(UUID uuid) {
        return taskRepository.findByAssignedTo(uuid)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}
