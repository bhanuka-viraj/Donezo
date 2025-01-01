package com.toddie.tasks.mapper.impl;

import com.toddie.tasks.domain.dto.TaskDto;
import com.toddie.tasks.domain.entities.Task;
import com.toddie.tasks.mapper.TaskMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {
    @Override
    public Task fromDto(TaskDto dto) {
        return Task.builder()
                .id(dto.id())
                .title(dto.title())
                .dueDate(dto.dueDate())
                .description(dto.description())
                .status(dto.status())
                .priority(dto.priority())
                .build();
    }

    @Override
    public TaskDto toDto(Task task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getPriority(),
                task.getStatus()
        );
    }
}
