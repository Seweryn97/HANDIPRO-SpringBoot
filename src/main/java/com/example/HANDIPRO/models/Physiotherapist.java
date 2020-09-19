package com.example.HANDIPRO.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;

@Entity
@Table (name = "physiotherapist")
public class Physiotherapist {

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "physiotherapist", fetch = FetchType.LAZY)
    private Set<Patient> patients;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "physiotherapist",fetch = FetchType.LAZY, orphanRemoval = true)
    PhysiotherapistToken physiotherapisttoken;

    public Physiotherapist(){

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

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }

    public boolean isConfirmedemail() {
        return confirmedemail;
    }

    public void setConfirmedemail(boolean confirmedemail) {
        this.confirmedemail = confirmedemail;
    }

    public PhysiotherapistToken getPhysiotherapisttoken() {
        return physiotherapisttoken;
    }

    public void setPhysiotherapisttoken(PhysiotherapistToken physiotherapisttoken) {
        this.physiotherapisttoken = physiotherapisttoken;
    }
}
