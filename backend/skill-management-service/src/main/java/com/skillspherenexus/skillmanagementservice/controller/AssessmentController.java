package com.skillspherenexus.skillmanagementservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillspherenexus.skillmanagementservice.entity.Assessment;
import com.skillspherenexus.skillmanagementservice.service.AssessmentService;

@RestController
@RequestMapping("/assessment")
@CrossOrigin("*")
public class AssessmentController {

    @Autowired
    private AssessmentService service;

    @PostMapping
    public Assessment saveAssessment(@RequestBody Assessment assessment){
        return service.saveAssessment(assessment);
    }

    @GetMapping
    public List<Assessment> getAllAssessments(){
        return service.getAllAssessments();
    }

    @GetMapping("/{id}")
    public Assessment getAssessment(@PathVariable Long id){
        return service.getAssessmentById(id);
    }

    @PutMapping("/{id}")
    public Assessment updateAssessment(@PathVariable Long id,
                                       @RequestBody Assessment assessment){
        return service.updateAssessment(id, assessment);
    }

    @DeleteMapping("/{id}")
    public String deleteAssessment(@PathVariable Long id){
        service.deleteAssessment(id);
        return "Assessment Deleted Successfully";
    }

}