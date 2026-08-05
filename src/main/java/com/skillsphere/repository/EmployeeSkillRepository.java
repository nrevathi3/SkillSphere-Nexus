package com.skillsphere.repository;

import com.skillsphere.entity.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployeeSkillRepository extends JpaRepository<EmployeeSkill, UUID> {

}