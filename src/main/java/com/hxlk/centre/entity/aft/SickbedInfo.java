package com.hxlk.centre.entity.aft;

import java.io.Serializable;

public class SickbedInfo implements Serializable {

    private Integer id;

    private Integer sickbedId;

    private String deviceId;

    private Integer patientId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSickbedId() {
        return sickbedId;
    }

    public void setSickbedId(Integer sickbedId) {
        this.sickbedId = sickbedId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }
}