package com.springframework.petclinic.controllers;


import com.springframework.petclinic.model.Account;
import com.springframework.petclinic.model.Invoice;
import com.springframework.petclinic.model.Owner;
import com.springframework.petclinic.services.AccountService;
import com.springframework.petclinic.services.InvoiceService;
import com.springframework.petclinic.services.OwnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/owners/{ownerId}/account/{accountId}/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;
    private  final AccountService accountService;
    private final OwnerService ownerService;

    private final String VIEWS_INVOICE_CREATE_OR_UPDATE_FORM = "/invoice/createOrUpdateInvoiceForm";


    public InvoiceController(InvoiceService invoiceService, AccountService accountService, OwnerService ownerService) {
        this.invoiceService = invoiceService;
        this.accountService = accountService;
        this.ownerService = ownerService;
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable Long ownerId)
    {
        return ownerService.findById(ownerId);
    }

    @ModelAttribute("invoice")
    public Invoice loadAccountWithInvoice(@PathVariable("accountId") Long accountId, Map<String, Object> model){

        Account account = accountService.findById(accountId);
        model.put("account", account);
        Invoice invoice = new Invoice();
        account.getInvoiceList().add(invoice);
        invoice.setAccount(account);
        invoice.setDueDate(invoice.calculateNextDuePayment());
        invoice.setVatLevel(account.getVatLevel());
        return invoice;

    }

    @GetMapping("/new")
    public String initCreationForm (Account account, Model model){
        log.debug("Returning the Create / Update Invoice Form");
        return VIEWS_INVOICE_CREATE_OR_UPDATE_FORM;
    }

    @GetMapping("/new/addcharge")
    public String addChargeToInvoice (Account account, Model model){
        log.debug("Returning the Create / Update Invoice Form");

        return VIEWS_INVOICE_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/new")
    public String processCreationForm(Account account, @Valid Invoice invoice, BindingResult result, Model model)
    {
        account.getInvoiceList().add(invoice);
        invoice.setAccount(account);
            invoiceService.save(invoice);
        accountService.save(account);
        return "redirect:/owners/{ownerId}";
    }

}
