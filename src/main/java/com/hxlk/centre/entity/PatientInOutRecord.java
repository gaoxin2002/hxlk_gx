package com.hxlk.centre.entity;

import java.io.Serializable;

public class PatientInOutRecord implements Serializable {

    private int id;
    private int patient_id;
    private String in_out;
    private String operate_time;
    private String department;
    private String operator;
    private String doctor;
    private String nurse;
    private String sickbed_id;

    @Override
    public String toString() {
        return "PatientInOutRecord{" +
                "id=" + id +
                ", patient_id=" + patient_id +
                ", in_out='" + in_out + '\'' +
                ", operate_time='" + operate_time + '\'' +
                ", department='" + department + '\'' +
                ", operator='" + operator + '\'' +
                ", doctor='" + doctor + '\'' +
                ", nurse='" + nurse + '\'' +
                ", sickbed_id='" + sickbed_id + '\'' +
                '}';
    }

    public PatientInOutRecord(int id, int patient_id, String in_out, String operate_time, String department, String operator, String doctor, String nurse, String sickbed_id) {
        this.id = id;
        this.patient_id = patient_id;
        this.in_out = in_out;
        this.operate_time = operate_time;
        this.department = department;
        this.operator = operator;
        this.doctor = doctor;
        this.nurse = nurse;
        this.sickbed_id = sickbed_id;
    }

    public PatientInOutRecord() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public String getIn_out() {
        return in_out;
    }

    public void setIn_out(String in_out) {
        this.in_out = in_out;
    }

    public String getOperate_time() {
        return operate_time;
    }

    public void setOperate_time(String operate_time) {
        this.operate_time = operate_time;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getNurse() {
        return nurse;
    }

    public void setNurse(String nurse) {
        this.nurse = nurse;
    }

    public String getSickbed_id() {
        return sickbed_id;
    }

    public void setSickbed_id(String sickbed_id) {
        this.sickbed_id = sickbed_id;
    }
}
