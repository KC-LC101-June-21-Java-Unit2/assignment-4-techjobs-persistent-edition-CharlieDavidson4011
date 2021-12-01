package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.data.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.data.Skill;
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

@Controller
@RequestMapping("skills")
public class SkillController {

    @Autowired
//this tells Spring to manage the repository and auto populate the employerRepository field so we don't set anything here.
    private SkillRepository skillRepository;


    //@GetMapping("")
    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("skills", skillRepository.findAll());//not sure about this

        return "skills/index";
    }


    @GetMapping("add")
    public String displayAddSkillForm(Model model) {
        model.addAttribute("skill", new Skill());
        return "skills/add";
    }



    @PostMapping("add")//do I need an @requestParam here?
    public String processAddSkillForm(@ModelAttribute @Valid Skill newSkill,
                                      Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "skills/add";
        }
        //re-added else

        //model.addAttribute("skills", skillRepository.save(newSkill));
        skillRepository.save(newSkill); //Removed Sunday morning with Angela
        return "redirect:";
    }


    //Sunday morning I replaced skillID with skill_ID and it still caused no errors, so it may not be getting here
    @GetMapping("view/{skillId}")//path parameter skillId. This piece of data customizes the response
    public String displayViewSkill(Model model, @PathVariable int skillId) {

        Optional optionalSkill = skillRepository.findById(skillId);
        if (optionalSkill.isPresent()) {
            Skill skill = (Skill) optionalSkill.get();
            model.addAttribute("skill", skill);

            return "skills/view";
        } else {
            return "redirect:../";
        }
    }







}