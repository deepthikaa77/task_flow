package com.taskFlow.task_flow.repository;

import com.taskFlow.task_flow.model.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepo extends JpaRepository<Projects, Integer>{
    List<Projects> findByCreatedByUserId(Integer createdByUserId);
}
