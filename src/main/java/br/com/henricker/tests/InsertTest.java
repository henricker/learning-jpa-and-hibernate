package br.com.henricker.tests;

import br.com.henricker.dao.CategoryDAO;
import br.com.henricker.models.Category;
import br.com.henricker.models.Product;
import br.com.henricker.dao.ProductDAO;
import br.com.henricker.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class InsertTest {

    public static void main(String[] args) {
        insert();

        EntityManager entityManager = JPAUtil.getEntityManager();
        ProductDAO productDAO = new ProductDAO(entityManager);
        Product product = productDAO.findById(1l);

        System.out.println(product);

        System.out.println("-------------------");

        System.out.println(productDAO.findAll());

        System.out.println("===================");

        System.out.println(productDAO.findByCategory("Celulares"));

        System.out.println("===================");

        System.out.println(productDAO.findPriceByNameProduct("Xiaomi Redmi note 9"));
    }

    public static void insert() {

        //Transient state
        Category cellphones = new Category("Celulares");
        Product phone = new Product("Xiaomi Redmi note 9", "6GB RAM and 128GB ROMs", new BigDecimal("800"), cellphones);

        EntityManager entityManager = JPAUtil.getEntityManager();

        //Managed state
        ProductDAO productDAO = new ProductDAO(entityManager);
        CategoryDAO categoryDAO = new CategoryDAO(entityManager);

        entityManager.getTransaction().begin();

        categoryDAO.save(cellphones);
        productDAO.save(phone);

        entityManager.getTransaction().commit();
    }

    public void test() {

        //Transient state
        Category cellphones = new Category("Celulares");
        Product phone = new Product("Xiaomi Redmi note 9", "6GB RAM and 128GB ROMs", new BigDecimal("800"), cellphones);

        EntityManager entityManager = JPAUtil.getEntityManager();

        //Managed state
        ProductDAO productDAO = new ProductDAO(entityManager);
        CategoryDAO categoryDAO = new CategoryDAO(entityManager);

        entityManager.getTransaction().begin();

        categoryDAO.save(cellphones);
        productDAO.save(phone);

        entityManager.flush();
        entityManager.clear();

        phone = entityManager.merge(phone); // merge -> Return object with managed state
        phone.setPrice(new BigDecimal("890"));
        entityManager.flush(); //flush -> Synchronize with database
        entityManager.remove(phone); //removed state
        entityManager.flush();
        entityManager.close();
        //Detached state
    }


}
