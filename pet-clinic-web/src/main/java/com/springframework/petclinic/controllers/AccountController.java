package com.springframework.petclinic.controllers;

import com.springframework.petclinic.model.Account;
import com.springframework.petclinic.model.Owner;
import com.springframework.petclinic.services.AccountService;
import com.springframework.petclinic.services.OwnerService;
import com.springframework.petclinic.services.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/owners/{ownerId}/account")
public class AccountController {

    private final AccountService accountService;
    private final PaymentService paymentService;

    private final OwnerService ownerService;

    private final String VIEWS_ACCOUNT_CREATE_OR_UPDATE_FORM = "/account/createOrUpdateAccountForm";

    public AccountController(AccountService accountService, PaymentService paymentService, OwnerService ownerService) {
        this.accountService = accountService;
        this.paymentService = paymentService;
        this.ownerService = ownerService;
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable Long ownerId){
        return ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder){

        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/new")
    public String initCreationForm (Owner owner, Model model){
        Account account = new Account();
        account.setActive(true);
        owner.getAccounts().add(account);
        account.setOwner(owner);

        model.addAttribute("account", account);
        return VIEWS_ACCOUNT_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/new")
    public String processCreationForm(Owner owner, @Valid Account account, BindingResult result, Model model){
        //todo: add null checks

        if(result.hasErrors()) {
            model.addAttribute("account", account);
            model.addAttribute("owner", owner);
            return VIEWS_ACCOUNT_CREATE_OR_UPDATE_FORM;
        }

        if(account.getPaymentType().isBlank()) {
            model.addAttribute("owner",owner);
            model.addAttribute("account", account);
        return VIEWS_ACCOUNT_CREATE_OR_UPDATE_FORM;


        } else {
            owner.getAccounts().add(account);
            account.setOwner(owner);
            account.setActive(true);

            accountService.save(account);
            account.generateAccountCode(); //todo: this shouldnt be here.. should be in the constructor. fix it!
            accountService.save(account);
            return "redirect:/owners/{ownerId}";
        }
    }

    @GetMapping("/{accountId}/edit")
    public String initUpdateAccountForm(@PathVariable("accountId") Long accountId, Model model){
        model.addAttribute("account", accountService.findById(accountId));
        log.debug("Serving the Edit Account Form");
        return VIEWS_ACCOUNT_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/{accountId}/edit")
    public String processUpdateAccountForm(@Valid Account account, Owner owner, BindingResult result, @PathVariable("accountId") Long accountId, @PathVariable("ownerId") Long ownerId, Model model){
        if(result.hasErrors()){
            return  VIEWS_ACCOUNT_CREATE_OR_UPDATE_FORM;
        } else {
            log.error("Payment Due Date:"+ account.getPaymentDueDate().toString());
            account.setId(accountId);
            account.setOwner(owner);

            Account savedAccount = accountService.save(account);
            return "redirect:/owners/" + owner.getId();
        }

    }

}
