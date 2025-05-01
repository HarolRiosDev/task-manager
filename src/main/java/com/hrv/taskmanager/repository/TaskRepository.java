package com.hrv.taskmanager.repository;

import com.hrv.taskmanager.repository.entity.TaskEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<TaskEntity, UUID> {

    TaskEntity findByStatus();

    Page<TaskEntity> findByAssignedTo(UUID id, Pageable pageable);

    TaskEntity findByCreatedBy(UUID id);

}
