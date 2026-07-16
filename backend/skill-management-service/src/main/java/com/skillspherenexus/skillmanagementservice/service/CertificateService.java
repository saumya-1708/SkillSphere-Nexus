package com.skillspherenexus.skillmanagementservice.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.skillspherenexus.skillmanagementservice.dto.CertificateRequestDTO;
import com.skillspherenexus.skillmanagementservice.dto.CertificateResponseDTO;

public interface CertificateService {

    CertificateResponseDTO saveCertificate(CertificateRequestDTO request);

    List<CertificateResponseDTO> getAllCertificates();

    List<CertificateResponseDTO> getCertificatesByEmployee(UUID empid);

    Optional<CertificateResponseDTO> getCertificateById(UUID id);

    CertificateResponseDTO updateCertificate(UUID id, CertificateRequestDTO request);

    void deleteCertificate(UUID id);
}