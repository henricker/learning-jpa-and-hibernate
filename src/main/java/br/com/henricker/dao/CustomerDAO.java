package br.com.henricker.dao;

import javax.persistence.EntityManager;

import br.com.henricker.models.Customer;

public class CustomerDAO {
    
    private EntityManager entityManager;

    public CustomerDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Customer customer) {
        this.entityManager.persist(customer);
    }

    public Customer findById(Long id) {
        return this.entityManager.find(Customer.class, id);
    }
}
