package com.toddie.tasks.mapper;

import com.toddie.tasks.domain.dto.TaskListDto;
import com.toddie.tasks.domain.entities.TaskList;

public interface TaskListMapper {
    TaskList fromDto(TaskListDto dto);

    TaskListDto toDto(TaskList taskList);
}
