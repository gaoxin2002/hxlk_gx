package com.hxlk.centre.entity;

import java.io.Serializable;

public class SickbedInfo implements Serializable {
    private int id;
    private String sickbed_id;
    private String device_id;
    private int patient_id;

    public SickbedInfo(int id, String sickbed_id, String device_id, int patient_id) {
        this.id = id;
        this.sickbed_id = sickbed_id;
        this.device_id = device_id;
        this.patient_id = patient_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSickbed_id() {
        return sickbed_id;
    }

    public void setSickbed_id(String sickbed_id) {
        this.sickbed_id = sickbed_id;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }
}
