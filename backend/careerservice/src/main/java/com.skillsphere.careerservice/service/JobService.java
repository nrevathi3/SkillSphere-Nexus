package com.skillsphere.careerservice.service;

import com.skillsphere.careerservice.dto.JobDTO;
import com.skillsphere.careerservice.entity.Job;
import com.skillsphere.careerservice.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public JobDTO create(JobDTO dto) {

        Job job = Job.builder()
                .title(dto.getTitle())
                .department(dto.getDepartment())
                .requiredSkills(dto.getRequiredSkills())
                .minimumExperience(dto.getMinimumExperience())
                .active(dto.getActive() != null ? dto.getActive() : true)
                .build();

        return toDTO(jobRepository.save(job));
    }

    public List<JobDTO> getAll() {
        return jobRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public List<JobDTO> getActiveJobs() {
        return jobRepository.findActiveJobs()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public List<JobDTO> getByDepartment(String department) {
        return jobRepository.findByDepartmentIgnoreCase(department)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public void delete(UUID id) {
        jobRepository.deleteById(id);
    }

    private JobDTO toDTO(Job job) {

        return JobDTO.builder()
                .jobId(job.getJobId())
                .title(job.getTitle())
                .department(job.getDepartment())
                .requiredSkills(job.getRequiredSkills())
                .minimumExperience(job.getMinimumExperience())
                .active(job.getActive())
                .build();
    }
}