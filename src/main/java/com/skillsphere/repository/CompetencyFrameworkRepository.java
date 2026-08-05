package com.skillsphere.repository;

import com.skillsphere.entity.CompetencyFramework;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompetencyFrameworkRepository extends JpaRepository<CompetencyFramework, UUID> {
}