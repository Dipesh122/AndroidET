package com.project.expenseTracker.book;

public class Book {
    private String name;
    private int id;
    private int currency;



    public Book() {
        this.name = "";
    }
    //constructor while getting date
    public Book(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public int getCurrency() {
        return currency;
    }
}
