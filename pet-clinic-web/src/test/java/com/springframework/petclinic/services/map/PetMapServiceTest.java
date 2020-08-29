package com.springframework.petclinic.services.map;

import com.springframework.petclinic.model.Pet;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class PetMapServiceTest {

    PetMapService petMapService;
    final Long petId = 3L;

    @BeforeEach
    void setUp() {
        petMapService  = new PetMapService();
        Pet tempPet = new Pet();
        tempPet.setId(petId);
       petMapService.save(tempPet);
    }

    @Test
    void findAll() {
        Set<Pet> petSet = petMapService.findAll();
        assertNotNull(petSet);
        assertEquals(1,petSet.size());
    }

    @Test
    void deleteById() {
        petMapService.deleteById(petId);
        assertEquals(0,petMapService.findAll().size());
    }

    @Test
    void delete() {
        petMapService.delete(petMapService.findById(petId));
        assertEquals(0,petMapService.findAll().size());

    }

    @Test
    void save() {
        Pet thisPet = new Pet();
        thisPet.setId(4L);
        Pet savedPet =  petMapService.save(thisPet);
        assertEquals(4L,savedPet.getId());
    }

    @Test
    void findById() {
        Pet kitty = petMapService.findById(petId);
        assertNotNull(kitty);
        assertEquals(petId, kitty.getId());
        log.debug("PET ID: " + kitty.getId().toString());
    }
}