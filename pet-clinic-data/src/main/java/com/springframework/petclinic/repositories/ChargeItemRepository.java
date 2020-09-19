package com.springframework.petclinic.repositories;

import com.springframework.petclinic.model.ChargeItem;
import org.springframework.data.repository.CrudRepository;

public interface ChargeItemRepository extends CrudRepository<ChargeItem, Long> {

}
