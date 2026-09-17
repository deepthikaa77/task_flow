package com.taskFlow.task_flow.service;

import com.taskFlow.task_flow.model.Users;
import com.taskFlow.task_flow.repository.UserRepo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    @Autowired
    UserRepo repo;

    public Users CreateUser(Users u){
        if(u.getEmail() == null || u.getEmail().isBlank()){
            throw new IllegalArgumentException("Email cannot be empty");
        }
        repo.findByEmail(u.getEmail()).ifPresent(existing ->{
            throw new IllegalArgumentException("Email is already registered: " + u.getEmail());
        });
        return repo.save(u);
    }

    public Users getUserById(Integer userId){
        return repo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    }

    public List<Users> getAllUsers(){
        return repo.findAll();
    }

    public Users updateUser(Integer userId, Users updatedUserData){
        Users existingUser = getUserById(userId);
        if(updatedUserData.getUsername() != null && !updatedUserData.getEmail().isBlank()){
            existingUser.setUsername(updatedUserData.getUsername());
        }
        if(updatedUserData.getEmail() != null && !updatedUserData.getUsername().isBlank()){
            repo.findByEmail(updatedUserData.getEmail()).ifPresent(u ->{
                if(!u.getUserId().equals(userId)){
                    throw new IllegalArgumentException("Email is already taken by another user");
                }
            });
            existingUser.setEmail(updatedUserData.getEmail());
        }
        return repo.save(existingUser);
    }
    public void deleteUser(Integer userId){
        Users userToDelete = getUserById(userId);
        repo.delete(userToDelete);
    }

}
