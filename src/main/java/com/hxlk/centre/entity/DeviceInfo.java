package com.hxlk.centre.entity;

import java.io.Serializable;

public class DeviceInfo implements Serializable {

    private int id;
    private String device_id;
    private String production_date;
    private String setup_date;

    public DeviceInfo(int id, String device_id, String production_date, String setup_date) {
        this.id = id;
        this.device_id = device_id;
        this.production_date = production_date;
        this.setup_date = setup_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getProduction_date() {
        return production_date;
    }

    public void setProduction_date(String production_date) {
        this.production_date = production_date;
    }

    public String getSetup_date() {
        return setup_date;
    }

    public void setSetup_date(String setup_date) {
        this.setup_date = setup_date;
    }
}
