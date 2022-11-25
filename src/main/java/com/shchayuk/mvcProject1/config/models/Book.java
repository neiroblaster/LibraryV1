package com.shchayuk.mvcProject1.config.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

public class Book {

    private int id;

    @NotEmpty(message = "Enter the name of the book")
    @Size(min = 2, max = 100, message = "Enter the name of the book form 2 to 100 characters")
    private String name;
    @NotEmpty(message = "Enter the name of the book")
    @Size(min = 2, max = 100, message = "Enter the name of the book form 2 to 100 characters")
    private String author;
    @Min(value = 1800, message = "The year should be greater than 1800")
    @Max(value = 2022, message = "The year should be less than 2022")
    private int year;
    boolean isLent;

    public Book() {
    }

    public Book(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
        isLent = false;
    }

    public boolean isLent() {
        return isLent;
    }

    public void setLent(boolean lent) {
        isLent = lent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
