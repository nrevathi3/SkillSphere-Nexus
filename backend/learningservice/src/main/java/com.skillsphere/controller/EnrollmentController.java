package com.skillsphere.controller;

import com.skillsphere.dto.EnrollmentDTO;
import com.skillsphere.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping
    public EnrollmentDTO enroll(
            @RequestParam UUID empId,
            @RequestParam UUID courseId) {

        return enrollmentService.enroll(empId, courseId);
    }

    @GetMapping("/employee/{empId}")
    public List<EnrollmentDTO> getEmployeeEnrollments(
            @PathVariable UUID empId) {

        return enrollmentService.getEmployeeEnrollments(empId);
    }
}