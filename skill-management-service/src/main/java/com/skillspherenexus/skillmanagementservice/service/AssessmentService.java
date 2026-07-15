package com.skillspherenexus.skillmanagementservice.service;

import java.util.List;

import com.skillspherenexus.skillmanagementservice.entity.Assessment;

public interface AssessmentService {

    Assessment saveAssessment(Assessment assessment);

    List<Assessment> getAllAssessments();

    Assessment getAssessmentById(Long id);

    Assessment updateAssessment(Long id, Assessment assessment);

    void deleteAssessment(Long id);
}