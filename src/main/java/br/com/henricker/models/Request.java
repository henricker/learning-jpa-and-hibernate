package br.com.henricker.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "request")
public class Request {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date = LocalDate.now();
    private BigDecimal totalValue = BigDecimal.ZERO;

    //By defualt relationship 'toOne' is eager
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    //Add mappedBy when exists bidirectional relationships
    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL) //Cascade, onDelete onPersist, onUpdate,onRemove
    private List<ItemRequest> itemRequest = new ArrayList<>();
 

    public Request () {}

    public Request (Customer customer) {
        this.customer = customer;
    } 

    public void addItem (ItemRequest itemRequest) {
        this.totalValue = this.totalValue.add(itemRequest.getValue());
        itemRequest.setRequest(this);
        this.itemRequest.add(itemRequest);
    }

    public Long getId () {
        return this.id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public LocalDate getDate () {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal totalValue () {
        return this.totalValue;
    }

    public void setTotalValue (BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public Customer getCustomer () {
        return this.customer;
    }
}
