package com.shchayuk.mvcProject1.config.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {
    private int id;

//    @Size(min = 2, max = 100, message = "Enter the name form 2 to 100 characters")
    private String name;
//    @Min(value = 1921, message = "Enter the year greater than 1920")
//    @Max(value = 2015, message = "Enter the year less than 2016")
    private int year;

    public Person() {
    }

    public Person(int id, String name, int year) {
        this.id = id;
        this.name = name;
        this.year = year;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
