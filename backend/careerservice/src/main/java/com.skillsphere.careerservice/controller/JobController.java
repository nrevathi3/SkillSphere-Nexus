package com.skillsphere.careerservice.controller;

import com.skillsphere.careerservice.dto.JobDTO;
import com.skillsphere.careerservice.service.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/career/jobs")
@CrossOrigin(origins = "http://localhost:4200")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    public ResponseEntity<JobDTO> create(
            @RequestBody JobDTO dto) {

        return ResponseEntity.ok(jobService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<JobDTO>> getAll() {

        return ResponseEntity.ok(jobService.getAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<JobDTO>> getActiveJobs() {

        return ResponseEntity.ok(jobService.getActiveJobs());
    }

    @GetMapping("/department/{department}")
    public ResponseEntity<List<JobDTO>> getByDepartment(
            @PathVariable String department) {

        return ResponseEntity.ok(
                jobService.getByDepartment(department)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID id) {

        jobService.delete(id);
        return ResponseEntity.noContent().build();
    }
}