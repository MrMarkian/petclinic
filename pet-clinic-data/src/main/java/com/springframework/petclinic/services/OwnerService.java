package com.springframework.petclinic.services;
import com.springframework.petclinic.model.Owner;
import java.util.Set;

public interface OwnerService {
    Owner findById(Long id);
    Owner save(Owner owner);
    Set<Owner> findAll();
}