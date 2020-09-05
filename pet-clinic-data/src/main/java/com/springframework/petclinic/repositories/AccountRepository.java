package com.springframework.petclinic.repositories;

import com.springframework.petclinic.model.Account;
import com.springframework.petclinic.model.Payment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface AccountRepository extends CrudRepository <Account, Long> {
}
