package br.com.henricker.dao;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import java.util.List;

import br.com.henricker.models.VO.ReportSalesVO;
import br.com.henricker.models.Request;

public class RequestDAO {

    private EntityManager entityManager;

    public RequestDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public void save(Request request) {
        this.entityManager.persist(request);
    }

    public Request findById (Long id) {
        return this.entityManager.find(Request.class, id);
    }

    public BigDecimal totalValueSales () {
        String jpql = "SELECT SUM(r.totalValue) FROM Request r";
        return this.entityManager.createQuery(jpql, BigDecimal.class)
        .getSingleResult();
    }

    //To avoid LazyException create a query with FETCH
    public Request selectRequestWithClient(Long id) {
        String jpql = "SELECT request FROM Request request JOIN FETCH request.customer WHERE request.id = :id";
        return this.entityManager
                .createQuery(jpql, Request.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    //Best way! :D
    public List<ReportSalesVO> salesReport () {
        String jpql = "SELECT new br.com.henricker.models.VO.ReportSalesVO(" + 
                      "product.name, " +
                      "SUM(item.quantity), " +
                      "MAX(request.date))" +
                      "FROM Request request " +
                      "JOIN request.itemRequest item " +
                      "JOIN item.product product " +
                      "GROUP BY product.name " +
                      "ORDER BY item.quantity DESC";
        
        return this.entityManager
                .createQuery(jpql, ReportSalesVO.class)
                .getResultList();
    }

    //Incorrect way, ugly :p
    // public List<Object[]> salesReport () {
    //     String jpql = "SELECT " + 
    //                   "product.name, " +
    //                   "SUM(item.quantity), " +
    //                   "MAX(request.date) " +
    //                   "FROM Request request " +
    //                   "JOIN request.itemRequest item " +
    //                   "JOIN item.product product " +
    //                   "GROUP BY product.name " +
    //                   "ORDER BY item.quantity DESC";
        
    //     return this.entityManager
    //             .createQuery(jpql, Object[].class)
    //             .getResultList();

    // }


}
