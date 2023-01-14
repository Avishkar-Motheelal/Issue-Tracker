package com.example.issueTracker.service;

import com.example.issueTracker.exceptions.NotFoundException;
import com.example.issueTracker.model.Board;
import com.example.issueTracker.model.Issue;
import com.example.issueTracker.repository.IssueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService {
    private final IssueRepository issueRepository;


    @Override
    public List<Issue> getAllIssues(Board board) {
        return issueRepository.findByBoardId(board.getId()).orElse(new ArrayList<>());
    }


    @Override
    public Issue createIssue(Issue issue) {
        return issueRepository.save(issue);
    }


    @Override
    public Issue getIssue(Long id) {
        return issueRepository.findById(id).orElseThrow(() -> new NotFoundException("No issue with the specified id was found"));
    }
}
