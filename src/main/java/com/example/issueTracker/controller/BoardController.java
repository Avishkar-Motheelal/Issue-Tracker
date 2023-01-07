package com.example.issueTracker.controller;

import com.example.issueTracker.exceptions.UnauthorizedException;
import com.example.issueTracker.model.Board;
import com.example.issueTracker.model.User;
import com.example.issueTracker.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final UserDetailsService userService;


    @GetMapping(value = "/boards")
    public String getAllBoards(Principal principal, ModelMap model) {
        User user = getUserFromPrincipal(principal);
        List<Board> boards = boardService.getBoardsByUser(user);
        model.addAttribute("boards", boards);
        return "boards";
    }


    @GetMapping(value = "/boards/{id}")
    public String viewBoard(@PathVariable("id") long id, Principal principal, ModelMap model) {
        Board board = boardService.getBoard(id);
        if (!Objects.equals(board.getUser().getUsername(), principal.getName())) {
            throw new UnauthorizedException("You are not authorized to view this board");
        }
        model.addAttribute("board", board);
        return "board";
    }


    @GetMapping(value = "/boards/create")
    public String createBoard(Model model) {
        model.addAttribute("board", new Board());
        return "create_board";
    }


    @PostMapping(value = "/boards/create")
    public String createBoard(@ModelAttribute("board") Board board, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return "create_board";
        }

        board.setUser(getUserFromPrincipal(principal));
        board = boardService.createBoard(board);
        return "redirect:/boards/" + board.getId();
    }


    private User getUserFromPrincipal(Principal principal) {
        return (User) userService.loadUserByUsername(principal.getName());
    }
}
