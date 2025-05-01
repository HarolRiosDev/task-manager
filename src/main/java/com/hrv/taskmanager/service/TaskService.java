package com.hrv.taskmanager.service;

import com.hrv.taskmanager.repository.TaskRepository;
import com.hrv.taskmanager.repository.UserRepository;
import com.hrv.taskmanager.repository.entity.TaskEntity;
import com.hrv.taskmanager.repository.entity.UserEntity;
import com.hrv.taskmanager.service.mapper.TaskMapper;
import com.hrv.taskmanager.web.rest.dto.NewTaskDTO;
import com.hrv.taskmanager.web.rest.dto.TaskDTO;
import com.hrv.taskmanager.web.rest.dto.UpdateTaskDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    private final TaskMapper taskMapper;

    private final UserRepository userRepository;

    public void create(NewTaskDTO dto, UUID userId){
        var entity = taskMapper.newTask(dto);
        var user = findUser(userId);
        entity.setCreatedBy(user);
        taskRepository.save(entity);
    }

    public void delete(UUID taskId){
        var entity = findTask(taskId);
        taskRepository.delete(entity);
    }

    public TaskDTO getTaskByID(UUID id){
        var entity = findTask(id);
        return taskMapper.toDTO(entity);
    }


    public Page<TaskEntity> findList(Pageable pageable, UUID assignedTo){
        if(assignedTo != null){
            return taskRepository.findByAssignedTo(assignedTo, pageable);
        }
        return taskRepository.findAll(pageable);
    }

    public TaskDTO updateTask(UpdateTaskDTO dto, UUID taskId){
        var actualTask = findTask(taskId);
        taskMapper.updateTaskFromDto(dto, actualTask);
        if (dto.getAssignedTo() != null) {
            var assignedTo = findUser(dto.getAssignedTo());
            actualTask.setAssignedTo(assignedTo);
        }
        taskRepository.save(actualTask);
        return taskMapper.toDTO(actualTask);
    }

    private TaskEntity findTask(UUID id){
        var optional = taskRepository.findById(id);
        if(optional.isEmpty()){
            throw new EntityNotFoundException("Task not found");
        }
        return optional.get();
    }
    private UserEntity findUser(UUID userId){
        var user = userRepository.findById(userId);
        if(user.isEmpty()){
            throw new EntityNotFoundException("User not found");
        }
        return user.get();
    }
}
