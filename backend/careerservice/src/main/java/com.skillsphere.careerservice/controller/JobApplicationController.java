package com.skillsphere.careerservice.controller;

import com.skillsphere.careerservice.dto.JobApplicationDTO;
import com.skillsphere.careerservice.service.JobApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/career/applications")
@CrossOrigin(origins = "http://localhost:4200")
public class JobApplicationController {

    private final JobApplicationService applicationService;

    public JobApplicationController(JobApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    public ResponseEntity<JobApplicationDTO> create(
            @RequestBody JobApplicationDTO dto) {

        return ResponseEntity.ok(applicationService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<JobApplicationDTO>> getAll() {

        return ResponseEntity.ok(applicationService.getAll());
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<JobApplicationDTO>> getByEmployee(
            @PathVariable UUID employeeId) {

        return ResponseEntity.ok(
                applicationService.getByEmployee(employeeId)
        );
    }

    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<JobApplicationDTO>> getByJob(
            @PathVariable UUID jobId) {

        return ResponseEntity.ok(
                applicationService.getByJob(jobId)
        );
    }
}