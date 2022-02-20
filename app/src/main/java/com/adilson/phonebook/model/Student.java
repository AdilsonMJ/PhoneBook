package com.adilson.phonebook.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Student implements Serializable {
    private int id = 0;
    private  String name;
    private  String cell;
    private  String email;

    public Student(String name, String cell, String email) {
        this.name = name;
        this.cell = cell;
        this.email = email;
    }

    public Student() {

    }


    public String getName() {
        return name;
    }

    public String getCell() {
        return cell;
    }

    public String getEmail() {
        return email;
    }

    public void setID(int countID) {
        this.id = countID;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }


    public boolean hasIdValid() {
        return id > 0;
    }
}
