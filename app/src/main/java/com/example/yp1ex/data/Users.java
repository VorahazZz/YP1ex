package com.example.yp1ex.data;

public class Users {
    private int id;
    private String login;
    private String pass;
    private String phone;

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Users() {
    }

    public Users(String login, String pass, String phone) {
        this.login = login;
        this.pass = pass;
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}

