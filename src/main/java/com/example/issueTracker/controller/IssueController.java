package com.example.issueTracker.controller;

import com.example.issueTracker.exceptions.UnauthorizedException;
import com.example.issueTracker.model.Issue;
import com.example.issueTracker.service.IssueService;
import com.example.issueTracker.util.IssueUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class IssueController {
    private final IssueService issueService;


    @GetMapping(value = "/issue/{id}")
    public String viewIssue(@PathVariable("id") Long id, Principal principal, ModelMap model) {
        Issue issue = issueService.getIssue(id);
        if (!IssueUtils.isUserIssueOwner(issue, principal)) {
            throw new UnauthorizedException("You are not authorized to view this issue");
        }

        model.addAttribute("issue", issue);
        return "issue";
    }


    @GetMapping(value = "/issue/{id}/edit")
    public String editIssue(@PathVariable("id") Long id, Principal principal, ModelMap model) {
        Issue issue = issueService.getIssue(id);
        if (!IssueUtils.isUserIssueOwner(issue, principal)) {
            throw new UnauthorizedException("You are not authorized to edit this issue");
        }
        System.out.println(issue.getBoard());
        model.addAttribute("issue", issue);
        return "edit_issue";
    }


    @PostMapping(value = "/issue/edit")
    public String saveModifiedIssue(@ModelAttribute("issue") Issue issue, BindingResult bindingResult, Principal principal) {
        System.out.println(issue.getBoard());
        if (!IssueUtils.isUserIssueOwner(issue, principal)) {
            throw new UnauthorizedException("You are not authorized to edit this issue");
        }

        Issue savedIssue = issueService.createIssue(issue);
        return "redirect:/issue/" + savedIssue.getIssueId();
    }
}
