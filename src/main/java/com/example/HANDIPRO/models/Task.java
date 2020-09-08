package com.example.HANDIPRO.models;

import javax.persistence.*;

@Entity
@Table(name = "Tasks")
public class Task {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String videoName;
    private String csvName;
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
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getCsvName() {
        return csvName;
    }

    public void setCsvName(String csvName) {
        this.csvName = csvName;
    }
}
