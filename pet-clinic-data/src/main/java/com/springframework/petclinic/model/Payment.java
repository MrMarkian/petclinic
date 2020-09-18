package com.springframework.petclinic.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payments")
public class Payment extends BaseEntity {

    @Column(name = "amount")
    @NotNull
    private Float amount;
    @Column(name = "paymentdate")
    private LocalDate paymentDate;
    @Column(name = "inputdate")
    private LocalDate inputDate;
    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;


}
