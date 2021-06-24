package br.com.henricker.tests;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.henricker.dao.CategoryDAO;
import br.com.henricker.dao.CustomerDAO;
import br.com.henricker.dao.ProductDAO;
import br.com.henricker.dao.RequestDAO;
import br.com.henricker.models.Category;
import br.com.henricker.models.Customer;
import br.com.henricker.models.ItemRequest;
import br.com.henricker.models.Product;
import br.com.henricker.models.Request;
import br.com.henricker.util.JPAUtil;

public class TestLazyException {
    
    public static void main(String[] args) {
        insert();
        EntityManager entityManager = JPAUtil.getEntityManager();
        RequestDAO requestDAO = new RequestDAO(entityManager);
        Request request = requestDAO.selectRequestWithClient(1l);

        entityManager.close();
        System.out.println(request.getCustomer().getName());

    }

    public static void insert() {

        // Transient state
        Category cellphones = new Category("Celulares");
        Product phone = new Product("Xiaomi Redmi note 9", "6GB RAM and 128GB ROMs", new BigDecimal("800"), cellphones);
        Customer customer = new Customer("Henricker", "12311111111");
        Request request = new Request(customer);
        request.addItem(new ItemRequest(phone.getPrice(), 10, request, phone));

        EntityManager entityManager = JPAUtil .getEntityManager();

        // Managed state
        ProductDAO productDAO = new ProductDAO(entityManager);
        CategoryDAO categoryDAO = new CategoryDAO(entityManager);
        CustomerDAO customerDAO = new CustomerDAO(entityManager);
        RequestDAO requestDAO = new RequestDAO(entityManager);

        entityManager.getTransaction().begin();

        categoryDAO.save(cellphones);
        productDAO.save(phone);
        customerDAO.save(customer);
        requestDAO.save(request);

        entityManager.getTransaction().commit();
    }

}
