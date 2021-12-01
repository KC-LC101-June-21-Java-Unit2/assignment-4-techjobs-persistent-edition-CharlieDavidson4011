package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.data.Employer;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("employers")
public class EmployerController {

    @Autowired//this tells Spring to manage the repository and auto populate the employerReository field so we don't set anything here.
    private EmployerRepository employerRepository;

    //we may want to use methods because the EmployerRepository extends the CRUD repository. See Chris' video
    //maybe findAll(), save()



    //Controllers section, #2
    //should this actually live at employers/index
    @GetMapping ("")
    public String index (Model model){
        model.addAttribute("employers", employerRepository.findAll());


        //responds with a list of all employers in the database
        return "employers/index";//or should this be return employers/index?
    }

    @GetMapping("add")
    public String displayAddEmployerForm(Model model) {
        model.addAttribute(new Employer());
        return "employers/add";
    }

    @PostMapping("add")//do I need an @requestParam here?
    public String processAddEmployerForm(@ModelAttribute @Valid Employer newEmployer,
                                         Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "employers/add";
        }
        else {

            model.addAttribute("employers", employerRepository.save(newEmployer));
            //employerRepository.save(new Employer());
        }

        return "redirect:";
    }

    @GetMapping("view/{employerId}")//path parameter employerId. This piece of data customizes the response
    public String displayViewEmployer(Model model, @PathVariable int employerId) {

        Optional optEmployer = employerRepository.findById(employerId);
        if (optEmployer.isPresent()) {
            Employer employer = (Employer) optEmployer.get();
            model.addAttribute("employer", employer);

            return "employers/view";
        } else {
            return "redirect:../";
        }
    }

}