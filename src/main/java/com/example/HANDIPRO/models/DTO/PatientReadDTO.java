package com.example.HANDIPRO.models.DTO;

import com.example.HANDIPRO.models.Patient;

public class PatientReadDTO {

    private int id;
    private String name;
    private String surname;

    public PatientReadDTO(Patient patient){
        id = patient.getId();
        name = patient.getName();
        surname = patient.getSurname();
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
