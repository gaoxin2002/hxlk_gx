package com.hxlk.centre.entity;

import java.io.Serializable;
import java.util.Date;

public class PatientInfo implements Serializable {

    private int id;
    private String name;
    private String sex;
    private String birthdate;
    private String telephone;
    private int status;
    private int record_id;
    private int infusion_sum;
    private int age;
    private String doctorName;
    private String nurseName;
    private Date inHosTime;
    private double weight;


    public PatientInfo(){

    }

    public PatientInfo(int id, String name, String sex, String birthdate, String telephone, int status,
                       int record_id,int infusion_sum,int age,String doctorName,String nurseName,Date inHosTime,double weight) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.birthdate = birthdate;
        this.telephone = telephone;
        this.status = status;
        this.record_id = record_id;
        this.infusion_sum=infusion_sum;
        this.age=age;
        this.doctorName=doctorName;
        this.nurseName=nurseName;
        this.inHosTime=inHosTime;
        this.weight=weight;
    }

    @Override
    public String toString() {
        return "PatientInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", telephone='" + telephone + '\'' +
                ", status=" + status +
                ", record_id=" + record_id +
                ", infusion_sum=" + infusion_sum +
                ", age=" + age +
                ", doctorName='" + doctorName + '\'' +
                ", nurseName='" + nurseName + '\'' +
                ", inHosTime=" + inHosTime +
                '}';
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getNurseName() {
        return nurseName;
    }

    public void setNurseName(String nurseName) {
        this.nurseName = nurseName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getInfusion_sum() {
        return infusion_sum;
    }

    public void setInfusion_sum(int infusion_sum) {
        this.infusion_sum = infusion_sum;
    }

    public Date getInHosTime() {
        return inHosTime;
    }

    public void setInHosTime(Date inHosTime) {
        this.inHosTime = inHosTime;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRecord_id() {
        return record_id;
    }

    public void setRecord_id(int record_id) {
        this.record_id = record_id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
