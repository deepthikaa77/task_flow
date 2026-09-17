package com.taskFlow.task_flow.controller;

import com.taskFlow.task_flow.model.Comments;
import com.taskFlow.task_flow.service.CommentService;
import org.hibernate.grammars.hql.HqlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("api/comment")
public class CommentController {
    @Autowired
    private CommentService commentsService;

    @GetMapping
    public List<Comments> getComments(){
        return commentsService.getAllComments();
    }

    @PostMapping
    public ResponseEntity<Comments> addComment(@RequestBody Comments comment) {
        Comments created = commentsService.addComment(comment);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<Comments>> getCommentsByTask(@PathVariable Integer taskId) {
        List<Comments> comments = commentsService.getCommentsByTaskId(taskId);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<Comments> getCommentById(@PathVariable Integer commentId) {
        Comments comment = commentsService.getCommentById(commentId);
        return ResponseEntity.ok(comment);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<Comments> updateComment(
            @PathVariable Integer commentId,
            @RequestBody Map<String, String> payload) {
        String content = payload.get("content");
        Comments updated = commentsService.updateComment(commentId, content);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer commentId) {
        commentsService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}
