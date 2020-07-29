package com.springframework.petclinic.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vets")
public class Vet extends Person{

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vet_specialties", joinColumns = @JoinColumn(name = "vet_id"))
    private Set<Speciality> specialitySet = new HashSet<>();


    public Set<Speciality> getSpecialitySet() { return specialitySet; }
    public void setSpecialitySet(Set<Speciality> specialitySet) { this.specialitySet = specialitySet;}
}

