package com.example.splitwise.model;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "expenses")
public class Expense {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Double amount;

    @ManyToOne
    private User payer;

    @ManyToOne
    private ExpenseGroup group;

    @OneToMany(mappedBy = "expense", cascade = CascadeType.ALL)
    private List<ExpenseShare> shares = new ArrayList<>();

    public Expense(){}
    public Expense(String description, Double amount, User payer, ExpenseGroup group){
        this.description = description; this.amount = amount; this.payer = payer; this.group = group;
    }

    // Getters & setters
    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}
    public String getDescription(){return description;}
    public void setDescription(String d){this.description=d;}
    public Double getAmount(){return amount;}
    public void setAmount(Double a){this.amount=a;}
    public User getPayer(){return payer;}
    public void setPayer(User p){this.payer=p;}
    public ExpenseGroup getGroup(){return group;}
    public void setGroup(ExpenseGroup g){this.group=g;}
    public List<ExpenseShare> getShares(){return shares;}
    public void setShares(List<ExpenseShare> s){this.shares=s;}
}
