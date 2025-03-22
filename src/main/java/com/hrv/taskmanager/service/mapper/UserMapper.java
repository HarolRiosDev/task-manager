package com.hrv.taskmanager.service.mapper;

import com.hrv.taskmanager.repository.entity.UserEntity;
import com.hrv.taskmanager.web.rest.dto.RegisterRequestDTO;
import com.hrv.taskmanager.web.rest.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "email", source = "email" )
    @Mapping(target = "name", source = "name")
    @Mapping(target = "surname", source = "surname")
    @Mapping(target = "dueDate", expression = "java(java.time.LocalDate.now().plusDays(30))")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDate.now())")
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDate.now())")
    @Mapping(target = "role", expression = "java(com.hrv.taskmanager.service.util.Role.USER)")
    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
    UserEntity toUserEntity(RegisterRequestDTO requestDTO);

    @Mapping(target = "email", source = "email")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "surname", source = "surname")
    @Mapping(target = "id", ignore = true)
    UserDTO toUserDTO(UserEntity entity);
}
