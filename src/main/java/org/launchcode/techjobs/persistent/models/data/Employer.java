package org.launchcode.techjobs.persistent.models.data;

import org.launchcode.techjobs.persistent.models.AbstractEntity;
import org.launchcode.techjobs.persistent.models.Job;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employer extends AbstractEntity {

    @NotBlank(message = "City is required")
    private String location;

    @OneToMany
    @JoinColumn(name = "employer_id")

    private final List<Job> jobs = new ArrayList<>();

    public Employer(String location) {
        this.location = location;
    }

    public Employer() {}

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}