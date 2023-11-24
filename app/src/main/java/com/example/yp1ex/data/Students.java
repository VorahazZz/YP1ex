package com.example.yp1ex.data;

import java.io.Serializable;

public class Students implements Serializable {
    private int id;
    private String firstName;
    private String secondName;
    private String surname;
    private String date;
    private int idGroup;

    public Students(String secondName, String firstName, String surname, String date, int idGroup) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.surname = surname;
        this.date = date;
        this.idGroup = idGroup;
    }

    public Students(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }
}
