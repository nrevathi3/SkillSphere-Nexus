package com.skillsphere.careerservice.repository;

import com.skillsphere.careerservice.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JobApplicationRepository
        extends JpaRepository<JobApplication, UUID> {

    List<JobApplication> findByEmployeeId(UUID employeeId);

    List<JobApplication> findByJobId(UUID jobId);
}