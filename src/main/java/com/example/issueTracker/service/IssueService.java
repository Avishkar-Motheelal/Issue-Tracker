package com.example.issueTracker.service;

import com.example.issueTracker.model.Board;
import com.example.issueTracker.model.Issue;

import java.util.List;

public interface IssueService {
    public List<Issue> getAllIssues(Board board);

    public Issue createIssue(Issue issue);

    public Issue getIssue(Long id);
}
