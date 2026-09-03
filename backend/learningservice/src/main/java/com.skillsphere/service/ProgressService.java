package com.skillsphere.service;

import com.skillsphere.entity.Enrollment;
import com.skillsphere.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProgressService {

    private final EnrollmentRepository enrollmentRepository;

    // Passing score
    private static final float PASSING_SCORE = 50.0f;


    // =====================================================
    // UPDATE COURSE CONTENT PROGRESS
    // =====================================================

    public Enrollment updateProgress(
            UUID enrollmentId,
            Integer progress) {

        if (progress < 0 || progress > 100) {
            throw new IllegalArgumentException(
                    "Progress must be between 0 and 100"
            );
        }

        Enrollment enrollment =
                enrollmentRepository.findById(enrollmentId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Enrollment not found"
                                )
                        );


        /*
         * Progress represents COURSE CONTENT completion.
         *
         * Reaching 100% does NOT mean the entire course
         * is completed.
         *
         * The assessment must also be passed and the user
         * must click Complete Course.
         */

        enrollment.setProgress(progress);


        /*
         * IMPORTANT:
         *
         * Do NOT automatically set completed = true
         * when progress reaches 100%.
         */

        if (progress < 100) {

            enrollment.setCompleted(false);

            enrollment.setCompletedAt(null);

        }


        return enrollmentRepository.save(enrollment);
    }


    // =====================================================
    // SUBMIT ASSESSMENT
    // =====================================================

    public Enrollment submitAssessment(
            UUID enrollmentId,
            Float score) {

        if (score == null || score < 0 || score > 100) {

            throw new IllegalArgumentException(
                    "Score must be between 0 and 100"
            );

        }


        Enrollment enrollment =
                enrollmentRepository.findById(enrollmentId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Enrollment not found"
                                )
                        );


        /*
         * Save assessment score.
         */

        enrollment.setScore(score);


        /*
         * IMPORTANT:
         *
         * Submitting an assessment does NOT automatically
         * complete the course.
         *
         * Even if the score is >= 50, the user still needs
         * to click "Complete Course".
         */

        enrollment.setCompleted(false);

        enrollment.setCompletedAt(null);


        return enrollmentRepository.save(enrollment);
    }


    // =====================================================
    // COMPLETE COURSE
    // =====================================================

    public Enrollment completeCourse(
            UUID enrollmentId) {

        Enrollment enrollment =
                enrollmentRepository.findById(enrollmentId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Enrollment not found"
                                )
                        );


        /*
         * Course content must be completed first.
         */

        if (
                enrollment.getProgress() == null ||
                        enrollment.getProgress() < 100
        ) {

            throw new IllegalStateException(
                    "Complete all course content first"
            );

        }


        /*
         * Assessment must exist.
         */

        if (enrollment.getScore() == null) {

            throw new IllegalStateException(
                    "Complete the assessment first"
            );

        }


        /*
         * Assessment must be passed.
         */

        if (enrollment.getScore() < PASSING_SCORE) {

            throw new IllegalStateException(
                    "Assessment must be passed before completing the course"
            );

        }


        /*
         * NOW the course is actually completed.
         */

        enrollment.setProgress(100);

        enrollment.setCompleted(true);

        enrollment.setCompletedAt(
                LocalDateTime.now()
        );


        return enrollmentRepository.save(enrollment);
    }
}