package com.skillspherenexus.skillmanagementservice.service;

import java.util.List;

import com.skillspherenexus.skillmanagementservice.entity.Skill;

public interface SkillService {

    Skill saveSkill(Skill skill);

    List<Skill> getAllSkills();

    Skill getSkillById(Integer skillId);

    Skill updateSkill(Integer skillId, Skill skill);

    void deleteSkill(Integer skillId);
}