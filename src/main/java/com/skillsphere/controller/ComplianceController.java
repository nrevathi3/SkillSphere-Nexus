package com.skillsphere.controller;

import com.skillsphere.dto.ComplianceDTO;
import com.skillsphere.service.ComplianceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/compliance")
@RequiredArgsConstructor
public class ComplianceController {

    private final ComplianceService complianceService;

    @GetMapping("/employee/{employeeId}")
    public ComplianceDTO getEmployeeCompliance(
            @PathVariable UUID employeeId) {

        return complianceService.getEmployeeCompliance(employeeId);
    }
}


