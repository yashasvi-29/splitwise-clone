package com.example.splitwise.controller;

import com.example.splitwise.model.*;
import com.example.splitwise.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<ExpenseGroup> listGroups(){
        return groupRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> createGroup(@RequestBody Map<String,Object> body){
        String name = (String) body.get("name");
        Integer ownerId = (Integer) body.get("ownerId");
        Optional<User> owner = userRepository.findById(Long.valueOf(ownerId));
        if(owner.isEmpty()){
            return ResponseEntity.badRequest().body(Map.of("message","Owner not found"));
        }
        ExpenseGroup g = new ExpenseGroup();
        g.setName(name);
        g.setOwner(owner.get());
        // Add owner as member by default
        g.getMembers().add(owner.get());
        groupRepository.save(g);
        return ResponseEntity.ok(g);
    }

    @PostMapping("/{groupId}/addMember")
    public ResponseEntity<?> addMember(@PathVariable Long groupId, @RequestBody Map<String,Object> body){
        Integer userId = (Integer) body.get("userId");
        Optional<ExpenseGroup> g = groupRepository.findById(groupId);
        Optional<User> u = userRepository.findById(Long.valueOf(userId));
        if(g.isEmpty() || u.isEmpty()) return ResponseEntity.badRequest().body(Map.of("message","Not found"));
        g.get().getMembers().add(u.get());
        groupRepository.save(g.get());
        return ResponseEntity.ok(g.get());
    }
}
