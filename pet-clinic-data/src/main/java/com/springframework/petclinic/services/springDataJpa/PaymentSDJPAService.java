package com.springframework.petclinic.services.springDataJpa;

import com.springframework.petclinic.model.Payment;
import com.springframework.petclinic.repositories.PaymentRepository;
import com.springframework.petclinic.services.PaymentService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class PaymentSDJPAService implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentSDJPAService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Set<Payment> findAll() {
        Set<Payment> thisPayment = new HashSet<>();
        paymentRepository.findAll().forEach(thisPayment :: add);

        return thisPayment;
    }

    @Override
    public Payment findById(Long aLong) {
        return paymentRepository.findById(aLong).orElse(null);
    }

    @Override
    public Payment save(Payment object) {
        return paymentRepository.save(object);
    }

    @Override
    public void delete(Payment object) {
        paymentRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        paymentRepository.deleteById(aLong);
    }
}
