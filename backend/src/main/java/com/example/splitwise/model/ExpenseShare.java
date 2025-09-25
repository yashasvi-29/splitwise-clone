package com.example.splitwise.model;

import jakarta.persistence.*;

@Entity
@Table(name = "expense_shares")
public class ExpenseShare {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Expense expense;

    @ManyToOne
    private User user;

    private Double amount;

    private Boolean settled = false;

    public ExpenseShare(){}
    public ExpenseShare(Expense e, User u, Double amount){
        this.expense = e; this.user = u; this.amount = amount;
    }

    // Getters & setters
    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}
    public Expense getExpense(){return expense;}
    public void setExpense(Expense e){this.expense=e;}
    public User getUser(){return user;}
    public void setUser(User u){this.user=u;}
    public Double getAmount(){return amount;}
    public void setAmount(Double a){this.amount=a;}
    public Boolean getSettled(){return settled;}
    public void setSettled(Boolean s){this.settled=s;}
}
