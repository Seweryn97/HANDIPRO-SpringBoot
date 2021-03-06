package com.example.HANDIPRO.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;

@Entity
@Table (name = "patient")
public class Patient{

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    @Email(message = "Provide Valid Email")
    @Column(unique = true)
    private String email;
    private String password;
    private String repeatedpassword;
    private boolean confirmedemail;
    @ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "physiotherapist_id", nullable = true)
    private Physiotherapist physiotherapist;
    @OneToMany (cascade = CascadeType.ALL,  mappedBy = "patient",fetch = FetchType.LAZY)
    Set<Task> tasks;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "patient",fetch = FetchType.LAZY)
    //@JoinColumn(nullable = false, name = "token")
    private PatientToken patientToken;

    public Patient(){

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

    @JsonBackReference
    public Physiotherapist getPhysiotherapist() {
        return physiotherapist;
    }

    public void setPhysiotherapist(Physiotherapist physiotherapist) {
        this.physiotherapist = physiotherapist;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public boolean isConfirmedemail() {
        return confirmedemail;
    }

    public void setConfirmedemail(boolean confirmedemail) {
        this.confirmedemail = confirmedemail;
    }

    public PatientToken getPatientToken() {
        return patientToken;
    }

    public void setPatientToken(PatientToken patientToken) {
        this.patientToken = patientToken;
    }
}
