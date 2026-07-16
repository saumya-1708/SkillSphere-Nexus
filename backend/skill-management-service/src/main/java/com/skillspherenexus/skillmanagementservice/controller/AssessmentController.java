package com.skillspherenexus.skillmanagementservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillspherenexus.skillmanagementservice.dto.AssessmentRequestDTO;
import com.skillspherenexus.skillmanagementservice.dto.AssessmentResponseDTO;
import com.skillspherenexus.skillmanagementservice.service.AssessmentService;

@RestController
@RequestMapping("/api/assessments")
public class AssessmentController {

    @Autowired
    private AssessmentService assessmentService;

    @PostMapping
    public AssessmentResponseDTO saveAssessment(
            @RequestBody AssessmentRequestDTO request) {

        return assessmentService.saveAssessment(request);
    }

    @GetMapping
    public List<AssessmentResponseDTO> getAllAssessments() {

        return assessmentService.getAllAssessments();
    }

    @GetMapping("/{id}")
    public AssessmentResponseDTO getAssessmentById(
            @PathVariable Long id) {

        return assessmentService.getAssessmentById(id);
    }

    @PutMapping("/{id}")
    public AssessmentResponseDTO updateAssessment(
            @PathVariable Long id,
            @RequestBody AssessmentRequestDTO request) {

        return assessmentService.updateAssessment(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteAssessment(@PathVariable Long id) {

        assessmentService.deleteAssessment(id);

        return "Assessment deleted successfully";
    }
}