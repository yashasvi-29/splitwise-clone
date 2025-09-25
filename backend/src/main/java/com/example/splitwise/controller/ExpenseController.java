package com.example.splitwise.controller;

import com.example.splitwise.model.*;
import com.example.splitwise.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> createExpense(@RequestBody Map<String,Object> body){
        String description = (String) body.get("description");
        Number amountN = (Number) body.get("amount");
        Double amount = amountN.doubleValue();
        Integer payerId = (Integer) body.get("payerId");
        Integer groupId = (Integer) body.get("groupId");
        List<Map<String, Object>> splits = (List<Map<String, Object>>) body.get("splits"); // [{userId:1, amount:12.5}, ...]

        Optional<User> payer = userRepository.findById(Long.valueOf(payerId));
        Optional<ExpenseGroup> group = groupRepository.findById(Long.valueOf(groupId));
        if(payer.isEmpty() || group.isEmpty()) return ResponseEntity.badRequest().body(Map.of("message","Invalid payer or group"));

        Expense e = new Expense(description, amount, payer.get(), group.get());
        // create shares
        for(Map<String,Object> s : splits){
            Integer uid = (Integer) s.get("userId");
            Number amtN = (Number) s.get("amount");
            Double shareAmt = amtN.doubleValue();
            Optional<User> u = userRepository.findById(Long.valueOf(uid));
            if(u.isPresent()){
                ExpenseShare share = new ExpenseShare();
                share.setExpense(e);
                share.setUser(u.get());
                share.setAmount(shareAmt);
                e.getShares().add(share);
            }
        }
        expenseRepository.save(e);
        return ResponseEntity.ok(e);
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<?> expensesByGroup(@PathVariable Long groupId){
        return ResponseEntity.ok(expenseRepository.findByGroupId(groupId));
    }
}
