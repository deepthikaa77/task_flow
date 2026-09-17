package com.taskFlow.task_flow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.taskFlow.task_flow.model.Tasks;

import java.util.Optional;

@Repository
public interface TaskRepo extends JpaRepository<Tasks, Integer>{
    Optional<Tasks> findByProjectId(Integer projectId);
    Optional<Tasks> findByAssignedToUserId(Integer assignedToUserId);
}
