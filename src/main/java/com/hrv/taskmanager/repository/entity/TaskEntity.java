package com.hrv.taskmanager.repository.entity;

import com.hrv.taskmanager.service.util.TaskStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

/**
 * TaskEntity
 */
@Getter
@Setter
@Entity
@Table(name = "tasks")
public class TaskEntity {
    @Id
    @Column(name = "id")
    private UUID id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    @Column(name = "created_at")
    private LocalDate createdAt;
    @Column(name = "updated_at")
    private LocalDate updatedAt;
    @Column(name = "due_date")
    private LocalDate dueDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_to")
    private UserEntity assignedTo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private UserEntity createdBy;
}
