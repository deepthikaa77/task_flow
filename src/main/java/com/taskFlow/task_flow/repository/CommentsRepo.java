package com.taskFlow.task_flow.repository;

import com.taskFlow.task_flow.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepo extends JpaRepository<Comments, Integer>{
    List<Comments> findByTaskId(Integer taskId);
}
