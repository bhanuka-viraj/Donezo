package com.toddie.tasks.domain.dto;

import com.toddie.tasks.domain.entities.TaskPriority;
import com.toddie.tasks.domain.entities.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskPriority priority,
        TaskStatus status
) {

}
