package com.skillspherenexus.skillmanagementservice.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillspherenexus.skillmanagementservice.entity.Certificate;
import com.skillspherenexus.skillmanagementservice.service.CertificateService;

@RestController
@RequestMapping("/api/certificates") // Clean REST path configuration [cite: 33]
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    @PostMapping
    public ResponseEntity<Certificate> addCertificate(@RequestBody Certificate certificate) {
        return ResponseEntity.ok(certificateService.saveCertificate(certificate));
    }

    @GetMapping
    public ResponseEntity<List<Certificate>> getAllCertificates() {
        return ResponseEntity.ok(certificateService.getAllCertificates());
    }

    // Milestone 1 Routing: Single employee certificate list query
    @GetMapping("/employee/{empid}")
    public ResponseEntity<List<Certificate>> getCertificatesByEmployee(@PathVariable UUID empid) {
        return ResponseEntity.ok(certificateService.getCertificatesByEmployee(empid));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Certificate> getCertificateById(@PathVariable UUID id) {
        return certificateService.getCertificateById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Certificate> updateCertificate(@PathVariable UUID id, @RequestBody Certificate certificate) {
        try {
            return ResponseEntity.ok(certificateService.updateCertificate(id, certificate));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCertificate(@PathVariable UUID id) {
        certificateService.deleteCertificate(id);
        return ResponseEntity.ok("Certificate deleted successfully");
    }
}