package com.skillsphere.service;

import com.skillsphere.entity.Assessment;
import com.skillsphere.repository.AssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AssessmentService {

    @Autowired
    private AssessmentRepository assessmentRepository;

    public Assessment saveAssessment(Assessment assessment) {
        return assessmentRepository.save(assessment);
    }

    public List<Assessment> getAllAssessments() {
        return assessmentRepository.findAll();
    }

    public Assessment getAssessmentById(UUID assessmentId) {
        return assessmentRepository.findById(assessmentId).orElse(null);
    }

    public void deleteAssessment(UUID assessmentId) {
        assessmentRepository.deleteById(assessmentId);
    }
}