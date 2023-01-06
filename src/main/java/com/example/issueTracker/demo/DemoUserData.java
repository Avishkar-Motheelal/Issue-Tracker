package com.example.issueTracker.demo;

import com.example.issueTracker.model.Board;
import com.example.issueTracker.model.Issue;
import com.example.issueTracker.model.User;
import com.example.issueTracker.security.SecurityUserDetailsService;
import com.example.issueTracker.service.BoardService;
import com.example.issueTracker.service.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class DemoUserData {

    private final SecurityUserDetailsService userDetailsManager;
    private final PasswordEncoder passwordEncoder;
    private final BoardService boardService;
    private final IssueService issueService;


    @EventListener
    public void loadDemoUser(ApplicationReadyEvent event) {
        User user = new User();
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setEnabled(true);
        userDetailsManager.createUser(user);

        Board board = new Board();
        board.setName("Boardname");
        board.setUser(user);
        boardService.createBoard(board);

        Issue issue = new Issue();
        issue.setBoard(board);
        issue.setTitle("Test title");
        issue.setDetails("A random bunch of demo detail text");
        board.addIssue(issue);
        boardService.createBoard(board);
    }
}
