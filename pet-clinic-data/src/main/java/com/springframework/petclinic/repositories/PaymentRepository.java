package com.springframework.petclinic.repositories;

import com.springframework.petclinic.model.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long> {

}

