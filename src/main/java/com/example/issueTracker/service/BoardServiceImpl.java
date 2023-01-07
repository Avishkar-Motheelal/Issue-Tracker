package com.example.issueTracker.service;

import com.example.issueTracker.exceptions.NotFoundException;
import com.example.issueTracker.model.Board;
import com.example.issueTracker.model.User;
import com.example.issueTracker.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;


    @Override
    public List<Board> getBoardsByUser(User user) {
        return boardRepository.findAllByUser(user);
    }


    @Override
    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }


    @Override
    public Board getBoard(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(() -> new NotFoundException("Board not found"));
    }
}
