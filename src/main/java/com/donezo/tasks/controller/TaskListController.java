package com.donezo.tasks.controller;

import com.donezo.tasks.domain.dto.TaskListDto;
import com.donezo.tasks.domain.entities.TaskList;
import com.donezo.tasks.mapper.TaskListMapper;
import com.donezo.tasks.service.TaskListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/task-lists")
@CrossOrigin("*")
public class TaskListController {

    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }

    @GetMapping
    public List<TaskListDto> listTaskLists() {
        List<TaskListDto> taskLists = taskListService.listTaskLists()
                .stream()
                .map(taskListMapper::toDto)
                .toList();

        return taskLists;
    }

    @PostMapping
    public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto) {
        TaskList taskList = taskListService.createTaskList(taskListMapper.fromDto(taskListDto));
        return taskListMapper.toDto(taskList);
    }

    @GetMapping("/{task-list-id}")
    public Optional<TaskListDto> getTaskList(@PathVariable("task-list-id") UUID id) {
        return taskListService.getTaskList(id).map(taskListMapper::toDto);
    }

    @PutMapping("/{task-list-id}")
    public TaskListDto updateTaskList(@PathVariable("task-list-id") UUID id, @RequestBody TaskListDto taskListDto) {
        TaskList updatedTaskList = taskListService.updateTaskList(id, taskListMapper.fromDto(taskListDto));
        return taskListMapper.toDto(updatedTaskList);
    }

    @DeleteMapping("/{task-list-id}")
    public void deleteTaskList(@PathVariable("task-list-id") UUID id) {
        taskListService.deleteTaskList(id);
    }
}
