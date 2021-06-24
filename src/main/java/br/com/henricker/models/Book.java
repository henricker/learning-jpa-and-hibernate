package br.com.henricker.models;

import javax.persistence.Entity;

@Entity
public class Book extends Product {

    private String author;
    private Integer numberPages;
 
    public Book () {}

    public Book (String author, Integer numberPages) {
        this.author = author;
        this.numberPages = numberPages;
    }
    
    public String getAuthor () {
        return this.author;
    }

    public void setAuthor (String author) {
        this.author = author;
    }

    public Integer getNumberPages () {
        return this.numberPages;
    }

    public void setNumberPages (Integer numberPages) {
        this.numberPages = numberPages;
    }
}
