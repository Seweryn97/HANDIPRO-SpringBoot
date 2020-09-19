package com.example.HANDIPRO.models;


import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "physiotherapisttoken")
public class PhysiotherapistToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String confirmationtoken;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdate;
    @OneToOne
    Physiotherapist physiotherapist;

    public PhysiotherapistToken(){

    }

    public PhysiotherapistToken(Physiotherapist physiotherapist){
        this.physiotherapist = physiotherapist;
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

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Physiotherapist getPhysiotherapist() {
        return physiotherapist;
    }

    public void setPhysiotherapist(Physiotherapist physiotherapist) {
        this.physiotherapist = physiotherapist;
    }
}
