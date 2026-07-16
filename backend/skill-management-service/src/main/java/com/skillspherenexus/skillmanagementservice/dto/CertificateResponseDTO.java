package com.skillspherenexus.skillmanagementservice.dto;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Data;

@Data
public class CertificateResponseDTO {

    private UUID certid;

    private UUID empid;

    private String name;

    private String issuingOrganization;

    private LocalDate issueDate;

    private LocalDate expiry;

    private String status;
}
