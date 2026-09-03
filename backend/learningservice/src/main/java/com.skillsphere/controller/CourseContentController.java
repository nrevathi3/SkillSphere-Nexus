package com.skillsphere.controller;

import com.skillsphere.entity.CourseContent;
import com.skillsphere.service.CourseContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/course-content")
@RequiredArgsConstructor
public class CourseContentController {

    private final CourseContentService courseContentService;

    @PostMapping("/course/{courseId}")
    public CourseContent createContent(
            @PathVariable UUID courseId,
            @RequestBody CourseContent content) {

        return courseContentService.createContent(courseId, content);
    }

    @GetMapping("/course/{courseId}")
    public List<CourseContent> getCourseContent(
            @PathVariable UUID courseId) {

        return courseContentService.getCourseContent(courseId);
    }

    @GetMapping("/{contentId}")
    public CourseContent getContent(
            @PathVariable UUID contentId) {

        return courseContentService.getContent(contentId);
    }

    @DeleteMapping("/{contentId}")
    public String deleteContent(
            @PathVariable UUID contentId) {

        courseContentService.deleteContent(contentId);

        return "Course content deleted successfully";
    }
}