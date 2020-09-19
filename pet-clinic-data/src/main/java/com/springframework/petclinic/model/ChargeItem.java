package com.springframework.petclinic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "items")
public class ChargeItem extends BaseEntity {

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Float itemPrice;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;
}
