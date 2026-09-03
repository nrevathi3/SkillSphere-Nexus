package com.skillsphere.repository;

import com.skillsphere.entity.LearningPath;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LearningPathRepository
        extends JpaRepository<LearningPath, UUID> {
}