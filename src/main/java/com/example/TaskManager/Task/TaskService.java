package com.example.TaskManager.Task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public Optional<Task> getTaskById(UUID uuid) {
        return taskRepository.findById(uuid);
    }

    /**
     * This function saves a task into the database by using save from jpa repository
     * @param task to be saved
     * @return Task saved
     */
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    /**
     * This method gets a list of all tasks assinged to a user
     * @param uuid of a user
     * @return the list of tasks for that user
     */
    public List<Task> getTasksAssignedTo(UUID uuid) {
        return taskRepository.findByAssignedTo(uuid);
    }
}
