package com.springframework.petclinic.controllers;
import com.springframework.petclinic.model.Account;
import com.springframework.petclinic.model.Payment;
import com.springframework.petclinic.services.AccountService;
import com.springframework.petclinic.services.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/owners/{ownerId}/account/{accountId}/payments")

public class PaymentController {

    private final AccountService accountService;
    private final PaymentService paymentService;

    public PaymentController(AccountService accountService, PaymentService paymentService) {
        this.accountService = accountService;
        this.paymentService = paymentService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder webDataBinder){ //Bind to any local date prroperty on the form and handle
        webDataBinder.setDisallowedFields("id");
        webDataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport(){
            @Override
            public void setAsText(String text) throws IllegalArgumentException{
                setValue(LocalDate.parse(text));
            }
        });
    }

    @ModelAttribute("payment")
    public Payment loadAccountWithPayment(@PathVariable("accountId") Long  accountId, Map<String, Object> model){

           Account account = accountService.findById(accountId);
           model.put("account", account);
           Payment payment = new Payment();
           payment.setInputDate(LocalDate.now());
           account.getPaymentList().add(payment);
           payment.setAccount(account);
           return payment;
    }

    //@GetMapping("/owners/*/account/{accountId}/payments/new")
    @GetMapping("/new")
    public String initNewPaymentForm(@PathVariable("accountId") Long accountId, Map<String, Object> model) {
        log.error("Returning the create or update payments form");
        return "payments/createOrUpdatePaymentForm";
    }

    @PostMapping("/new")
    public String processNewPaymentForm(@Valid Payment payment, BindingResult result){
        if(result.hasErrors()){
            return "payments/createOrUpdatePaymentForm";

        } else {
             paymentService.save(payment);
            return "redirect:/owners/{ownerId}";
        }
    }

    @GetMapping("/{paymentId}/edit")
    public String updatePayment(@PathVariable String paymentId, Model model){
        model.addAttribute("payment", paymentService.findById(Long.valueOf(paymentId)));
        return "payments/createOrUpdatePaymentForm";

    }

    @PostMapping("/{paymentId}/edit")
public String saveOrUpdate(@Valid @ModelAttribute("payment")@PathVariable("paymentId") Long paymentId, Payment payment, BindingResult result){

        if(result.hasErrors()){
            return "payments/createOrUpdatePaymentForm";
        } else {
            payment.setId(paymentId);
             paymentService.save(payment);
            return "redirect:/owners/" + payment.getAccount().getOwner().getId().toString();
        }

    }

}
