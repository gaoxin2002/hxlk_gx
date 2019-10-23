package com.hxlk.centre.entity;

import java.io.Serializable;

public class PatientMonitorInfo implements Serializable {

    private int id;
    private String occur_time;
    private String device_id;
    private double temperature;
    private int sphygmus;
    private int blood_oxygen;
    private double blood_sugar;
    private int urinate_volume;
    private int transfusion_progress;
    private int bladder;
    private int urine_time;
    private int urine_switch;
    private int transfusion_switch;
    private int Patient_Id;
    private String Patient_name;
    // 追加数据
    private int bedNum; // 床号
    private String doctorName; // 医生姓名
    private String nurseName; // 护士姓名
    private String patientSex;  // 病人性别
    private int patientAge;  // 病人年龄
    private String inHosDate;   // 入院时间
    private int flag1 = 0;  // 医护信息标志位
    private int flag2 = 0;  // 患者信息标志位
    private int qmax;
    private int bloodSugerFlag=0;


    public int getBloodSugerFlag() {
        return bloodSugerFlag;
    }

    public void setBloodSugerFlag(int bloodSugerFlag) {
        this.bloodSugerFlag = bloodSugerFlag;
    }

    public int getQmax() {
        return qmax;
    }

    public void setQmax(int qmax) {
        this.qmax = qmax;
    }

    public int getFlag1() {
        return flag1;
    }

    public void setFlag1(int flag1) {
        this.flag1 = flag1;
    }

    public int getFlag2() {
        return flag2;
    }

    public void setFlag2(int flag2) {
        this.flag2 = flag2;
    }

    public int getBedNum() {
        return bedNum;
    }

    public void setBedNum(int bedNum) {
        this.bedNum = bedNum;
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

    public String getPatientSex() {
        return patientSex;
    }

    public void setPatientSex(String patientSex) {
        this.patientSex = patientSex;
    }

    public int getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(int patientAge) {
        this.patientAge = patientAge;
    }

    public String getInHosDate() {
        return inHosDate;
    }

    public void setInHosDate(String inHosDate) {
        this.inHosDate = inHosDate;
    }

    public PatientMonitorInfo(int id, String occur_time, String device_id, double temperature, int sphygmus, int blood_oxygen,
                              double blood_sugar, int urinate_volume, int transfusion_progress, int bladder, int urine_time,
                              int urine_switch, int transfusion_switch, int Patient_Id, String Patient_name) {
        this.id = id;
        this.occur_time = occur_time;
        this.device_id = device_id;
        this.temperature = temperature;
        this.sphygmus = sphygmus;
        this.blood_oxygen = blood_oxygen;
        this.blood_sugar = blood_sugar;
        this.urinate_volume = urinate_volume;
        this.transfusion_progress = transfusion_progress;
        this.bladder = bladder;
        this.urine_time = urine_time;
        this.urine_switch = urine_switch;
        this.transfusion_switch = transfusion_switch;
        this.Patient_Id = Patient_Id;
        this.Patient_name = Patient_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOccur_time() {
        return occur_time;
    }

    public void setOccur_time(String occur_time) {
        this.occur_time = occur_time;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getSphygmus() {
        return sphygmus;
    }

    public void setSphygmus(int sphygmus) {
        this.sphygmus = sphygmus;
    }

    public int getBlood_oxygen() {
        return blood_oxygen;
    }

    public void setBlood_oxygen(int blood_oxygen) {
        this.blood_oxygen = blood_oxygen;
    }

    public double getBlood_sugar() {
        return blood_sugar;
    }

    public void setBlood_sugar(double blood_sugar) {
        this.blood_sugar = blood_sugar;
    }

    public int getUrinate_volume() {
        return urinate_volume;
    }

    public void setUrinate_volume(int urinate_volume) {
        this.urinate_volume = urinate_volume;
    }

    public int getTransfusion_progress() {
        return transfusion_progress;
    }

    public void setTransfusion_progress(int transfusion_progress) {
        this.transfusion_progress = transfusion_progress;
    }

    public int getBladder() {
        return bladder;
    }

    public void setBladder(int bladder) {
        this.bladder = bladder;
    }

    public int getUrine_time() {
        return urine_time;
    }

    public void setUrine_time(int urine_time) {
        this.urine_time = urine_time;
    }

    public int getUrine_switch() {
        return urine_switch;
    }

    public void setUrine_switch(int urine_switch) {
        this.urine_switch = urine_switch;
    }

    public int getTransfusion_switch() {
        return transfusion_switch;
    }

    public void setTransfusion_switch(int transfusion_switch) {
        this.transfusion_switch = transfusion_switch;
    }

    public int getPatient_Id() {
        return Patient_Id;
    }

    public void setPatient_Id(int patient_Id) {
        Patient_Id = patient_Id;
    }

    public String getPatient_name() {
        return Patient_name;
    }

    public void setPatient_name(String patient_name) {
        Patient_name = patient_name;
    }
}
