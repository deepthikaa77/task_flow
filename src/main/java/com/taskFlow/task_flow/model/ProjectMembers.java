package com.taskFlow.task_flow.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMembers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Integer projectMemberId;
    Integer userId;
    Integer projectId;

    @Enumerated(EnumType.STRING)
    Role userRole;
}
