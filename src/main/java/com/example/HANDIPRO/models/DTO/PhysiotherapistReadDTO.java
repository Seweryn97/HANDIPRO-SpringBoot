package com.example.HANDIPRO.models.DTO;

import com.example.HANDIPRO.models.Physiotherapist;

import java.util.Set;
import java.util.stream.Collectors;

public class PhysiotherapistReadDTO {

    private int id;
    private String name;
    private String surname;
    private Set<PatientReadDTO> patients;

    public PhysiotherapistReadDTO(Physiotherapist physiotherapist){
        id = physiotherapist.getId();
        name = physiotherapist.getName();
        surname = physiotherapist.getSurname();
        patients = physiotherapist.getPatients().stream().map(PatientReadDTO::new).collect(Collectors.toSet());

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

    public Set<PatientReadDTO> getPatients() {
        return patients;
    }

    public void setPatients(Set<PatientReadDTO> patients) {
        this.patients = patients;
    }
}
