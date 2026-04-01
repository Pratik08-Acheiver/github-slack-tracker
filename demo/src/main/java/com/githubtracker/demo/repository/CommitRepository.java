package com.githubtracker.demo.repository;

import com.githubtracker.demo.entity.CommitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommitRepository extends JpaRepository<CommitEntity, Long> {
}
