package br.com.henricker.dao;

import br.com.henricker.models.Product;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ProductDAO {
    private EntityManager entityManager;

    public ProductDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Product product) {
        this.entityManager.persist(product);
    }

    public Product findById(Long id) {
        return this.entityManager.find(Product.class, id);
    }

    public List<Product> findByName(String name) {
        String jpql = "SELECT p FROM Product p WHERE p.name = :name";
        return this.entityManager.createQuery(jpql, Product.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Product> findByCategory(String category) {
        String jpql = "SELECT p FROM Product p WHERE p.category.name = :name";
        return this.entityManager.createQuery(jpql, Product.class)
                .setParameter("name", category)
                .getResultList();
    }

    public BigDecimal findPriceByNameProduct(String name) {
        String jpql = "SELECT p.price FROM Product p WHERE p.name = :name";
        return this.entityManager.createQuery(jpql, BigDecimal.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    public List<Product> findAll() {
        String jpql = "SELECT p FROM Product p"; //Specified Product class and not table products
        return this.entityManager.createQuery(jpql, Product.class).getResultList();
    }
}
