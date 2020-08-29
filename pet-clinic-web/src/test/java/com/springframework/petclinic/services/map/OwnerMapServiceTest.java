package com.springframework.petclinic.services.map;

import com.springframework.petclinic.model.Owner;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
class OwnerMapServiceTest {

    OwnerMapService ownerMapService;

    final Long ownerId = 2L;
    final String lastName = "Smith";

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());

        Owner tempOwner = new Owner();
        tempOwner.setId(ownerId);
        tempOwner.setFirstName("John");
        tempOwner.setLastName("Smith");
        ownerMapService.save(tempOwner);
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerMapService.findAll();
        assertEquals(1, ownerSet.size());
    }

    @Test
    void findById() {
      Owner owner = ownerMapService.findById(ownerId);
      assertEquals(ownerId, owner.getId());

    }

    @Test
    void saveExistingID() {
        Long thisId = 2L;

        Owner owner2 = new Owner();
        owner2.setId(thisId);
        Owner savedOwner = ownerMapService.save(owner2);

        assertEquals(thisId,savedOwner.getId());
    }

    @Test
    void saveNoID(){
        Owner savedOwner = new Owner();
        ownerMapService.save(savedOwner);
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());

    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerId));
        assertEquals(0,ownerMapService.findAll().size() );
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId);
        assertEquals(0,ownerMapService.findAll().size() );
    }

    @Test
    void findByLastName() {
        Owner smith = ownerMapService.findByLastName(lastName);
        assertNotNull(smith);
        assertEquals(ownerId,smith.getId());
        log.debug(smith.getLastName());
    }

    @Test
    void findAllByLastNameLike() {
    }
}