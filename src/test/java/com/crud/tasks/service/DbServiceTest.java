package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DbServiceTest {
    @Autowired
    DbService dbService;

    @Test
    void getAllTasks() {
        //Given
        Task task = new Task();
        task.setTitle("testTitle");
        task.setContent("testContent");
        //When
        dbService.saveTask(task);
        Task testTask = dbService.getTaskByTitle("testTitle");
        //Then
        Assertions.assertEquals(testTask.getTitle(), task.getTitle());
        //CleanUp
        dbService.deleteTask(testTask.getId());
    }

}