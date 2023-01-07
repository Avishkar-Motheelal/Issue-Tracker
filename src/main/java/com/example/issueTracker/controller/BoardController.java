package com.example.issueTracker.controller;

import com.example.issueTracker.model.Board;
import com.example.issueTracker.model.User;
import com.example.issueTracker.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final UserDetailsService userService;


    @GetMapping(value = "/boards")
    public String getAllBoards(Principal principal, ModelMap model) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        List<Board> boards = boardService.getBoardsByUser(user);
        model.addAttribute("boards", boards);
        return "boards";
    }
}
