package com.example.HANDIPRO.models.DTO;

import com.example.HANDIPRO.models.Patient;
import com.example.HANDIPRO.models.Physiotherapist;

public class PatientUpdateDTO {

    private int id;
    private String email;
    private Physiotherapist physiotherapist;

    public PatientUpdateDTO(){

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

    public Physiotherapist getPhysiotherapist() {
        return physiotherapist;
    }

    public void setPhysiotherapist(Physiotherapist physiotherapist) {
        this.physiotherapist = physiotherapist;
    }
}
