package com.springframework.petclinic.formatters;

import com.springframework.petclinic.model.Vet;
import com.springframework.petclinic.services.VetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

@Slf4j
@Component
public class VetFormatter implements Formatter<Vet>{

    private final VetService vetService;

    public VetFormatter(VetService vetService) {
        this.vetService = vetService;
    }

    @Override
    public Vet parse(String s, Locale locale) throws ParseException {
        Collection<Vet> findVets = vetService.findAll();
        log.error("Vet Formatter received: " + s);
        if (s != null)
        return vetService.findById(Long.valueOf(s));
        else
     /*   for (Vet vet : findVets){
            if(vet.getFirstName().equals(vetService.findById(Long.valueOf(s)).getFirstName())){

                return vet;
            }
        }*/
        throw new ParseException("Type Not Found.." + s , 0);
    }

    @Override
    public String print(Vet vet, Locale locale) {
        return vet.getFirstName();
    }
}
