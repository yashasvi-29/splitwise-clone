package com.example.splitwise.repository;

import com.example.splitwise.model.ExpenseShare;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseShareRepository extends JpaRepository<ExpenseShare, Long> {
}
