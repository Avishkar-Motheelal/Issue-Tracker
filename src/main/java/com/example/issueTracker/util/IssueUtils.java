package com.example.issueTracker.util;

import com.example.issueTracker.model.Board;
import com.example.issueTracker.model.Issue;

import java.security.Principal;

public class IssueUtils {
    public static boolean isUserIssueOwner(Issue issue, Principal principal) {
        Board board = issue.getBoard();
        return BoardUtils.isUserBoardOwner(board, principal);
    }
}
