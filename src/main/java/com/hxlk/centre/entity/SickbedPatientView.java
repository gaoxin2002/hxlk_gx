package com.hxlk.centre.entity;

import java.io.Serializable;

public class SickbedPatientView implements Serializable {

    private String sickbed_id;
    private String device_id;
    private int patient_id;
    private String name;
    private String sex;
    private String birthdate;
    private String telephone;

    public SickbedPatientView(String sickbed_id, String device_id, int patient_id, String name, String sex, String birthdate, String telephone) {
        this.sickbed_id = sickbed_id;
        this.device_id = device_id;
        this.patient_id = patient_id;
        this.name = name;
        this.sex = sex;
        this.birthdate = birthdate;
        this.telephone = telephone;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
