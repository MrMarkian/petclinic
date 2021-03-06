package com.springframework.petclinic.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="owners")
public class Owner extends Person{

    @Builder
    public Owner(Long id, String firstName, String lastName, String address, String city,
                 String telephone, Set<Pet> pets, Set<Account> account) {
        super(id, firstName, lastName);
        this.address = address;
        this.city = city;
        this.telephone = telephone;
        this.accounts = account;
        if(pets != null) {
            this.pets = pets;
        }
    }

    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "telephone")
    private String telephone;
    @Column(name = "postCode")
    private String postCode;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    @OrderBy("id ASC")
    private Set<Pet> pets = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    @OrderBy("id ASC")
    private Set<Account> accounts = new HashSet<>();

    public Pet getPet(String name){
        return getPet(name, false);
    }



    public String getTotalBalances(){

        Float balanceTotal =0F;

        for(Account a : accounts)
        {
            balanceTotal += a.getBalance();
        }

        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        return decimalFormat.format(balanceTotal);
    }


    public Pet getPet(String name, boolean ignoreNew)
    {
        name = name.toLowerCase();

        for (Pet pet:pets){
            if (!ignoreNew || !pet.isNew()) {
                String compName = pet.getName();
                compName = compName.toLowerCase();
                if(compName.equals(name)){
                    return pet;
                }
            }
        }
        return  null;
    }

}
