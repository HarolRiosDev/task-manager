package com.hrv.taskmanager.service;

import com.hrv.taskmanager.repository.TaskRepository;
import com.hrv.taskmanager.repository.UserRepository;
import com.hrv.taskmanager.service.mapper.TaskMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;
    @Mock
    private TaskMapper taskMapper;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TaskService taskService;

    public TaskServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Method to test  TaskService.create
     */
    @Test
    void createTest() {
      // Arrange
      // when(...).thenReturn(...);

      // Act
      // taskService.create(dto, userId);

      // Assert
      // assertEquals(...);
    }

    /**
     * Method to test TaskService.delete
     */
    @Test
    void deleteTest() {
      // Arrange
      // when(...).thenReturn(...);

      // Act
      // taskService.delete(taskId);

      // Assert
      // assertEquals(...);
    }

    /**
     * Method to test TaskService.getTaskByID
     */
    @Test
    void getTaskByIDTest() {
      // Arrange
      // when(...).thenReturn(...);

      // Act
      // taskService.getTaskByID(id);

      // Assert
      // assertEquals(...);
    }

    /**
     * Method to test TaskService.findList
     */
    @Test
    void findListTest() {
      // Arrange
      // when(...).thenReturn(...);

      // Act
      // taskService.findList(pageable, assignedTo);

      // Assert
      // assertEquals(...);
    }

    /**
     * Method to test TaskService.updateTask
     */
    @Test
    void updateTaskTest() {
      // Arrange
      // when(...).thenReturn(...);

      // Act
      // taskService.updateTask(dto, taskId);

      // Assert
      // assertEquals(...);
    }

}
