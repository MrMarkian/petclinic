package com.springframework.petclinic.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "accounts")
public class Account extends BaseEntity{

    public Account(Long id, Owner owner, Set<Payment> paymentList, String paymentType, Float creditLimit) {
        super(id);
        this.owner = owner;
        if(paymentList == null || paymentList.size() > 0)
        {       this.paymentList = paymentList; }
        this.paymentType = paymentType;
        this.creditLimit = creditLimit;
        this.active = true;
    }

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @OneToMany(mappedBy = "account")
    @OrderBy("paymentDate DESC")
    private Set<Payment> paymentList = new HashSet<>();

    @OneToMany(mappedBy = "account")
    @OrderBy("generatedDate desc")
    private Set<Invoice> invoiceList = new HashSet<>();

    @Column(name = "paymentType")
    @NotNull
    @NotBlank
    @NotEmpty
    private String paymentType;
//todo:make a dropdown in veiw


    @Column(name = "creditLimit")
    private Float creditLimit =0F;

    @Column(name = "creationDate")
    private LocalDate creationDate = LocalDate.now();

    @Column(name = "paymentDueDate")
    private LocalDate paymentDueDate;


    @Column(name = "directDebitId")
    private String directDebitId;

    @Column(name = "active")
    private Boolean active;


    //todo:make a drop down list in view
    @Column(name = "vatLevel")
    private float vatLevel;


    public Float getBalance(){

        Float paymentTotal = 0F;
        Float invoiceTotal = 0F;

        for(Payment p : paymentList){
            paymentTotal += p.getAmount();
        }

        for(Invoice i : invoiceList){
            invoiceTotal += i.getTotal();
        }

        return invoiceTotal - paymentTotal;

    }

    public Float getPaymentTotal(){
        Float paymentTotal = 0F;
        for(Payment p : paymentList){
            paymentTotal += p.getAmount();
        }

        return Float.valueOf(new DecimalFormat("0.00").format(paymentTotal));
    }

    public Long getPaymentCount(){
        return Long.valueOf(new DecimalFormat("0").format( paymentList.size()));
    }

    public Float getInvoiceTotal(){
        Float invoiceTotal = 0F;
        for(Invoice i : invoiceList){
            invoiceTotal += i.getTotal();
        }
        return Float.valueOf(new DecimalFormat("0.00").format(invoiceTotal));
    }

    public String getCreditLimit(){
       return creditLimit.toString();
    }

}
