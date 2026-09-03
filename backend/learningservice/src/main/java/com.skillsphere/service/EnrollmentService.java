package com.skillsphere.service;

import com.skillsphere.dto.EnrollmentDTO;
import com.skillsphere.entity.Enrollment;
import com.skillsphere.repository.CourseRepository;
import com.skillsphere.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentDTO enroll(UUID empId, UUID courseId) {

        var course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new RuntimeException("Course not found"));

        Enrollment enrollment = Enrollment.builder()
                .empId(empId)
                .course(course)
                .enrolledAt(LocalDateTime.now())
                .progress(0)
                .completed(false)
                .score(0.0f)
                .build();

        return toDTO(enrollmentRepository.save(enrollment));
    }

    public List<EnrollmentDTO> getEmployeeEnrollments(UUID empId) {

        return enrollmentRepository.findByEmpId(empId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    private EnrollmentDTO toDTO(Enrollment enrollment) {

        return EnrollmentDTO.builder()
                .enrollmentId(enrollment.getEnrollmentId())
                .empId(enrollment.getEmpId())
                .courseId(enrollment.getCourse().getCourseId())
                .enrolledAt(enrollment.getEnrolledAt())
                .progress(enrollment.getProgress())
                .completed(enrollment.getCompleted())
                .score(enrollment.getScore())
                .completedAt(enrollment.getCompletedAt())
                .build();
    }
}