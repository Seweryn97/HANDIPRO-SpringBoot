package com.example.HANDIPRO.models.DTO;

import com.example.HANDIPRO.models.Physiotherapist;

public class PhysiotherapistUpdateDTO {

    private int id;
    private String email;
    private String password;
    private String repeatedpassword;

    public PhysiotherapistUpdateDTO(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatedpassword() {
        return repeatedpassword;
    }

    public void setRepeatedpassword(String repeatedpassword) {
        this.repeatedpassword = repeatedpassword;
    }
}
