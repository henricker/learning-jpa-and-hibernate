package br.com.henricker.models;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //personal data belong Customer, but not is table
    @Embedded
    private PersonalData personalData;
    
    public Customer() {}

    public Customer(String name, String cpf) {
        this.personalData = new PersonalData(name, cpf);
    }

    public Long getId () {
        return this.id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getName () {
        return this.personalData.getName();
    }

    public void setName (String name) {
        this.personalData.setName(name);
    }

    public String getCPF () {
        return this.personalData.getCPF();
    }

    public void setCPF (String cpf) {
        this.personalData.setCPF(cpf);
    }


}
