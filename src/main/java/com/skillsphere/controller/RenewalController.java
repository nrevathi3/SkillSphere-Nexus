package com.skillsphere.controller;

import com.skillsphere.dto.RenewalDTO;
import com.skillsphere.service.RenewalService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/api/certifications/renewals")
@RequiredArgsConstructor
public class RenewalController {

    private final RenewalService renewalService;

    @PostMapping("/{certificationId}")
    @PreAuthorize("hasRole('HR')")
    public RenewalDTO requestRenewal(
            @PathVariable UUID certificationId,
            @RequestParam String requestedBy) {

        return renewalService.requestRenewal(
                certificationId,
                requestedBy
        );
    }

    @GetMapping("/{renewalId}")
    public RenewalDTO getRenewal(
            @PathVariable UUID renewalId) {

        return renewalService.getRenewal(renewalId);
    }

    @PutMapping("/{renewalId}/approve")
    @PreAuthorize("hasRole('HR')")
    public RenewalDTO approveRenewal(
            @PathVariable UUID renewalId,
            @RequestParam LocalDate newExpiry,
            @RequestParam String approvedBy) {

        return renewalService.approveRenewal(
                renewalId,
                newExpiry,
                approvedBy
        );
    }
}