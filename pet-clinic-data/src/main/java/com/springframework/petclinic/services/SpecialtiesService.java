package com.springframework.petclinic.services;

import com.springframework.petclinic.model.Speciality;
import org.springframework.stereotype.Service;

@Service
public interface SpecialtiesService extends CrudService<Speciality, Long> {


}
