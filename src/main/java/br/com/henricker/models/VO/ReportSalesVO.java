package br.com.henricker.models.VO;

import java.time.LocalDate;

public class ReportSalesVO {

    private String productName;
    private Long totalQuantity;
    private LocalDate dateLastSale;

    public ReportSalesVO (String productName, Long totalQuantity, LocalDate dateLastSale) {
        this.dateLastSale = dateLastSale;
        this.productName = productName;
        this.totalQuantity = totalQuantity;
    }


    public String getProductName () {
        return this.productName;
    }

    public void setProductName (String productName) {
        this.productName = productName;
    }

    public Long getTotalQuantity () {
        return this.totalQuantity;
    }

    public void getTotalQuantity (Long totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public LocalDate getDateLastSale () {
        return this.dateLastSale;
    }

    public void setDateLastSale (LocalDate dateLastSale) {
        this.dateLastSale = dateLastSale;
    }

    @Override
    public String toString () {
        return "------- Relatory -------\n" +
               "Product: " + this.productName + "\n" +
               "Quantity: " + this.totalQuantity + "\n" +
               "Date last sale: " + this.dateLastSale;
    }


}
