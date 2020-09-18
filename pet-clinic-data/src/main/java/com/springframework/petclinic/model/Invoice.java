package com.springframework.petclinic.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
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

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy = "invoice")
    private Set<ChargeItem> chargeItemSet = new HashSet<>();





}
