package com.skillsphere.controller;

import com.skillsphere.entity.LearningPath;
import com.skillsphere.entity.LearningPathCourse;
import com.skillsphere.service.LearningPathService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/learning-paths")
@RequiredArgsConstructor
public class LearningPathController {

    private final LearningPathService learningPathService;


    // =====================================================
    // CREATE LEARNING PATH
    // =====================================================

    @PostMapping
    public LearningPath createLearningPath(
            @RequestBody LearningPath learningPath) {

        return learningPathService
                .createLearningPath(learningPath);
    }


    // =====================================================
    // GET ALL LEARNING PATHS
    // =====================================================

    @GetMapping
    public List<LearningPath> getAllLearningPaths() {

        return learningPathService
                .getAllLearningPaths();
    }


    // =====================================================
    // GET LEARNING PATH
    // =====================================================

    @GetMapping("/{pathId}")
    public LearningPath getLearningPath(
            @PathVariable UUID pathId) {

        return learningPathService
                .getLearningPath(pathId);
    }


    // =====================================================
    // ADD COURSE TO LEARNING PATH
    // =====================================================

    @PostMapping("/{pathId}/courses")
    public LearningPathCourse addCourseToLearningPath(

            @PathVariable UUID pathId,

            @RequestParam UUID courseId,

            @RequestParam Integer sequenceOrder) {

        return learningPathService
                .addCourseToLearningPath(
                        pathId,
                        courseId,
                        sequenceOrder
                );
    }


    // =====================================================
    // GET COURSES IN LEARNING PATH
    // =====================================================

    @GetMapping("/{pathId}/courses")
    public List<LearningPathCourse>
    getCoursesInLearningPath(
            @PathVariable UUID pathId) {

        return learningPathService
                .getCoursesInLearningPath(pathId);
    }
    // =====================================================
// GET LEARNING PATH PROGRESS
// =====================================================

    @GetMapping("/{pathId}/progress/{empId}")
    public Integer getLearningPathProgress(
            @PathVariable UUID pathId,
            @PathVariable UUID empId) {

        return learningPathService
                .getLearningPathProgress(pathId, empId);
    }
}