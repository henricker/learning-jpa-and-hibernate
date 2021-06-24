package br.com.henricker.models;

import javax.persistence.Entity;

@Entity
public class Tech extends Product {
    
    private String model;
    private String brand;

    public Tech (String model, String brand) {
        this.brand = brand;
        this.model = model;
    }

    public String getModel () {
        return this.model;
    }

    public void setModel (String model) {
        this.model = model;
    }

    public String getBrand () {
        return this.brand;
    }

    public void setBrand (String brand) {
        this.brand = brand;
    }

}
