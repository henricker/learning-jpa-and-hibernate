package br.com.henricker.models;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "item_product")
public class ItemRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal unitPrice;
    private Integer quantity;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Request request;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    public ItemRequest () {}

    public ItemRequest (BigDecimal unitPrice, Integer quantity, Request request, Product product) {
        this.product = product;
        this.request = request;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Long getId () {
        return this.id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public BigDecimal getUnitPrice () {
        return this.unitPrice;
    }

    public void setUnitPrice (BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity () {
        return this.quantity;
    }

    public void setQuantity (Integer quantity) {
        this.quantity = quantity;
    }

    public Request getRequest () {
        return this.request;
    }

    public void setRequest (Request request) {
        this.request = request;
    }

    public Product getProduct () {
        return this.product;
    }

    public void setProduct (Product product) {
        this.product = product;
    }

    public BigDecimal getValue () {
        return this.unitPrice.multiply(new BigDecimal(this.quantity));
    }
}
