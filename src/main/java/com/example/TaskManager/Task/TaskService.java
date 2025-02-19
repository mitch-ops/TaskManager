package com.example.TaskManager.Task;

import com.example.TaskManager.User.User;
import com.example.TaskManager.User.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Business layer for Task
 */
@Service
public class TaskService {

    @Autowired
    private final TaskRepository taskRepository;

    @Autowired
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
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
        taskEntity.setTaskTitle(task.getTitle());
        taskEntity.setPriorityLevel(task.getPriority());
        taskEntity.setTaskDescription(task.getDescription());
        taskEntity.setStatus(task.getStatus());

        // Store the username directly instead of looking up UUID
        taskEntity.setAssignedTo(task.getAssignedTo());

        // Set createdBy from authenticated admin
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            User adminUser = userRepository.findByUsername(userDetails.getUsername())
                    .orElseThrow(() -> new EntityNotFoundException("Admin user not found"));
            taskEntity.setCreatedBy(adminUser.getId());
        }

        taskEntity.setCreatedAt(LocalDateTime.now());
        taskEntity.setUpdatedAt(LocalDateTime.now());

        // Save task
        Task savedTask = taskRepository.save(taskEntity);

        return toResponseDTO(savedTask);
    }


    public TaskResponseDTO getTaskById(String username) {
        Task task = taskRepository.findById(username).orElseThrow(() -> new EntityNotFoundException("Task not found"));
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
        dto.setAssignedTo(task.getAssignedTo());

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
     * @param username of a user
     * @return the list of tasks for that user
     */
    public List<TaskResponseDTO> getTasksAssignedTo(String username) {
        return taskRepository.findByAssignedTo(username)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}
