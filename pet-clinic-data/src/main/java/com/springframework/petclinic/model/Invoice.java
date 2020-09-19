package com.springframework.petclinic.model;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.MonthDay;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "invoices")
public class Invoice extends BaseEntity {

    private Float total;
    private Float subtotal;
    private LocalDate generatedDate = LocalDate.now();
    private LocalDate dueDate;
    private Integer minPaymentNoticeTime = 5;
    private float vatLevel;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy = "invoice")
    private Set<ChargeItem> chargeItemSet = new HashSet<>();


    public LocalDate calculateNextDuePayment(){

        if(account.getPaymentDueDate() < LocalDate.now().getDayOfMonth()){
            return LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue() +1, account.getPaymentDueDate());
        } else {
            int dateDiff =  account.getPaymentDueDate() - LocalDate.now().getDayOfMonth();
            if ( dateDiff <= minPaymentNoticeTime) {
                return LocalDate.now().plusDays(minPaymentNoticeTime);
            }
        }
            return LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), account.getPaymentDueDate());
    }

    public void setVatLevel(float vat){
        vatLevel = vat;
    }

}
