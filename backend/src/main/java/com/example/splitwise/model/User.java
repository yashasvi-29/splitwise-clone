package com.example.splitwise.model;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true, nullable=false)
    private String username;
    @Column(unique=true, nullable=false)
    private String email;
    @Column(nullable=false)
    private String password;

    // groups this user is a member of
    @ManyToMany(mappedBy = "members")
    private Set<ExpenseGroup> groups = new HashSet<>();

    public User() {}
    public User(String username, String email, String password) {
        this.username = username; this.email = email; this.password = password;
    }

    // Getters and setters...
    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}
    public String getUsername(){return username;}
    public void setUsername(String u){this.username=u;}
    public String getEmail(){return email;}
    public void setEmail(String e){this.email=e;}
    public String getPassword(){return password;}
    public void setPassword(String p){this.password=p;}
    public Set<ExpenseGroup> getGroups(){return groups;}
    public void setGroups(Set<ExpenseGroup> g){this.groups=g;}
}
