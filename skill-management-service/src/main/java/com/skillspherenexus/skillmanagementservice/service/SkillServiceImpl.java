package com.skillspherenexus.skillmanagementservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillspherenexus.skillmanagementservice.entity.Skill;
import com.skillspherenexus.skillmanagementservice.repository.SkillRepository;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Override
    public Skill saveSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    @Override
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    @Override
    public Skill getSkillById(Integer skillId) {
        return skillRepository.findById(skillId).orElse(null);
    }

    @Override
    public Skill updateSkill(Integer skillId, Skill skill) {

        Skill existingSkill = skillRepository.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        existingSkill.setSkillName(skill.getSkillName());
        existingSkill.setCategory(skill.getCategory());
        existingSkill.setDescription(skill.getDescription());

        return skillRepository.save(existingSkill);
    }

    @Override
    public void deleteSkill(Integer skillId) {
        skillRepository.deleteById(skillId);
    }
}