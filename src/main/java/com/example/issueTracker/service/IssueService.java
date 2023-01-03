package com.example.issueTracker.service;

import com.example.issueTracker.model.Board;
import com.example.issueTracker.model.Issue;
import com.example.issueTracker.repository.IssueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IssueService {
    private final IssueRepository issueRepository;


    public List<Issue> getAllIssues(Board board) {
        return issueRepository.findByBoardId(board.getId()).orElseThrow(EntityNotFoundException::new);
    }


    public void createIssue(Issue issue) {
        issueRepository.save(issue);
    }
}
