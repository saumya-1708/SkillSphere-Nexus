package com.skillspherenexus.skillmanagementservice.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.skillspherenexus.skillmanagementservice.entity.Certificate;

public interface CertificateService {

    Certificate saveCertificate(Certificate certificate);

    List<Certificate> getAllCertificates();

    List<Certificate> getCertificatesByEmployee(UUID empid);

    Optional<Certificate> getCertificateById(UUID id);

    Certificate updateCertificate(UUID id, Certificate certificate);

    void deleteCertificate(UUID id);
}