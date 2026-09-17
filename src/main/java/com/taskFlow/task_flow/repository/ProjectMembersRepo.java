package com.taskFlow.task_flow.repository;

import com.taskFlow.task_flow.model.ProjectMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectMembersRepo extends JpaRepository<ProjectMembers, Integer> {
    List<ProjectMembers> findByProjectId(Integer projectId);
    List<ProjectMembers> findByUserId(Integer userId);
    Optional<ProjectMembers> findByProjectIdAndUserId(Integer projectId, Integer userId);
    boolean existsByProjectIdAndUserId(Integer projectId, Integer userId);
}
