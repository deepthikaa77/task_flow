package com.taskFlow.task_flow.controller;

import com.taskFlow.task_flow.model.Users;
import com.taskFlow.task_flow.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    UserService service;

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers(){
        List<Users> users = service.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable("id") Integer userId){
        Users user = service.getUserById(userId);
        return ResponseEntity.ok(user);
    }
    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users user){
        Users createdUser = service.CreateUser(user);
        return new ResponseEntity<Users>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable("id") Integer userId, @RequestBody Users user){
        Users updated = service.updateUser(userId, user);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer userId){
        service.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
