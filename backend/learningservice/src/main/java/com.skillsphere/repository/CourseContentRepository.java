package com.skillsphere.repository;

import com.skillsphere.entity.CourseContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CourseContentRepository
        extends JpaRepository<CourseContent, UUID> {

    List<CourseContent> findByCourseCourseIdOrderBySequenceOrder(UUID courseId);
}