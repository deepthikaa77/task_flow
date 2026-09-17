package com.taskFlow.task_flow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.taskFlow.task_flow.model.Users;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer>{
    Optional<Users> findByEmail(String email);
}
