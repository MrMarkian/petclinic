package com.springframework.petclinic.repositories;

import com.springframework.petclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface SpecialityRepository extends CrudRepository<PetType, Long> {

}

