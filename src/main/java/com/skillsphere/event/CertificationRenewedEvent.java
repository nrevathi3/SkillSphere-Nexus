package com.skillsphere.event;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CertificationRenewedEvent {

    private UUID certificationId;

    private UUID employeeId;

    private LocalDate oldExpiry;

    private LocalDate newExpiry;

    private String renewedBy;
}