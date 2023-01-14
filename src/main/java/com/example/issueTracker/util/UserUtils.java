package com.example.issueTracker.util;

import com.example.issueTracker.model.User;
import com.example.issueTracker.security.SecurityUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
public class UserUtils {

    public static User getUserFromPrincipal(Principal principal, UserDetailsService userService) {
        return (User) userService.loadUserByUsername(principal.getName());
    }
}
