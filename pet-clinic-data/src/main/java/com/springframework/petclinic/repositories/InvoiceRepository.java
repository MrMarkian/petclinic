package com.springframework.petclinic.repositories;

import com.springframework.petclinic.model.Invoice;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

}
