package com.springframework.petclinic.services.map;

import com.springframework.petclinic.model.Account;
import com.springframework.petclinic.services.AccountService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
@Profile({"default","map"})
public class AccountMapService extends AbstractMapService<Account, Long> implements AccountService {

    @Override
    public Set<Account> findAll() {
        return super.findAll();
    }

    @Override
    public Account findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Account save(Account object) {
        return super.save(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Account object) {
        super.delete(object);
    }
}
