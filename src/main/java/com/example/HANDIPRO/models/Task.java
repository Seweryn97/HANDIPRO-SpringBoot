package com.example.HANDIPRO.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "Tasks")
public class Task {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String videoname;
    private String csvname;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    Task(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideoName() {
        return videoname;
    }

    public void setVideoName(String videoName) {
        this.videoname = videoName;
    }

    public String getCsvName() {
        return csvname;
    }

    public void setCsvName(String csvName) {
        this.csvname = csvName;
    }

    @JsonBackReference
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
