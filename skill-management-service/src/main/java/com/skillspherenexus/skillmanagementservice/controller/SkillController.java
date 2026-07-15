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

import com.skillspherenexus.skillmanagementservice.entity.Skill;
import com.skillspherenexus.skillmanagementservice.service.SkillService;

@RestController
@RequestMapping("/skills")
public class SkillController {

    @Autowired
    private SkillService skillService;

    // Create Skill
    @PostMapping
    public Skill saveSkill(@RequestBody Skill skill) {
        return skillService.saveSkill(skill);
    }

    // Get All Skills
    @GetMapping
    public List<Skill> getAllSkills() {
        return skillService.getAllSkills();
    }

    // Get Skill By Id
    @GetMapping("/{id}")
    public Skill getSkillById(@PathVariable Integer id) {
        return skillService.getSkillById(id);
    }

    // Update Skill
    @PutMapping("/{id}")
    public Skill updateSkill(@PathVariable Integer id,
                             @RequestBody Skill skill) {
        return skillService.updateSkill(id, skill);
    }

    // Delete Skill
    @DeleteMapping("/{id}")
    public String deleteSkill(@PathVariable Integer id) {
        skillService.deleteSkill(id);
        return "Skill deleted successfully";
    }
}