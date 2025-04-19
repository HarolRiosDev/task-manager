package com.hrv.taskmanager.web.rest;

import com.hrv.taskmanager.service.JwtService;
import com.hrv.taskmanager.web.rest.api.TaskManagementAPI;
import com.hrv.taskmanager.web.rest.dto.NewTaskDTO;
import com.hrv.taskmanager.web.rest.dto.TaskDTO;
import com.hrv.taskmanager.web.rest.dto.TaskListResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TaskManagementController implements TaskManagementAPI {

    private final JwtService jwtService;

    @Override
    public ResponseEntity<TaskDTO> createTask(String authorization, NewTaskDTO newTaskDTO) {
        jwtService.validateAndVerifyToken(authorization);
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteTask(String authorization) {
        jwtService.validateAndVerifyToken(authorization);
        return null;
    }

    @Override
    public ResponseEntity<TaskDTO> getTask(String authorization) {
        jwtService.validateAndVerifyToken(authorization);
        return null;
    }

    @Override
    public ResponseEntity<TaskListResponseDTO> getTaskList(String authorization, Integer page, Integer size, String sort) {
        jwtService.validateAndVerifyToken(authorization);
        return null;
    }

    @Override
    public ResponseEntity<TaskDTO> updateTask(String authorization, TaskDTO taskDTO) {
        jwtService.validateAndVerifyToken(authorization);
        return null;
    }
}
