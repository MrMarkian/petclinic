package com.springframework.petclinic.model;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;


@MappedSuperclass  //Establishes JPA Base Class.
public class BaseEntity implements Serializable {


    @Id // JPA primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Take the databases method for id generation
    private Long id;

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id =id;
    }
}
