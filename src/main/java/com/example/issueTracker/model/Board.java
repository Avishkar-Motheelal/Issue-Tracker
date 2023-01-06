package com.example.issueTracker.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "boards")
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    @OneToMany(mappedBy = "board",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    private List<Issue> issues = new ArrayList<>();

    private String name;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }


    public List<Issue> getIssues() {
        return issues;
    }


    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void addIssue(Issue issue) {
        issues.add(issue);
    }
}
