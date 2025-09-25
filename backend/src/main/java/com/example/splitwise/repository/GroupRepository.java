package com.example.splitwise.repository;

import com.example.splitwise.model.ExpenseGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<ExpenseGroup, Long> {
}
