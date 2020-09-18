package com.springframework.petclinic.controllers;

import com.springframework.petclinic.model.Owner;
import com.springframework.petclinic.model.Pet;
import com.springframework.petclinic.model.Vet;
import com.springframework.petclinic.model.Visit;

import com.springframework.petclinic.services.PetService;
import com.springframework.petclinic.services.VetService;
import com.springframework.petclinic.services.VisitService;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

@Slf4j
@Controller
public class VisitController {

    private final VisitService visitService;
    private final PetService petService;
    private final VetService vetService;

    public VisitController(VisitService visitService, PetService petService, VetService vetService) {
        this.visitService = visitService;
        this.petService = petService;
        this.vetService = vetService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder webDataBinder){ //Bind to any local date prroperty on the form and handle
        webDataBinder.setDisallowedFields("id");
        webDataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport(){
            @Override
            public void setAsText(String text) throws IllegalArgumentException{
                setValue(LocalDate.parse(text));
            }
        });
    }

    @ModelAttribute("visit")
    public Visit loadPetWithVisit(@PathVariable("petId") Long petId, Map<String , Object> model) {
        Pet pet = petService.findById(petId);
        Set<Vet> vetSet = vetService.findAll();
        model.put("pet", pet);
      // model.put("vets",vetSet);
        Visit visit = new Visit();
        pet.getVisitSet().add(visit);
        visit.setPet(pet);
        return  visit;
    }


    @ModelAttribute("vets")
    public Set<Vet> populateVets(){
        return vetService.findAll();
    }

    // Spring MVC calls method loadPetWithVisit(...) before initNewVisitForm is called
    @RequestMapping("/owners/*/pets/{petId}/visits/new")
    public String initNewVisitForm(@PathVariable("petId") Long petId, Model model) {
        model.addAttribute("vets", vetService.findAll());
        return "pets/createOrUpdateVisitForm";
    }
    // Spring MVC calls method loadPetWithVisit(...) before processNewVisitForm is called
    @PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
    public String processNewVisitForm(@Valid Visit visit, @ModelAttribute(value = "vet") Vet vets, BindingResult result) {
        if (result.hasErrors()) {
            return "pets/createOrUpdateVisitForm";
        } else {

            visit.setVet(vets);
            vets.getVisitSet().add(visit);

            visitService.save(visit);
            vetService.save(vets);


            log.error("Received Vet Id: " + vets.getId().toString());
            log.error("Saved Visit: - Vet :" + vets.getFirstName() + " " + vets.getLastName());


            return "redirect:/owners/{ownerId}";
        }
    }

    @GetMapping("/owners/{ownerId}/pets/{petId}/visits/{visitId}/edit")
    public String initUpdateForm(@PathVariable("visitId") Long visitId, Model model){
        model.addAttribute("visit", visitService.findById(visitId));
        return "pets/createOrUpdateVisitForm";
    }

    @PostMapping("/owners/{ownerId}/pets/{petId}/visits/{visitId}/edit")
     public String processUpdateForm(@Valid Visit visit, @Valid Pet pet,@Valid Vet vet, @PathVariable("ownerId") Long ownerId, @PathVariable("petId") Long petId, BindingResult result, @PathVariable("visitId") Long visitId){
        if(result.hasErrors()){
            return "pets/createOrUpdateVisitForm";
        }else
        {
         //   Pet savedPet =petService.save(pet);
            Vet savedVet = vetService.save(vet);
          Visit savedVisit = visitService.save(visit);

          return "redirect:/owners/" + savedVisit.getPet().getOwner().getId();
        }

    }
}
