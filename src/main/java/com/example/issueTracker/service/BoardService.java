package com.example.issueTracker.service;

import com.example.issueTracker.model.Board;
import com.example.issueTracker.model.User;
import com.example.issueTracker.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;


    public Board getBoardByUser(User user) {
        return boardRepository.findByUser(user.getUsername()).orElseThrow(EntityNotFoundException::new);
    }


    public void createBoard(Board board) {
        boardRepository.save(board);
    }
}
