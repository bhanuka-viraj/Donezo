package com.donezo.tasks.service.impl;

import com.donezo.tasks.domain.entities.Task;
import com.donezo.tasks.domain.entities.TaskList;
import com.donezo.tasks.domain.entities.TaskPriority;
import com.donezo.tasks.domain.entities.TaskStatus;
import com.donezo.tasks.repository.TaskListRepository;
import com.donezo.tasks.repository.TaskRepository;
import com.donezo.tasks.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<Task> listTasks(UUID taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }

    @Override
    public Task createTask(UUID taskListId, Task task) {
        if (task.getId() != null) {
            throw new IllegalArgumentException("Task already has an ID!");
        }

        if (task.getTitle() == null || task.getTitle().isBlank()) {
            throw new IllegalArgumentException("Task title must be present!");
        }

        TaskPriority taskPriority = Optional.ofNullable(task.getPriority()).orElse(TaskPriority.MEDIUM);
        TaskList taskList = taskListRepository.findById(taskListId).orElseThrow(() -> new IllegalArgumentException("Task list not found!"));
        LocalDateTime now = LocalDateTime.now();


        return taskRepository.save(Task.builder()
                        .title(task.getTitle())
                        .description(task.getDescription())
                        .dueDate(task.getDueDate())
                        .created(now)
                        .updated(now)
                        .status(TaskStatus.OPEN)
                        .priority(taskPriority)
                        .taskList(taskList)
                .build()
        );
    }

    @Override
    public Optional<Task> getTask(UUID taskListId, UUID id) {
        return taskRepository.findByTaskListIdAndId(taskListId, id);
    }

    @Override
    public Task updateTask(UUID taskListId, UUID taskId, Task task) {
        if (task.getId() == null) {
            throw new IllegalArgumentException("Task must have an ID!");
        }
        if (!Objects.equals(task.getId(), taskId)) {
            throw new IllegalArgumentException("Attempting to change task id, this is not permitted!");
        }

        if (task.getPriority() == null) {
            throw new IllegalArgumentException("Task must have a valid priority!");
        }
        if (task.getStatus() == null) {
            throw new IllegalArgumentException("Task must have a valid status!");
        }

        Task existingTask = taskRepository.findById(taskId).orElseThrow(() -> new IllegalStateException("Task not found!"));
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setUpdated(LocalDateTime.now());
        existingTask.setPriority(task.getPriority());
        existingTask.setStatus(task.getStatus());

        return taskRepository.save(existingTask);
    }

    @Override
    public void deleteTask(UUID taskListId, UUID taskId) {
        taskRepository.deleteByTaskListIdAndId(taskListId, taskId);
    }
}
