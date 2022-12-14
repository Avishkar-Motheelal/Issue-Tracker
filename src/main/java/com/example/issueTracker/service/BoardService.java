package com.example.issueTracker.service;

import com.example.issueTracker.model.Board;
import com.example.issueTracker.model.User;

import java.util.List;

public interface BoardService {

    List<Board> getBoardsByUser(User user);

    Board createBoard(Board board);

    Board getBoard(Long id);
}
