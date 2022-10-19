package com.example.issueTracker.controller;

import com.example.issueTracker.model.User;
import com.example.issueTracker.security.SecurityUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private SecurityUserDetailsService userDetailsManager;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/register")
    public String register() {
        return "register";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @PostMapping(
        value = "/register",
        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
        MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public String addUser(@RequestParam Map<String, String> body) {
        User user = new User();
        user.setUsername(body.get("username"));
        user.setPassword(passwordEncoder.encode(body.get("password")));
        user.setEnabled(true);
        userDetailsManager.createUser(user);

        return "redirect:/login";
    }
}
