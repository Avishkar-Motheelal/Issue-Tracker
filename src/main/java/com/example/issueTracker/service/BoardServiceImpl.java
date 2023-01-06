package com.example.issueTracker.service;

import com.example.issueTracker.model.Board;
import com.example.issueTracker.model.User;
import com.example.issueTracker.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;


    @Override
    public Board getBoardByUser(User user) {
        return boardRepository.findByUser(user).orElseThrow(EntityNotFoundException::new);
    }


    @Override
    public void createBoard(Board board) {
        boardRepository.save(board);
    }
}
