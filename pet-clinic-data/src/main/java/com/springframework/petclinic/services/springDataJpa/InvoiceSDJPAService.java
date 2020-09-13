package com.springframework.petclinic.services.springDataJpa;

import com.springframework.petclinic.model.Invoice;
import com.springframework.petclinic.repositories.InvoiceRepository;
import com.springframework.petclinic.services.InvoiceService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class InvoiceSDJPAService implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceSDJPAService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public Set<Invoice> findAll() {
        Set<Invoice> invoiceSet = new HashSet<>();
        invoiceRepository.findAll().forEach(invoiceSet::add);

        return invoiceSet;
    }

    @Override
    public Invoice findById(Long aLong) {
        return invoiceRepository.findById(aLong).orElse(null);
    }

    @Override
    public Invoice save(Invoice object) {
        return invoiceRepository.save(object);
    }

    @Override
    public void delete(Invoice object) {
        invoiceRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        invoiceRepository.deleteById(aLong);
    }
}
