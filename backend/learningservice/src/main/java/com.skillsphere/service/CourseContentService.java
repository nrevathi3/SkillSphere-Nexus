package com.skillsphere.service;

import com.skillsphere.entity.Course;
import com.skillsphere.entity.CourseContent;
import com.skillsphere.repository.CourseContentRepository;
import com.skillsphere.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseContentService {

    private final CourseContentRepository courseContentRepository;
    private final CourseRepository courseRepository;

    public CourseContent createContent(UUID courseId, CourseContent content) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new RuntimeException("Course not found"));

        content.setCourse(course);

        return courseContentRepository.save(content);
    }

    public List<CourseContent> getCourseContent(UUID courseId) {

        return courseContentRepository
                .findByCourseCourseIdOrderBySequenceOrder(courseId);
    }

    public CourseContent getContent(UUID contentId) {

        return courseContentRepository.findById(contentId)
                .orElseThrow(() ->
                        new RuntimeException("Course content not found"));
    }

    public void deleteContent(UUID contentId) {

        if (!courseContentRepository.existsById(contentId)) {
            throw new RuntimeException("Course content not found");
        }

        courseContentRepository.deleteById(contentId);
    }
}