package com.taskFlow.task_flow.service;

import com.taskFlow.task_flow.model.ProjectMembers;
import com.taskFlow.task_flow.model.Role;
import com.taskFlow.task_flow.repository.ProjectMembersRepo;
import com.taskFlow.task_flow.repository.ProjectRepo;
import com.taskFlow.task_flow.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectMemberService {
    @Autowired
    private ProjectMembersRepo projectMembersRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ProjectRepo projectRepo;

    public List<ProjectMembers> getAllMembers() {
        return projectMembersRepo.findAll();
    }

    public ProjectMembers addMemberToProject(ProjectMembers member) {
        if (member.getProjectId() == null || member.getUserId() == null) {
            throw new IllegalArgumentException("Project ID and User ID are required");
        }

        if (!projectRepo.existsById(member.getProjectId())) {
            throw new RuntimeException("Project not found with ID: " + member.getProjectId());
        }

        if (!userRepo.existsById(member.getUserId())) {
            throw new RuntimeException("User not found with ID: " + member.getUserId());
        }

        if (projectMembersRepo.existsByProjectIdAndUserId(member.getProjectId(), member.getUserId())) {
            throw new IllegalArgumentException("User is already a member of this project");
        }

        // Default role if not supplied
        if (member.getUserRole() == null) {
            member.setUserRole(Role.DEVELOPER);
        }

        return projectMembersRepo.save(member);
    }

    public List<ProjectMembers> getMembersByProjectId(Integer projectId) {
        if (!projectRepo.existsById(projectId)) {
            throw new RuntimeException("Project not found with ID: " + projectId);
        }
        return projectMembersRepo.findByProjectId(projectId);
    }

    public List<ProjectMembers> getProjectsByUserId(Integer userId) {
        if (!userRepo.existsById(userId)) {
            throw new RuntimeException("User not found with ID: " + userId);
        }
        return projectMembersRepo.findByUserId(userId);
    }

    public void removeMember(Integer projectMemberId) {
        ProjectMembers member = projectMembersRepo.findById(projectMemberId)
                .orElseThrow(() -> new RuntimeException("Membership not found with ID: " + projectMemberId));
        projectMembersRepo.delete(member);
    }
}
