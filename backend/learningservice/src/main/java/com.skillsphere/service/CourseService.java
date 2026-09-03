package com.skillsphere.service;

import com.skillsphere.dto.CourseDTO;
import com.skillsphere.entity.Course;
import com.skillsphere.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public CourseDTO getCourse(UUID courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new RuntimeException("Course not found"));

        return toDTO(course);
    }

    public CourseDTO createCourse(CourseDTO dto) {

        Course course = Course.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .duration(dto.getDuration())
                .type(Course.CourseType.valueOf(dto.getType()))
                .instructor(dto.getInstructor())
                .rating(dto.getRating())
                .active(true)
                .build();

        return toDTO(courseRepository.save(course));
    }

    public void deleteCourse(UUID courseId) {
        courseRepository.deleteById(courseId);
    }

    private CourseDTO toDTO(Course course) {

        return CourseDTO.builder()
                .courseId(course.getCourseId())
                .title(course.getTitle())
                .description(course.getDescription())
                .duration(course.getDuration())
                .type(course.getType().name())
                .instructor(course.getInstructor())
                .rating(course.getRating())
                .active(course.getActive())
                .build();
    }
}