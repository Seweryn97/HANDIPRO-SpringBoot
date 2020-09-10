package com.example.HANDIPRO.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "Tasks")
public class Task {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String videofilename;
    private String csvfilename;
    @Lob
    private byte [] videodata;
    @Lob
    private byte [] csvdata;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public Task(){

    }

    public Task(String videoFileName, String csvFileName ,
                byte [] videoData, byte [] csvData){
        this.videofilename = videoFileName;
        this.csvfilename = csvFileName;
        this.videodata = videoData;
        this.csvdata = csvData;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideoFileName() {
        return videofilename;
    }

    public String getCsvFileName() {
        return csvfilename;
    }

    @JsonBackReference
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
