package com.example.issueTracker.service;

import com.example.issueTracker.model.Board;
import com.example.issueTracker.model.User;

public interface BoardService {

    public Board getBoardByUser(User user);

    public void createBoard(Board board);
}
