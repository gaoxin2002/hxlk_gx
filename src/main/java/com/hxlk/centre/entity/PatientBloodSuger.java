package com.hxlk.centre.entity;

import java.util.Date;


public class PatientBloodSuger {
    private Integer id;

    private Double bloodSuger;

    private Date ts;

    private Integer patientId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getBloodSuger() {
        return bloodSuger;
    }

    public void setBloodSuger(Double bloodSuger) {
        this.bloodSuger = bloodSuger;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }
}