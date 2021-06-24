package br.com.henricker.dao;

import br.com.henricker.models.Category;

import javax.persistence.EntityManager;

public class CategoryDAO {

    private EntityManager entityManager;

    public CategoryDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Category category) {
        this.entityManager.persist(category);
    }

    public void update(Category category) {
        this.entityManager.merge(category);
    }

    public void remove(Category category) {
        category = this.entityManager.merge(category); //Now category is on managed state
        this.entityManager.remove(category);
    }
}
