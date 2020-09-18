package com.springframework.petclinic.controllers;

import com.springframework.petclinic.model.Vet;
import com.springframework.petclinic.services.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Set;

@RequestMapping("/vets")
@Controller
public class VetController {

    private final VetService vetService;
    private final String VIEW_VET_DETAILS = "vets/vetDetails";
    private final String CREATE_OR_UPDATE_VET_FORM = "/vets/createOrUpdateVetForm";

    public VetController(VetService vetService) {
        this.vetService = vetService;

    }

    @RequestMapping({"/index", "/index.html", "/vets.html"})
    public String listVets(Model model){
        model.addAttribute("vets", vetService.findAll());
        return "vets/index";
    }


    @GetMapping("/{vetId}")
    public ModelAndView showVet (@PathVariable("vetId") Long vetId){
        ModelAndView mav = new ModelAndView(VIEW_VET_DETAILS);
        mav.addObject(vetService.findById(vetId));
        return mav;
    }

    @GetMapping(value = "/new")
    public String initCreationForm(Model model){
        model.addAttribute("vet", Vet.builder().build());
        return CREATE_OR_UPDATE_VET_FORM;
    }

    @PostMapping("/new")
    public String processCreationForm(@Valid Vet vet, BindingResult result){
        if (result.hasErrors()){
            return CREATE_OR_UPDATE_VET_FORM;

        }else {
            Vet savedVet = vetService.save(vet);
            return "redirect:/vets/" + savedVet.getId();
        }
    }

    @GetMapping("/{vetId}/edit")
    public String initUpdateVetForm(@PathVariable("vetId") Long vetId, Model model ){
        model.addAttribute(vetService.findById(vetId));
        return CREATE_OR_UPDATE_VET_FORM;
    }

    @PostMapping("/{vetId}/edit")
    public String processUpdateVetForm(@Valid Vet vet,@PathVariable("vetId") Long vetId, BindingResult result, Model model ){
        if(result.hasErrors()){
            return CREATE_OR_UPDATE_VET_FORM;
        } else{
            vet.setId(vetId);
            Vet savedVet = vetService.save(vet);
            return "redirect:/vets/" + savedVet.getId();
        }

    }




    @RequestMapping("/api/vets")
    public @ResponseBody Set<Vet> getVetsJson(){
        return vetService.findAll();
    }

}
