package com.skillsphere.controller;

import com.skillsphere.entity.Assessment;
import com.skillsphere.service.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/assessments")
public class AssessmentController {

    @Autowired
    private AssessmentService assessmentService;

    @PostMapping
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

    @DeleteMapping("/{id}")
    public String deleteAssessment(@PathVariable UUID id) {
        assessmentService.deleteAssessment(id);
        return "Assessment deleted successfully";
    }
}