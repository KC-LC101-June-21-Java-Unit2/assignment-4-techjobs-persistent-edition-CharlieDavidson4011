package org.launchcode.techjobs.persistent.models;

import org.launchcode.techjobs.persistent.models.data.Employer;
import org.launchcode.techjobs.persistent.models.data.Skill;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Job extends AbstractEntity{

    @ManyToOne
    private Employer employer;


    @ManyToMany
    @NotEmpty(message = "A skill must be selected")

    private List<Skill> skills = new ArrayList<>();


    public Job(Employer anEmployer, List<Skill> someSkills) {
        super();
        this.employer = anEmployer;
        this.skills = someSkills;
    }

    public Job() {

    }
    public Employer getEmployer() {
        return employer;
    }


    public void setEmployer(Employer employer) {
        this.employer = employer;
    }


    public List<Skill> getSkills() {
        return skills;
    }


    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

}