package com.githubtracker.demo.service;

import com.githubtracker.demo.dto.GitHubPushDto;
import com.githubtracker.demo.entity.Author;
import com.githubtracker.demo.entity.CommitEntity;
import com.githubtracker.demo.repository.AuthorRepository;
import com.githubtracker.demo.repository.CommitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GitHubWebhookService {
    private final AuthorRepository authorRepository;
    private final CommitRepository commitRepository;
    private final SlackService slackService;
    public void processPush(GitHubPushDto dto) {
        String authorName = dto.getPusher().getName();
        Author author = authorRepository
                .findByName(authorName)
                .orElseGet(() -> {
                    Author a = new Author();
                    a.setName(authorName);
                    return authorRepository.save(a);
                });
        List<String> commitMessages = new ArrayList<>();
        for (GitHubPushDto.CommitDto commitDto : dto.getCommits()) {
            CommitEntity commit = new CommitEntity();
            commit.setCommitId(commitDto.getId());
            commit.setMessage(commitDto.getMessage());
            commit.setAuthor(author);
            commitRepository.save(commit);
            commitMessages.add(commitDto.getMessage());
        }
        slackService.sendMessage(authorName, commitMessages);
    }
}