package br.com.henricker.models;

import javax.persistence.Embeddable;

//Not is table, but your attributes belong to table customer
@Embeddable
public class PersonalData {
    private String name;
    private String cpf;

    public PersonalData() {}

    public PersonalData (String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
    }

    public String getName () {
        return this.name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getCPF () {
        return this.cpf;
    }

    public void setCPF (String cpf) {
        this.cpf = cpf;
    } 
}
