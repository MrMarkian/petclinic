package com.springframework.petclinic.services.springDataJpa;

import com.springframework.petclinic.model.Visit;
import com.springframework.petclinic.repositories.VisitRepository;
import com.springframework.petclinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class VisitSDJPAService implements VisitService {

        private final VisitRepository visitRepository;


    public VisitSDJPAService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    public Set<Visit> findAll() {
        Set<Visit> thisVisit = new HashSet<>();
        visitRepository.findAll().forEach(thisVisit::add);

        return thisVisit;
    }

    @Override
    public Visit findById(Long aLong) {
        return visitRepository.findById(aLong).orElse(null);

    }

    @Override
    public Visit save(Visit object) {

        return visitRepository.save(object);

    }

    @Override
    public void delete(Visit object) {
            visitRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
            visitRepository.deleteById(aLong);
    }
}
