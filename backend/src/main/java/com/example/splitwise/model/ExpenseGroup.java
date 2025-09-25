package com.example.splitwise.model;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "expense_groups")
public class ExpenseGroup {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // owner/creator
    @ManyToOne
    private User owner;

    @ManyToMany
    @JoinTable(name = "group_members",
        joinColumns = @JoinColumn(name = "group_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> members = new HashSet<>();

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Expense> expenses = new ArrayList<>();

    public ExpenseGroup(){}

    public ExpenseGroup(String name, User owner){
        this.name=name; this.owner=owner;
    }

    // Getters & setters
    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}
    public String getName(){return name;}
    public void setName(String n){this.name=n;}
    public User getOwner(){return owner;}
    public void setOwner(User o){this.owner=o;}
    public Set<User> getMembers(){return members;}
    public void setMembers(Set<User> m){this.members=m;}
    public List<Expense> getExpenses(){return expenses;}
    public void setExpenses(List<Expense> e){this.expenses=e;}
}
