package com.springframework.petclinic.model;


import lombok.*;

import javax.persistence.*;
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



    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private Set<Payment> paymentList = new HashSet<>();

    @Column(name = "payment_type")
    private String paymentType;



}
