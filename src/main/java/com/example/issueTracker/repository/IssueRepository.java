package com.example.issueTracker.repository;

import com.example.issueTracker.model.Board;
import com.example.issueTracker.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
    Optional<List<Issue>> findByBoardId(long id);
}
