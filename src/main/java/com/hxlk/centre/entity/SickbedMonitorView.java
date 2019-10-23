package com.hxlk.centre.entity;

import java.io.Serializable;

public class SickbedMonitorView implements Serializable {

    private String sickbed_id;
    private String device_id;
    private int patient_id;
    private String name;
    private String sex;
    private String birthdate;
    private String occur_time;
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
    private String operate_time;
    private String doctor;
    private String nurse;
    private int infusion_sum;

    public SickbedMonitorView(String sickbed_id, String device_id, int patient_id, String name, String sex, String birthdate, String occur_time, double temperature, int sphygmus, int blood_oxygen, double blood_sugar, int urinate_volume, int transfusion_progress, int bladder, int urine_time, int urine_switch, int transfusion_switch, String operate_time, String doctor, String nurse) {
        this.sickbed_id = sickbed_id;
        this.device_id = device_id;
        this.patient_id = patient_id;
        this.name = name;
        this.sex = sex;
        this.birthdate = birthdate;
        this.occur_time = occur_time;
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
        this.operate_time = operate_time;
        this.doctor = doctor;
        this.nurse = nurse;
    }

    public int getInfusion_sum() {
        return infusion_sum;
    }

    public void setInfusion_sum(int infusion_sum) {
        this.infusion_sum = infusion_sum;
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

    public String getOccur_time() {
        return occur_time;
    }

    public void setOccur_time(String occur_time) {
        this.occur_time = occur_time;
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

    public String getOperate_time() {
        return operate_time;
    }

    public void setOperate_time(String operate_time) {
        this.operate_time = operate_time;
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
}
