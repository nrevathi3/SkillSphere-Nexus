package com.skillsphere.careerservice.service;

import com.skillsphere.careerservice.dto.JobApplicationDTO;
import com.skillsphere.careerservice.entity.JobApplication;
import com.skillsphere.careerservice.repository.JobApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class JobApplicationService {

    private final JobApplicationRepository applicationRepository;

    public JobApplicationService(JobApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public JobApplicationDTO create(JobApplicationDTO dto) {

        JobApplication application = JobApplication.builder()
                .jobId(dto.getJobId())
                .jobTitle(dto.getJobTitle())
                .employeeName(dto.getEmployeeName())
                .employeeId(dto.getEmployeeId())
                .currentRole(dto.getCurrentRole())
                .yearsOfExperience(dto.getYearsOfExperience())
                .relevantSkills(dto.getRelevantSkills())
                .interestMessage(dto.getInterestMessage())
                .resumeFileName(dto.getResumeFileName())
                .status(JobApplication.ApplicationStatus.APPLIED)
                .build();

        return toDTO(applicationRepository.save(application));
    }

    public List<JobApplicationDTO> getAll() {

        return applicationRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public List<JobApplicationDTO> getByEmployee(UUID employeeId) {

        return applicationRepository.findByEmployeeId(employeeId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public List<JobApplicationDTO> getByJob(UUID jobId) {

        return applicationRepository.findByJobId(jobId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    private JobApplicationDTO toDTO(JobApplication application) {

        return JobApplicationDTO.builder()
                .applicationId(application.getApplicationId())
                .jobId(application.getJobId())
                .jobTitle(application.getJobTitle())
                .employeeName(application.getEmployeeName())
                .employeeId(application.getEmployeeId())
                .currentRole(application.getCurrentRole())
                .yearsOfExperience(application.getYearsOfExperience())
                .relevantSkills(application.getRelevantSkills())
                .interestMessage(application.getInterestMessage())
                .resumeFileName(application.getResumeFileName())
                .status(application.getStatus())
                .build();
    }
}