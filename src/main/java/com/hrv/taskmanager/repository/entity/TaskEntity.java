package com.hrv.taskmanager.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * TaskEntity
 */
@Getter
@Setter
@Entity
@Table(name = "tasks")
public class TaskEntity {
    @Id
    private String id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    private String status;
    @Column(name = "created_at")
    private String createdAt;
    @Column(name = "updated_at")
    private String updatedAt;
    @Column(name = "due_date")
    private String dueDate;
    @Column(name = "assigned_to")
    private String assignedTo;
    @Column(name = "created_by")
    private String createdBy;
}
