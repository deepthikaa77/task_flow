package com.taskFlow.task_flow.service;

import com.taskFlow.task_flow.model.Comments;
import com.taskFlow.task_flow.repository.CommentsRepo;
import com.taskFlow.task_flow.repository.TaskRepo;
import com.taskFlow.task_flow.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.Comment;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentsRepo commentsRepo;

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private UserRepo userRepo;

    public List<Comments> getAllComments(){
        return commentsRepo.findAll();
    }
    public Comments addComment(Comments comment) {
        if (comment.getContent() == null || comment.getContent().isBlank()) {
            throw new IllegalArgumentException("Comment content cannot be empty");
        }

        if (comment.getTaskId() == null || !taskRepo.existsById(comment.getTaskId())) {
            throw new RuntimeException("Task not found with ID: " + comment.getTaskId());
        }

        if (comment.getUserId() == null || !userRepo.existsById(comment.getUserId())) {
            throw new RuntimeException("User not found with ID: " + comment.getUserId());
        }

        return commentsRepo.save(comment);
    }

    public List<Comments> getCommentsByTaskId(Integer taskId) {
        if (!taskRepo.existsById(taskId)) {
            throw new RuntimeException("Task not found with ID: " + taskId);
        }
        return commentsRepo.findByTaskId(taskId);
    }

    public Comments getCommentById(Integer commentId) {
        return commentsRepo.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found with ID: " + commentId));
    }

    public Comments updateComment(Integer commentId, String updatedContent) {
        if (updatedContent == null || updatedContent.isBlank()) {
            throw new IllegalArgumentException("Comment content cannot be empty");
        }

        Comments comment = getCommentById(commentId);
        comment.setContent(updatedContent);
        return commentsRepo.save(comment);
    }

    public void deleteComment(Integer commentId) {
        Comments comment = getCommentById(commentId);
        commentsRepo.delete(comment);
    }
}
