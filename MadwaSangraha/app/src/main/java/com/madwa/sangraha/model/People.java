package com.madwa.sangraha.model;

/**
 * Created by sachin on 14/1/16.
 */
public class People {

    private String name;
    private String lastName;
    private int id;
    public People(String name, int id) {
        this.name = name;
        this.lastName = lastName;
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }






}
