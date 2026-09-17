package com.taskFlow.task_flow.service;

import com.taskFlow.task_flow.model.ProjectMembers;
import com.taskFlow.task_flow.model.Projects;
import com.taskFlow.task_flow.model.Role;
import com.taskFlow.task_flow.repository.ProjectMembersRepo;
import com.taskFlow.task_flow.repository.ProjectRepo;
import com.taskFlow.task_flow.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ProjectMembersRepo projectMembersRepo;

    @Transactional
    public Projects createProject(Projects project) {
        if (project.getProjectName() == null || project.getProjectName().isBlank()) {
            throw new IllegalArgumentException("Project name cannot be empty");
        }

        if (project.getCreatedByUserId() == null) {
            throw new IllegalArgumentException("Creator user ID is required");
        }

        if (!userRepo.existsById(project.getCreatedByUserId())) {
            throw new RuntimeException("User not found with ID: " + project.getCreatedByUserId());
        }

        // 1. Save project to generate the projectId
        Projects savedProject = projectRepo.save(project);

        // 2. Automatically link creator as OWNER in project_members
        ProjectMembers owner = new ProjectMembers();
        owner.setProjectId(savedProject.getProjectId());
        owner.setUserId(savedProject.getCreatedByUserId());
        owner.setUserRole(Role.OWNER);

        projectMembersRepo.save(owner);

        return savedProject;
    }

    public Projects getProjectById(Integer projectId) {
        return projectRepo.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with ID: " + projectId));
    }

    public List<Projects> getAllProjects() {
        return projectRepo.findAll();
    }

    public List<Projects> getProjectsByCreator(Integer userId) {
        return projectRepo.findByCreatedByUserId(userId);
    }

    public Projects updateProject(Integer projectId, Projects updatedData) {
        Projects existingProject = getProjectById(projectId);

        if (updatedData.getProjectName() != null && !updatedData.getProjectName().isBlank()) {
            existingProject.setProjectName(updatedData.getProjectName());
        }

        if (updatedData.getDescription() != null) {
            existingProject.setDescription(updatedData.getDescription());
        }

        return projectRepo.save(existingProject);
    }

    public void deleteProject(Integer projectId) {
        Projects existingProject = getProjectById(projectId);
        projectRepo.delete(existingProject);
    }
}
