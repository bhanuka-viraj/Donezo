package com.toddie.tasks.mapper.impl;

import com.toddie.tasks.domain.dto.TaskListDto;
import com.toddie.tasks.domain.entities.Task;
import com.toddie.tasks.domain.entities.TaskList;
import com.toddie.tasks.domain.entities.TaskStatus;
import com.toddie.tasks.mapper.TaskListMapper;
import com.toddie.tasks.mapper.TaskMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TaskListMapperImpl implements TaskListMapper {

    private final TaskMapper taskMapper;

    public TaskListMapperImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskList fromDto(TaskListDto dto) {
        return TaskList.builder()
                .id(dto.id())
                .title(dto.title())
                .description(dto.description())
                .tasks(Optional.ofNullable(dto.tasks()).map(
                        tasks -> tasks.stream().map(taskMapper::fromDto).toList()
                ).orElse(null))
                .build();
    }

    @Override
    public TaskListDto toDto(TaskList taskList) {
        return new TaskListDto(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),
                Optional.ofNullable(taskList.getTasks())
                        .map(List::size)
                        .orElse(0),
                calculateProgress(taskList.getTasks()),
                Optional.ofNullable(taskList.getTasks())
                        .map(tasks -> tasks.stream().map(taskMapper::toDto).toList())
                        .orElse(null)

        );
    }

    private Double calculateProgress(List<Task> tasks) {
        if (tasks == null){
            return null;
        }
        long closedTasksCount = tasks.stream().filter(task -> task.getStatus() == TaskStatus.CLOSED).count();
        return (double) closedTasksCount / tasks.size();
    }
}
