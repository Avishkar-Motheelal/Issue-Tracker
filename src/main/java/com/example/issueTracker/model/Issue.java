package com.example.issueTracker.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "issues")
@NoArgsConstructor
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long issueId;

    @ManyToOne
    @JoinColumn(name = "boardId")
    private Board board;

    private String title;

    private String details;


    public Long getIssueId() {
        return issueId;
    }


    public void setIssueId(Long issueId) {
        this.issueId = issueId;
    }


    public Board getBoard() {
        return board;
    }


    public void setBoard(Board board) {
        this.board = board;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getDetails() {
        return details;
    }


    public void setDetails(String details) {
        this.details = details;
    }
}
