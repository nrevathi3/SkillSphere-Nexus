package com.skillsphere.service;

import com.skillsphere.entity.Assessment;
import com.skillsphere.repository.AssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AssessmentService {

    private static final int PASS_THRESHOLD = 70;

    @Autowired
    private AssessmentRepository assessmentRepository;

    public Assessment saveAssessment(Assessment assessment) {

        // Calculate pass/fail
        assessment.setPassed(assessment.getScore() >= PASS_THRESHOLD);

        // New assessments are not verified until HR verifies them
        assessment.setVerified(false);

        // Keep your existing result field updated
        assessment.setResult(
                assessment.getPassed() ? "PASSED" : "FAILED"
        );

        return assessmentRepository.save(assessment);
    }

    public List<Assessment> getAllAssessments() {
        return assessmentRepository.findAll();
    }

    public Assessment getAssessmentById(UUID assessmentId) {
        return assessmentRepository.findById(assessmentId).orElse(null);
    }

    public Assessment verifyAssessment(UUID assessmentId) {

        Assessment assessment = assessmentRepository.findById(assessmentId)
                .orElse(null);

        if (assessment != null) {
            assessment.setVerified(true);
            return assessmentRepository.save(assessment);
        }

        return null;
    }

    public void deleteAssessment(UUID assessmentId) {
        assessmentRepository.deleteById(assessmentId);
    }
}