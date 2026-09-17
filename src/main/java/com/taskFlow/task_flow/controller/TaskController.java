package com.taskFlow.task_flow.controller;

import com.taskFlow.task_flow.model.Tasks;
import com.taskFlow.task_flow.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tasks")
public class TaskController {
    @Autowired
    TaskService service;

    @GetMapping
    public List<Tasks> getTasks(){
        return service.getTasks();
    }
    @GetMapping("/{id}")
    public Tasks getTasksById(@PathVariable Integer taskId){
        return service.getTaskById(taskId);
    }
    @PostMapping
    public Tasks createTask(@RequestBody Tasks t){
        return service.createTask(t);
    }
    @PutMapping("/{id}")
    public Tasks updateTask(@PathVariable Integer taskId, @RequestBody Tasks t){
        return service.updateTask(taskId, t);
    }
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Integer taskId){
        service.deleteTask(taskId);
    }
}
