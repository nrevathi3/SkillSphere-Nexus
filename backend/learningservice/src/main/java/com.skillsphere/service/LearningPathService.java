package com.skillsphere.service;

import com.skillsphere.entity.Course;
import com.skillsphere.entity.Enrollment;
import com.skillsphere.entity.LearningPath;
import com.skillsphere.entity.LearningPathCourse;
import com.skillsphere.repository.CourseRepository;
import com.skillsphere.repository.EnrollmentRepository;
import com.skillsphere.repository.LearningPathCourseRepository;
import com.skillsphere.repository.LearningPathRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LearningPathService {

    private final LearningPathRepository learningPathRepository;

    private final LearningPathCourseRepository learningPathCourseRepository;

    private final CourseRepository courseRepository;

    private final EnrollmentRepository enrollmentRepository;


    // =====================================================
    // CREATE LEARNING PATH
    // =====================================================

    public LearningPath createLearningPath(
            LearningPath learningPath) {

        if (learningPath.getProgress() == null) {
            learningPath.setProgress(0);
        }

        if (learningPath.getActive() == null) {
            learningPath.setActive(true);
        }

        return learningPathRepository.save(learningPath);
    }


    // =====================================================
    // GET ALL LEARNING PATHS
    // =====================================================

    public List<LearningPath> getAllLearningPaths() {

        return learningPathRepository.findAll();
    }


    // =====================================================
    // GET LEARNING PATH
    // =====================================================

    public LearningPath getLearningPath(
            UUID pathId) {

        return learningPathRepository.findById(pathId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Learning path not found"
                        )
                );
    }


    // =====================================================
    // ADD COURSE TO LEARNING PATH
    // =====================================================

    public LearningPathCourse addCourseToLearningPath(
            UUID pathId,
            UUID courseId,
            Integer sequenceOrder) {

        LearningPath learningPath =
                learningPathRepository.findById(pathId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Learning path not found"
                                )
                        );

        Course course =
                courseRepository.findById(courseId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Course not found"
                                )
                        );

        LearningPathCourse learningPathCourse =
                LearningPathCourse.builder()
                        .learningPath(learningPath)
                        .course(course)
                        .sequenceOrder(sequenceOrder)
                        .build();

        return learningPathCourseRepository.save(
                learningPathCourse
        );
    }


    // =====================================================
    // GET COURSES IN LEARNING PATH
    // =====================================================

    public List<LearningPathCourse>
    getCoursesInLearningPath(UUID pathId) {

        learningPathRepository.findById(pathId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Learning path not found"
                        )
                );

        return learningPathCourseRepository
                .findByLearningPathPathIdOrderBySequenceOrder(
                        pathId
                );
    }


    // =====================================================
    // GET LEARNING PATH PROGRESS
    // =====================================================

    public Integer getLearningPathProgress(
            UUID pathId,
            UUID empId) {

        // Make sure the learning path exists
        learningPathRepository.findById(pathId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Learning path not found"
                        )
                );

        // Get courses belonging to this learning path
        List<LearningPathCourse> pathCourses =
                learningPathCourseRepository
                        .findByLearningPathPathIdOrderBySequenceOrder(
                                pathId
                        );

        // No courses = 0%
        if (pathCourses.isEmpty()) {
            return 0;
        }

        // Get employee enrollments once
        List<Enrollment> enrollments =
                enrollmentRepository.findByEmpId(empId);

        int completedCourses = 0;

        for (LearningPathCourse pathCourse : pathCourses) {

            UUID courseId =
                    pathCourse.getCourse().getCourseId();

            boolean completed =
                    enrollments.stream()
                            .anyMatch(enrollment ->
                                    enrollment.getCourse()
                                            .getCourseId()
                                            .equals(courseId)
                                            &&
                                            Boolean.TRUE.equals(
                                                    enrollment.getCompleted()
                                            )
                            );

            if (completed) {
                completedCourses++;
            }
        }

        return (completedCourses * 100) / pathCourses.size();
    }
}