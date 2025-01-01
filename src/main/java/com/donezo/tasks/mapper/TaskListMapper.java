package com.donezo.tasks.mapper;

import com.donezo.tasks.domain.dto.TaskListDto;
import com.donezo.tasks.domain.entities.TaskList;

public interface TaskListMapper {
    TaskList fromDto(TaskListDto dto);

    TaskListDto toDto(TaskList taskList);
}
