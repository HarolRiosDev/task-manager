package com.hrv.taskmanager.web.rest;

import com.hrv.taskmanager.service.JwtService;
import com.hrv.taskmanager.service.TaskService;
import com.hrv.taskmanager.service.mapper.TaskMapper;
import com.hrv.taskmanager.service.util.OrderValues;
import com.hrv.taskmanager.service.util.Validation;
import com.hrv.taskmanager.web.rest.api.TaskManagementAPI;
import com.hrv.taskmanager.web.rest.dto.NewTaskDTO;
import com.hrv.taskmanager.web.rest.dto.TaskDTO;
import com.hrv.taskmanager.web.rest.dto.TaskListResponseDTO;
import com.hrv.taskmanager.web.rest.dto.UpdateTaskDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TaskManagementController implements TaskManagementAPI {

    private final JwtService jwtService;

    private final TaskService taskService;

    private final TaskMapper taskMapper;

    @Override
    public ResponseEntity<Void> createTask(String authorization, NewTaskDTO newTaskDTO) {
        jwtService.validateAndVerifyToken(authorization);
        var userId = jwtService.extractUserId(authorization);
        taskService.create(newTaskDTO, userId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteTask(String authorization, UUID taskId) {
        jwtService.validateAndVerifyToken(authorization);
        taskService.delete(taskId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<TaskDTO> getTask(String authorization, UUID taskId) {
        jwtService.validateAndVerifyToken(authorization);
        var response = taskService.getTaskByID(taskId);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<TaskListResponseDTO> getTaskList(String authorization, Integer page, Integer size, String sort, UUID assignedTo) {
        jwtService.validateAndVerifyToken(authorization);
        Validation.validateSort(sort, OrderValues.taskOrderBy);
        var pageable = taskMapper.pageRequest(page, size, sort);
        var response = taskService.findList(pageable, assignedTo);
        return ResponseEntity.ok(taskMapper.toTaskListResponseDTO(response));
    }

    @Override
    public ResponseEntity<TaskDTO> updateTask(String authorization, UUID taskId, UpdateTaskDTO updateTaskDTO) {
        jwtService.validateAndVerifyToken(authorization);
        var response = taskService.updateTask(updateTaskDTO, taskId);
        return ResponseEntity.ok(response);
    }
}
