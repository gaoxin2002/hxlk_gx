package com.hxlk.centre.entity.vo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * author zerolur
 * 病人监护信息 for redis
 */
public class PatientMonitorData {
    private Integer patientId;  // 病人ID
    private Integer sickbedNum;  // 病床号
    private String patientName;    // 病人姓名
    private String patientPhone;    // 病人姓名
    private String deviceId;    // 设备ID
    private String sex; // 性别
    private Integer age;    // 年龄
    private Date inHosDate; // 入院时间
    private String doctorName;  // 医生姓名
    private String nurseName;   // 护士姓名
    private Integer transfusionSum; // 输液总量
    private Integer transfusionSumFlag = 0; // 输液总量修改标志
    private Integer transfusionProgress; // 输液进度
    private Integer bladder;    // 膀胱训练
    private Integer bladderTimer;    // 膀胱训练倒计时
    private Integer bladderFlag = 0;    // 膀胱训练
    private Integer urineSwitch;    // 尿液开关
    private Integer urineSwitchFlag = 0;    // 尿液开关标志
    private Integer transfusionSwitch;    // 输液开关
    private Integer transfusionSwitchFlag =0;    // 输液开关修改标志
    private Integer flag1 = 0;  // 医护信息标志位1
    private Integer flag2 = 0;  // 患者信息标志位2
    private Integer qMax;   // 尿流率
    private Double weight; // 体重


    // 实体类转Map
    public Map<String,Object> toMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("patientId",patientId);
        map.put("sickbedNum",sickbedNum);
        map.put("patientName",patientName);
        map.put("patientPhone",patientPhone);
        map.put("deviceId",deviceId);
        map.put("sex",sex);
        map.put("age",age);
        map.put("inHosDate",inHosDate);
        map.put("doctorName",doctorName);
        map.put("nurseName",nurseName);
        map.put("transfusionSum",transfusionSum);
        map.put("transfusionProgress",transfusionProgress);
        map.put("bladder",bladder);
        map.put("urineSwitch",urineSwitch);
        map.put("transfusionSwitch",transfusionSwitch);
        map.put("flag1",flag1);
        map.put("flag2",flag2);
        map.put("bladderFlag",bladderFlag);
        map.put("transfusionSumFlag",transfusionSumFlag);
        map.put("transfusionSwitchFlag",transfusionSwitchFlag);
        map.put("urineSwitchFlag",urineSwitchFlag);
        map.put("qMax",qMax);
        map.put("bladderTimer",bladderTimer);
        map.put("weight",weight);
        return map;
    }


    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getBladderTimer() {
        return bladderTimer;
    }

    public void setBladderTimer(Integer bladderTimer) {
        this.bladderTimer = bladderTimer;
    }

    public Integer getqMax() {
        return qMax;
    }

    public void setqMax(Integer qMax) {
        this.qMax = qMax;
    }

    public Integer getTransfusionSumFlag() {
        return transfusionSumFlag;
    }

    public void setTransfusionSumFlag(Integer transfusionSumFlag) {
        this.transfusionSumFlag = transfusionSumFlag;
    }

    public Integer getBladderFlag() {
        return bladderFlag;
    }

    public void setBladderFlag(Integer bladderFlag) {
        this.bladderFlag = bladderFlag;
    }

    public Integer getUrineSwitchFlag() {
        return urineSwitchFlag;
    }

    public void setUrineSwitchFlag(Integer urineSwitchFlag) {
        this.urineSwitchFlag = urineSwitchFlag;
    }

    public Integer getTransfusionSwitchFlag() {
        return transfusionSwitchFlag;
    }

    public void setTransfusionSwitchFlag(Integer transfusionSwitchFlag) {
        this.transfusionSwitchFlag = transfusionSwitchFlag;
    }

    public Integer getTransfusionProgress() {
        return transfusionProgress;
    }

    public void setTransfusionProgress(Integer transfusionProgress) {
        this.transfusionProgress = transfusionProgress;
    }

    public Integer getFlag1() {
        return flag1;
    }

    public void setFlag1(Integer flag1) {
        this.flag1 = flag1;
    }

    public Integer getFlag2() {
        return flag2;
    }

    public void setFlag2(Integer flag2) {
        this.flag2 = flag2;
    }

    public Integer getBladder() {
        return bladder;
    }

    public void setBladder(Integer bladder) {
        this.bladder = bladder;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getSickbedNum() {
        return sickbedNum;
    }

    public void setSickbedNum(Integer sickbedNum) {
        this.sickbedNum = sickbedNum;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getInHosDate() {
        return inHosDate;
    }

    public void setInHosDate(Date inHosDate) {
        this.inHosDate = inHosDate;
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

    public Integer getTransfusionSum() {
        return transfusionSum;
    }

    public void setTransfusionSum(Integer transfusionSum) {
        this.transfusionSum = transfusionSum;
    }

    public Integer getUrineSwitch() {
        return urineSwitch;
    }

    public void setUrineSwitch(Integer urineSwitch) {
        this.urineSwitch = urineSwitch;
    }

    public Integer getTransfusionSwitch() {
        return transfusionSwitch;
    }

    public void setTransfusionSwitch(Integer transfusionSwitch) {
        this.transfusionSwitch = transfusionSwitch;
    }

    public String getPatientPhone() {
        return patientPhone;
    }

    public void setPatientPhone(String patientPhone) {
        this.patientPhone = patientPhone;
    }
}
