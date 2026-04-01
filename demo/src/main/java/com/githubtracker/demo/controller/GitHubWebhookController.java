package com.githubtracker.demo.controller;

import com.githubtracker.demo.dto.GitHubPushDto;
import com.githubtracker.demo.service.GitHubWebhookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/github")
@RequiredArgsConstructor
public class GitHubWebhookController {
    private final GitHubWebhookService webhookService;
    @PostMapping("/webhook")
    public ResponseEntity<String> receivePush(
            @RequestBody GitHubPushDto payload) {
        webhookService.processPush(payload);
        return ResponseEntity.ok("Webhook processed!!");
    }
}

