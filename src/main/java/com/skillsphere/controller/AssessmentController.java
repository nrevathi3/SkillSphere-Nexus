package com.skillsphere.controller;

import com.skillsphere.entity.Assessment;
import com.skillsphere.service.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/assessments")
public class AssessmentController {

    @Autowired
    private AssessmentService assessmentService;

    @PostMapping
    @PreAuthorize("hasRole('HR')")
    public Assessment saveAssessment(@RequestBody Assessment assessment) {
        return assessmentService.saveAssessment(assessment);
    }

    @GetMapping
    public List<Assessment> getAllAssessments() {
        return assessmentService.getAllAssessments();
    }

    @GetMapping("/{id}")
    public Assessment getAssessmentById(@PathVariable UUID id) {
        return assessmentService.getAssessmentById(id);
    }

    @PutMapping("/{id}/verify")
    @PreAuthorize("hasRole('HR')")
    public Assessment verifyAssessment(@PathVariable UUID id) {
        return assessmentService.verifyAssessment(id);
    }

    @DeleteMapping("/{id}")
    public String deleteAssessment(@PathVariable UUID id) {
        assessmentService.deleteAssessment(id);
        return "Assessment deleted successfully";
    }
}