package com.toddie.tasks.service;


import com.toddie.tasks.domain.entities.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {
    List<Task> listTasks(UUID taskListId);
    Task createTask(UUID taskListId, Task task);
    Optional<Task> getTask(UUID taskListId, UUID id);
    Task updateTask(UUID taskListId, UUID taskId, Task task);
    void deleteTask (UUID taskListId, UUID taskId);
}