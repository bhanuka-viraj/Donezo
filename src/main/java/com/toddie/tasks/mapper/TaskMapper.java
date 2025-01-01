package com.toddie.tasks.mapper;

import com.toddie.tasks.domain.dto.TaskDto;
import com.toddie.tasks.domain.entities.Task;

public interface TaskMapper {
    Task fromDto(TaskDto dto);
    TaskDto toDto (Task task);
}
