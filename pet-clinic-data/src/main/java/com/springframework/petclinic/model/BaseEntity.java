package com.springframework.petclinic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass  //Establishes JPA Base Class.
public class BaseEntity implements Serializable {


    @Id // JPA primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Take the databases method for id generation
    private Long id;

    public boolean isNew(){
        return this.id == null;
    }

}
