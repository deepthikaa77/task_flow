package com.taskFlow.task_flow.controller;

import com.taskFlow.task_flow.model.ProjectMembers;
import com.taskFlow.task_flow.model.Projects;
import com.taskFlow.task_flow.service.ProjectMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/members")
public class ProjectMemberController {
    @Autowired
    private ProjectMemberService memberService;

    @GetMapping
    public ResponseEntity<List<ProjectMembers>> getAllMembers() {
        List<ProjectMembers> allMembers = memberService.getAllMembers();
        return ResponseEntity.ok(allMembers);
    }

    @PostMapping
    public ResponseEntity<ProjectMembers> addMember(@RequestBody ProjectMembers member) {
        ProjectMembers created = memberService.addMemberToProject(member);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<ProjectMembers>> getMembersByProject(@PathVariable Integer projectId) {
        List<ProjectMembers> members = memberService.getMembersByProjectId(projectId);
        return ResponseEntity.ok(members);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProjectMembers>> getProjectsByUser(@PathVariable Integer userId) {
        List<ProjectMembers> memberships = memberService.getProjectsByUserId(userId);
        return ResponseEntity.ok(memberships);
    }

    @DeleteMapping("/{projectMemberId}")
    public ResponseEntity<Void> removeMember(@PathVariable Integer projectMemberId) {
        memberService.removeMember(projectMemberId);
        return ResponseEntity.noContent().build();
    }
}
