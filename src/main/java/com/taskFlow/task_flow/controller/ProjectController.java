package com.taskFlow.task_flow.controller;

import com.taskFlow.task_flow.model.Projects;
import com.taskFlow.task_flow.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity<Projects> createProject(@RequestBody Projects project) {
        Projects created = projectService.createProject(project);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Projects> getProjectById(@PathVariable Integer projectId) {
        Projects project = projectService.getProjectById(projectId);
        return ResponseEntity.ok(project);
    }

    @GetMapping
    public ResponseEntity<List<Projects>> getAllProjects() {
        List<Projects> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/creator/{userId}")
    public ResponseEntity<List<Projects>> getProjectsByCreator(@PathVariable Integer userId) {
        List<Projects> projects = projectService.getProjectsByCreator(userId);
        return ResponseEntity.ok(projects);
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<Projects> updateProject(@PathVariable Integer projectId, @RequestBody Projects project) {
        Projects updated = projectService.updateProject(projectId, project);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Projects> deleteProject(@PathVariable Integer projectId) {
        projectService.deleteProject(projectId);
        return ResponseEntity.noContent().build();
    }
}
