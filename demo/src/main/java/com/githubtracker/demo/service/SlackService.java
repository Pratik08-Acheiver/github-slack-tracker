package com.githubtracker.demo.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SlackService {
    @Value("${slack.webhook.url}")
    private String webhookUrl;
    private final RestTemplate restTemplate = new RestTemplate();
    public void sendMessage(String author, List<String> commitMessages) {
        StringBuilder text = new StringBuilder();
        text.append(" ").append(author)
                .append(" pushed code with commits:\n");
        for (String message : commitMessages) {
            text.append("• ").append(message).append("\n");
        }
        Map<String, String> body = new HashMap<>();
        body.put("text", text.toString());
        restTemplate.postForEntity(webhookUrl, body, String.class);
    }
}

