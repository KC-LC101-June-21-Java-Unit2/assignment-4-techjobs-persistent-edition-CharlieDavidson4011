package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.data.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.data.Skill;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private SkillRepository skillRepository;

    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("title", "My Jobs");
        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());
        model.addAttribute(new Job());

        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                    Errors errors, Model model,
                                    @RequestParam int employerId,
                                    @RequestParam(required = false) List<Integer> skills) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            model.addAttribute("employers", employerRepository.findAll());
            model.addAttribute("skills", skillRepository.findAll());
            return "add";
        }
        Optional<Employer> employerResult = employerRepository.findById(employerId);
        Employer employer = employerResult.get();
        newJob.setEmployer(employer);


        List<Skill> skillObs = (List<Skill>) skillRepository.findAllById(skills);
        if (skillObs.isEmpty()) {
            return "add";
        } else {
            newJob.setSkills(skillObs);

            jobRepository.save(newJob);
        }
//        model.addAttribute("job", newJob);
//        model.addAttribute("employer", employerId);
//       model.addAttribute("employerId", employerId);
//        model.addAttribute("employer", employerRepository.findById(employerId));
//       model.addAttribute("skill", skillRepository.findAll());
       return "redirect:";


}

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {

        Optional<Job> optJob = jobRepository.findById(jobId);
        if (optJob.isPresent()) {
            Job job = optJob.get();
            model.addAttribute("job", job);
            return "view";
        } else {
            return "redirect:../";
        }
    }

}
