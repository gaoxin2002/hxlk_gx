package com.hxlk.centre.service;

import com.hxlk.centre.entity.vo.PatientMonitorData;

import java.util.List;
import java.util.Map;

// 病人监护数据变更
public interface PatientMonitorService {

    // 新增监护数据
    public boolean insertPatientMonitorInfo(PatientMonitorData patientMonitorData);

    // 获取病人监护信息
    public PatientMonitorData getPatientMonitorInfo(Integer patientId);

    // 修改病人监护信息
    public boolean updatePatientMonitorInfo(PatientMonitorData patientMonitorData);

    // 修改输液总量
    public boolean updatePatientInfusion(Map<String,Object> params);

    // 根据病人ID查询病人体温报表数据
    public List<Map<String,Object>> getTemperatureCharListById(Integer patientId);

    // 开关操作
    public String operatorSwitch(Map<String,Object> params);

    // 根据 病床号和日期查询二级页面报表数据
    public List<Map<String,Object>> selectMonitorDataCharList(Map<String,Object> params);

    // 膀胱训练修改
    public String updatePatientBladder(Map<String,Object> params);

    // 删除病人监护信息 (出院)
    public boolean deletePatientInfoById(Integer patientId);

    // 病床 -> 病人绑定
    public boolean patientBindBed(Integer sickedNum,Integer patientId);

    // 根据病床ID 获取病人ID
    public Integer getPatientIdBySickedNum(Integer sickedNum);

    // 根据设备ID 获取病床编号
    public Integer getSickedNumByDeviceId(String deviceId);

    // 病床 -> 绑定设备
    public boolean deviceBindSicked(Integer sickedNum,String deviceId);

    // 根据设备ID 获取病人信息
    public PatientMonitorData getPatientMonitorDataByDeviceId(String deviceId);

    // 根据病床ID查询病人信息
    public PatientMonitorData getPatientMonitorDataBySickedNum(Integer sickedNum);

    // 标志位置零
    public boolean resetPatientFlag(Integer patientId);
}
