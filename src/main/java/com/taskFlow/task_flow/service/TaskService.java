package com.taskFlow.task_flow.service;

import com.taskFlow.task_flow.model.Tasks;
import com.taskFlow.task_flow.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    TaskRepo repo;

    public List<Tasks> getTasks(){
        return repo.findAll();
    }
    public Tasks createTask(Tasks t){
        return repo.save(t);
    }
    public void deleteTask(int taskId){
        repo.deleteById(taskId);
    }
    public Tasks getTaskById(int taskId){
        return repo.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Resource Not Found for " + taskId));
    }
    public Tasks updateTaskStatus(int taskId, String newStatus){
        Tasks toUpdate = getTaskById(taskId);
        toUpdate.setStatus(newStatus);
        return repo.save(toUpdate);
    }
    public Tasks updateTask(int taskId, Tasks toGetUpdatedTask){
        Tasks toUpdate = getTaskById(taskId);
            toUpdate.setProjectId(toGetUpdatedTask.getProjectId());
            toUpdate.setStatus(toGetUpdatedTask.getStatus());
            toUpdate.setAssignedToUserId(toGetUpdatedTask.getAssignedToUserId());
            toUpdate.setTitle(toGetUpdatedTask.getTitle());
            toUpdate.setDescription(toGetUpdatedTask.getDescription());
            toUpdate.setPriority(toGetUpdatedTask.getPriority());
        return repo.save(toUpdate);
    }
}
