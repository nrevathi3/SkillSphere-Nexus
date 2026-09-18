package com.skillsphere.careerservice.repository;

import com.skillsphere.careerservice.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface JobRepository extends JpaRepository<Job, UUID> {

    @Query("SELECT j FROM Job j WHERE j.active = true")
    List<Job> findActiveJobs();

    List<Job> findByDepartmentIgnoreCase(String department);
}