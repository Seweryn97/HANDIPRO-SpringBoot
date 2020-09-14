package com.example.HANDIPRO.models.DTO;

import com.example.HANDIPRO.models.Physiotherapist;

public class PatientUpdateDTO {
    public String email;
    public String password;
    public String repeatedpassword;
    public Physiotherapist physiotherapist;

    public PatientUpdateDTO(){

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

    public Physiotherapist getPhysiotherapist() {
        return physiotherapist;
    }

    public void setPhysiotherapist(Physiotherapist physiotherapist) {
        this.physiotherapist = physiotherapist;
    }
}
