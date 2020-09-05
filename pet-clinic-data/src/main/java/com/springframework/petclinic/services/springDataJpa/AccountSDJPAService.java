package com.springframework.petclinic.services.springDataJpa;

import com.springframework.petclinic.model.Account;
import com.springframework.petclinic.repositories.AccountRepository;
import com.springframework.petclinic.services.AccountService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class AccountSDJPAService implements AccountService {

    private final AccountRepository accountRepository;

    public AccountSDJPAService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public Set<Account> findAll() {
        Set<Account> accountSet = new HashSet<>();
        accountRepository.findAll().forEach(accountSet::add);
        return accountSet;
    }

    @Override
    public Account findById(Long aLong) {
        return accountRepository.findById(aLong).orElse(null);
    }

    @Override
    public Account save(Account object) {
        return accountRepository.save(object);
    }

    @Override
    public void delete(Account object) {
        accountRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        accountRepository.deleteById(aLong);
    }
}
