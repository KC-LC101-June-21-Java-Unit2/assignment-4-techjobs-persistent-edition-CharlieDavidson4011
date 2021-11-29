package org.launchcode.techjobs.persistent.models.data;

import org.launchcode.techjobs.persistent.models.data.Skill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends CrudRepository<Skill, Integer> {
}