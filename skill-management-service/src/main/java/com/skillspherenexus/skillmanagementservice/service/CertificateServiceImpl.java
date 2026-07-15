package com.skillspherenexus.skillmanagementservice.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillspherenexus.skillmanagementservice.entity.Certificate;
import com.skillspherenexus.skillmanagementservice.repository.CertificateRepository;

@Service
public class CertificateServiceImpl implements CertificateService {

    @Autowired
    private CertificateRepository certificateRepository;

    @Override
    public Certificate saveCertificate(Certificate certificate) {
        validateExpiryStatus(certificate);
        return certificateRepository.save(certificate);
    }

    @Override
    public List<Certificate> getAllCertificates() {
        List<Certificate> list = certificateRepository.findAll();
        list.forEach(this::validateExpiryStatus);
        return list;
    }

    @Override
    public List<Certificate> getCertificatesByEmployee(UUID empid) {
        List<Certificate> certificates = certificateRepository.findByEmpid(empid);
        certificates.forEach(this::validateExpiryStatus);
        return certificates;
    }

    @Override
    public Optional<Certificate> getCertificateById(UUID id) {
        Optional<Certificate> cert = certificateRepository.findById(id);
        cert.ifPresent(this::validateExpiryStatus);
        return cert;
    }

    @Override
    public void deleteCertificate(UUID id) {
        certificateRepository.deleteById(id);
    }

    @Override
    public Certificate updateCertificate(UUID id, Certificate certificate) {

        if (!certificateRepository.existsById(id)) {
            throw new RuntimeException("Certificate not found with id: " + id);
        }

        certificate.setCertid(id);
        validateExpiryStatus(certificate);

        return certificateRepository.save(certificate);
    }

    /**
     * Updates certificate status based on expiry date.
     */
    private void validateExpiryStatus(Certificate certificate) {

        if (certificate.getExpiry() != null) {

            if (certificate.getExpiry().isBefore(LocalDate.now())) {
                certificate.setStatus("Expired");
            } else {
                certificate.setStatus("Valid");
            }

        }
    }
}