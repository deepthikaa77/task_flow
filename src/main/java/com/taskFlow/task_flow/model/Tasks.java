package com.taskFlow.task_flow.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer taskId;

    Integer projectId;
    Integer assignedToUserId;
    String title;
    String description;
    String status; //pending, in_progress, completed
    Integer priority;
}
