package com.hxlk.centre.entity;

import java.io.Serializable;

public class MedicalWorkerInfo implements Serializable {

    private int id;
    private String name;
    private String duty;

    public MedicalWorkerInfo(int id, String name, String duty) {
        this.id = id;
        this.name = name;
        this.duty = duty;
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

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }
}
