package com.example.issueTracker.util;

import com.example.issueTracker.model.Board;

import java.security.Principal;
import java.util.Objects;

public class BoardUtils {

    public static boolean isUserBoardOwner(Board board, Principal principal) {
        return Objects.equals(board.getUser().getUsername(), principal.getName());
    }
}
