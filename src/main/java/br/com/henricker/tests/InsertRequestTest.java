package br.com.henricker.tests;

import java.math.BigDecimal;
import java.util.List;

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
import br.com.henricker.models.VO.ReportSalesVO;
import br.com.henricker.util.JPAUtil;

public class InsertRequestTest {

    public static void main(String[] args) {
        insert();
        EntityManager entityManager = JPAUtil.getEntityManager();
        ProductDAO productDAO = new ProductDAO(entityManager);
        CustomerDAO customerDAO = new CustomerDAO(entityManager);
        RequestDAO requestDAO = new RequestDAO(entityManager);

        Product product = productDAO.findById(1l);
        Customer customer = customerDAO.findById(1l);

        Request request = new Request(customer);
        request.addItem(new ItemRequest(product.getPrice(), 10, request, product));

        entityManager.getTransaction().begin();
        requestDAO.save(request);
        entityManager.getTransaction().commit();

        BigDecimal totalValue = requestDAO.totalValueSales();
        System.out.println("Total value: " + totalValue);

        List<ReportSalesVO> relatory = requestDAO.salesReport();
        relatory.forEach(System.out::println);
        
        // //Incorrect way, ugly :p
        // for(Object[] object : relatory) {
        //     System.out.println(object[0]);
        //     System.out.println(object[1]);
        //     System.out.println(object[2]);
        // } 

    }

    public static void insert() {

        // Transient state
        Category cellphones = new Category("Celulares");
        Product phone = new Product("Xiaomi Redmi note 9", "6GB RAM and 128GB ROMs", new BigDecimal("800"), cellphones);
        Customer customer = new Customer("Henricker", "12311111111");

        EntityManager entityManager = JPAUtil.getEntityManager();

        // Managed state
        ProductDAO productDAO = new ProductDAO(entityManager);
        CategoryDAO categoryDAO = new CategoryDAO(entityManager);
        CustomerDAO customerDAO = new CustomerDAO(entityManager);

        entityManager.getTransaction().begin();

        categoryDAO.save(cellphones);
        productDAO.save(phone);
        customerDAO.save(customer);
        

        entityManager.getTransaction().commit();
    }
}
