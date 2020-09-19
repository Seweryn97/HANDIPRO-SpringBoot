package com.example.HANDIPRO.models;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "patienttoken")
public class PatientToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String confirmationtoken;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdate;
    @OneToOne
    @JoinColumn(nullable = false, name = "patient_id")
    private Patient patient;

    public PatientToken(){

    }

    public PatientToken(Patient patient){
        this.patient = patient;
        createdate = new Date();
        confirmationtoken = UUID.randomUUID().toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConfirmationtoken() {
        return confirmationtoken;
    }

    public void setConfirmationtoken(String confirmationtoken) {
        this.confirmationtoken = confirmationtoken;
    }

    public Date getCreatdate() {
        return createdate;
    }

    public void setCreatdate(Date creatdate) {
        this.createdate = creatdate;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
