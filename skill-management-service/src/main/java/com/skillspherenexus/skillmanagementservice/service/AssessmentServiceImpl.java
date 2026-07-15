package com.skillspherenexus.skillmanagementservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillspherenexus.skillmanagementservice.entity.Assessment;
import com.skillspherenexus.skillmanagementservice.repository.AssessmentRepository;

@Service
public class AssessmentServiceImpl implements AssessmentService {

    @Autowired
    private AssessmentRepository repository;

    @Override
    public Assessment saveAssessment(Assessment assessment) {
        return repository.save(assessment);
    }

    @Override
    public List<Assessment> getAllAssessments() {
        return repository.findAll();
    }

    @Override
    public Assessment getAssessmentById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Assessment updateAssessment(Long id, Assessment assessment) {

        Assessment existing = repository.findById(id).orElse(null);

        if(existing != null){

            existing.setEmployeeId(assessment.getEmployeeId());
            existing.setSkillId(assessment.getSkillId());
            existing.setScore(assessment.getScore());
            existing.setVerified(assessment.getVerified());

            return repository.save(existing);
        }

        return null;
    }

    @Override
    public void deleteAssessment(Long id) {
        repository.deleteById(id);
    }
}