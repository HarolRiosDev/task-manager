package com.hrv.taskmanager.service.mapper;

import com.hrv.taskmanager.repository.entity.TaskEntity;
import com.hrv.taskmanager.service.util.TaskStatus;
import com.hrv.taskmanager.web.rest.dto.NewTaskDTO;
import com.hrv.taskmanager.web.rest.dto.TaskDTO;
import com.hrv.taskmanager.web.rest.dto.TaskListResponseDTO;
import com.hrv.taskmanager.web.rest.dto.UpdateTaskDTO;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TaskMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "dueDate", source = "dueDate")
    @Mapping(target = "assignedTo", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDate.now())")
    @Mapping(target = "status",qualifiedByName = "setStatus",source = "status")
    void updateTaskFromDto(UpdateTaskDTO dto, @MappingTarget TaskEntity entity);

    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "status", expression = "java(TaskStatus.PENDING)")
    @Mapping(target = "dueDate", source = "dueDate")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDate.now())")
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDate.now())")
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "assignedTo", ignore = true )
    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
    TaskEntity newTask(NewTaskDTO dto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "dueDate", source = "dueDate")
    @Mapping(target = "assignedTo.id", source = "assignedTo.id")
    @Mapping(target = "assignedTo.name", source = "assignedTo.name")
    @Mapping(target = "assignedTo.surname", source = "assignedTo.surname")
    @Mapping(target = "assignedTo.email", source = "assignedTo.email")
    TaskDTO toDTO(TaskEntity entity);

    @Mapping(target = "tasks", source = "content")
    @Mapping(target = "metaData.currentPage", source = "number")
    @Mapping(target = "metaData.pageSize", source = "size")
    @Mapping(target = "metaData.totalItems", source = "numberOfElements")
    TaskListResponseDTO toTaskListResponseDTO(Page<TaskEntity> entityPage);

    /**
     * Method that return pageable needed to filter
     * @param page page number
     * @param size page size
     * @param sort sort value
     * @return pageable object type
     */
    default Pageable pageRequest(Integer page, Integer size, String sort) {
        if(!StringUtils.isEmpty(sort)){
            var property = sort.substring(1);
            var direction = sort.charAt(0) == '+' ? Sort.Direction.ASC : Sort.Direction.DESC;
            return  PageRequest.of(page, size, Sort.by(direction, property));
        }
        return  PageRequest.of(page, size, Sort.unsorted());
    }

    @Named("setStatus")
    default TaskStatus setStatus(UpdateTaskDTO.StatusEnum status){
        return TaskStatus.valueOf(status.name());
    }
}
