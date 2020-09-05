package com.springframework.petclinic.services.map;

import com.springframework.petclinic.model.Payment;
import com.springframework.petclinic.services.PaymentService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class PaymentMapService extends AbstractMapService<Payment, Long> implements PaymentService {

    @Override
    public Set<Payment> findAll() {
        return super.findAll();
    }

    @Override
    public Payment findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Payment save(Payment thisPayment) {
      if(thisPayment.getAccount() == null
      ||    thisPayment.getAccount().getOwner() == null
      ||    thisPayment.getAccount().getId() == null
      ||    thisPayment.getAccount().getOwner().getId() == null) {

          throw new RuntimeException("Invalid / Null Account");
      }

        return super.save(thisPayment);
    }

    @Override
    public void delete(Payment object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}

