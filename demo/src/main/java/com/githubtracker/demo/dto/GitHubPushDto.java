package com.githubtracker.demo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class GitHubPushDto {
    private Pusher pusher;
    private List<CommitDto> commits;
    @Getter
    @Setter
    public static class Pusher {
        private String name;
    }
    @Getter
    @Setter
    public static class CommitDto {
        private String id;
        private String message;
    }
}
