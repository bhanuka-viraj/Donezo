package com.donezo.tasks.mapper;

import com.donezo.tasks.domain.dto.TaskDto;
import com.donezo.tasks.domain.entities.Task;

public interface TaskMapper {
    Task fromDto(TaskDto dto);
    TaskDto toDto (Task task);
}
