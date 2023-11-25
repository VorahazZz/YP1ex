package com.example.yp1ex.data;

import java.io.Serializable;

public class Groups implements Serializable {
    private int id;
    private int number;
    private String name;
    private boolean isSelected;

    public Groups(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public boolean isSelected(){
        return isSelected;
    }
    public void setSelected(boolean selected){
        isSelected = selected;
    }

    public Groups(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
