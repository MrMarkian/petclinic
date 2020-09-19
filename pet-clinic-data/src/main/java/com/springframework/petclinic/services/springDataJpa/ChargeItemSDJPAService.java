package com.springframework.petclinic.services.springDataJpa;

import com.springframework.petclinic.model.ChargeItem;
import com.springframework.petclinic.repositories.ChargeItemRepository;
import com.springframework.petclinic.services.ChargeItemService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class ChargeItemSDJPAService implements ChargeItemService {

    private final ChargeItemRepository chargeItemRepository;

    public ChargeItemSDJPAService(ChargeItemRepository chargeItemRepository) {
        this.chargeItemRepository = chargeItemRepository;
    }

    @Override
    public Set<ChargeItem> findAll() {
        Set<ChargeItem> chargeItemSet = new HashSet<>();
        chargeItemRepository.findAll().forEach(chargeItemSet::add);
        return chargeItemSet;
    }

    @Override
    public ChargeItem findById(Long aLong) {
        return chargeItemRepository.findById(aLong).orElse(null);
    }

    @Override
    public ChargeItem save(ChargeItem object) {
        return chargeItemRepository.save(object);
    }

    @Override
    public void delete(ChargeItem object) {
        chargeItemRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        chargeItemRepository.deleteById(aLong);
    }
}
