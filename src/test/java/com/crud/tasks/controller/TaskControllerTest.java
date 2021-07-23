package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.http.RequestEntity.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TaskMapper taskMapper;
    @MockBean
    private DbService service;

    @Test
    void getEmptyTask() throws Exception {
        //Given
        List<TaskDto> taskDtoList = new ArrayList<>();
        when(taskMapper.mapToTaskDtoList(service.getAllTasks())).thenReturn(taskDtoList);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void getTasks() throws Exception {
        //Given
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(new TaskDto(1L, "testTitle", "testContent"));
        when(taskMapper.mapToTaskDtoList(service.getAllTasks())).thenReturn(taskDtoList);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is("1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("testTitle")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is("testContent")));
    }

    @Test
    void getTaskById() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(9L, "testTitle", "testContent");
        Task task = new Task(9L, "testTitle", "testContent");
        when(service.getTaskById(9L)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/task/getTaskById?taskId=9").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("testContent")));
    }

    @Test
    void createTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "title1", "content1");
        Task task = new Task(1L, "title1", "content1");
        when(taskMapper.mapToTask(ArgumentMatchers.any())).thenReturn(task);
        when(service.saveTask(task)).thenReturn(ArgumentMatchers.any());
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }


    @Test
    void testUpdateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "title1", "content1");
        Task task = new Task(1L, "title1", "content1");
        when(taskMapper.mapToTask(ArgumentMatchers.any())).thenReturn(task);
        when(service.saveTask(task)).thenReturn(ArgumentMatchers.any());
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                .put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("title1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("content1")));
    }

    @Test
    void deleteById() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "title1", "content1");
        Task task = new Task(1L, "title1", "content1");
        when(service.getTaskById(1L)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/v1/task/deleteTask?taskId=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}