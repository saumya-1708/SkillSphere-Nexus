package com.skillspherenexus.skillmanagementservice.dto;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CertificateRequestDTO {

    @NotNull
    private UUID empid;

    @NotBlank
    private String name;

    private String issuingOrganization;

    private LocalDate issueDate;

    private LocalDate expiry;

    private String status;
}
