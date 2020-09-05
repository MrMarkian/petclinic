package com.springframework.petclinic.formatters;

import com.springframework.petclinic.model.Pet;
import com.springframework.petclinic.model.PetType;
import com.springframework.petclinic.repositories.PetRepository;
import com.springframework.petclinic.services.PetTypeService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

@Component
public class PetTypeFormatter implements Formatter<PetType> {

    private final PetTypeService petTypeService;

    public PetTypeFormatter(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public PetType parse(String text, Locale locale) throws ParseException {
        Collection<PetType> findPetType = petTypeService.findAll();

            for(PetType type : findPetType){
                if(type.getName().equals(text)) {
                    return type;
                }
            }

        throw new ParseException("Type Not Found.." + text , 0);

    }

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }
}
