package com.hxlk.centre.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hxlk.centre.entity.vo.PatientMonitorData;
import com.hxlk.centre.mapper.PatientMonitorMapper;
import com.hxlk.centre.service.PatientMonitorService;
import com.hxlk.centre.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

@Service
public class PatientMonitorServiceImpl implements PatientMonitorService {
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    PatientMonitorMapper patientMonitorMapper;
    // 病人监护基本信息 前缀
    private final String monitorBaseInfoPrefix = "monitor_patient_info_";
    // 病人开关数据前缀
    private final String monitorSwitchPrefix = "monitor_patient_switch_";
    // 病床绑定病人ID
    private final String monitorSickedPrefix = "monitor_sicked_hash";
    // 设备绑定病床编号
    private final String monitorDeviceIdPrefix = "monitor_deviceId_hash";


    // 新增/修改病人监护信息 -> redis
    @Override
    public boolean insertPatientMonitorInfo(PatientMonitorData patientMonitorData) {
        return redisUtil.hmset(monitorBaseInfoPrefix+patientMonitorData.getPatientId(),patientMonitorData.toMap());
    }
    // 根据ID获取病人监护信息
    @Override
    public PatientMonitorData getPatientMonitorInfo(Integer patientId) {
        if (null != patientId && patientId > 0){
            Map<Object, Object> resMap = redisUtil.hmget(monitorBaseInfoPrefix+patientId);
            PatientMonitorData patientMonitorData = JSONObject.parseObject(JSON.toJSONString(resMap),PatientMonitorData.class);
            return patientMonitorData;
        }
        return null;
    }
    // 修改病人监护信息
    @Override
    public boolean updatePatientMonitorInfo(PatientMonitorData patientMonitorData) {
        Integer patientId =  patientMonitorData.getPatientId();
        if (null != patientId && patientId > 0){
            PatientMonitorData redisInfo = getPatientMonitorInfo(patientId);    // redis
            redisInfo.setSickbedNum(patientMonitorData.getSickbedNum()== null?redisInfo.getSickbedNum():patientMonitorData.getSickbedNum());
            redisInfo.setPatientName(patientMonitorData.getPatientName() == null?redisInfo.getPatientName():patientMonitorData.getPatientName());
            redisInfo.setPatientPhone(patientMonitorData.getPatientPhone() == null?redisInfo.getPatientPhone():patientMonitorData.getPatientPhone());
            redisInfo.setSex(patientMonitorData.getSex() == null?redisInfo.getSex():patientMonitorData.getSex());
            redisInfo.setAge(patientMonitorData.getAge() == null?redisInfo.getAge():patientMonitorData.getAge());
            redisInfo.setInHosDate(patientMonitorData.getInHosDate() == null?redisInfo.getInHosDate():patientMonitorData.getInHosDate());
            redisInfo.setDoctorName(patientMonitorData.getDoctorName() == null?redisInfo.getDoctorName():patientMonitorData.getDoctorName());
            redisInfo.setNurseName(patientMonitorData.getNurseName() == null?redisInfo.getNurseName():patientMonitorData.getNurseName());
            redisInfo.setTransfusionSum(patientMonitorData.getTransfusionSum() == null?redisInfo.getTransfusionSum():patientMonitorData.getTransfusionSum());
            redisInfo.setWeight(patientMonitorData.getWeight());
            // 医护信息修改标志位
            if (null != patientMonitorData.getDoctorName() && !patientMonitorData.getDoctorName().equals(redisInfo.getDoctorName()) ){
                redisInfo.setFlag1(1);
            }
            if (null != patientMonitorData.getNurseName() && !patientMonitorData.getNurseName().equals(redisInfo.getNurseName())){
                redisInfo.setFlag1(1);
            }
            // 患者信息修改标志位
            if (null != patientMonitorData.getPatientName() && !patientMonitorData.getPatientName().equals(redisInfo.getPatientName())){
                redisInfo.setFlag2(1);
            }
            if (null != patientMonitorData.getSex() && patientMonitorData.getSex() != redisInfo.getSex()){
                redisInfo.setFlag2(1);
            }
            if (null != patientMonitorData.getAge() && patientMonitorData.getAge() != redisInfo.getAge()){
                redisInfo.setFlag2(1);
            }
            if (null != redisInfo.getInHosDate() && null != patientMonitorData.getInHosDate() && patientMonitorData.getInHosDate().getTime() != redisInfo.getInHosDate().getTime()){
                redisInfo.setFlag2(1);
            }
            if (null != patientMonitorData.getWeight() && !patientMonitorData.getWeight().equals(redisInfo.getWeight())){
                redisInfo.setFlag2(1);
            }
            redisInfo.setTransfusionSumFlag(patientMonitorData.getTransfusionSum()==null?null:1);
            redisInfo.setUrineSwitchFlag(patientMonitorData.getUrineSwitchFlag() == null?0:patientMonitorData.getUrineSwitchFlag());
            redisInfo.setTransfusionSwitchFlag(patientMonitorData.getTransfusionSwitchFlag() == null?0:patientMonitorData.getTransfusionSwitchFlag());
            redisInfo.setBladderFlag(patientMonitorData.getBladder()==null?0:patientMonitorData.getBladder() > 0?1:0);
            // 下位机交互数据
            redisInfo.setBladderTimer(patientMonitorData.getBladderTimer() == null?redisInfo.getBladderTimer():patientMonitorData.getBladderTimer());
            redisInfo.setTransfusionProgress(patientMonitorData.getTransfusionProgress() == null?redisInfo.getTransfusionProgress():patientMonitorData.getTransfusionProgress());
            redisInfo.setUrineSwitch(patientMonitorData.getUrineSwitch()==null?redisInfo.getUrineSwitch():patientMonitorData.getUrineSwitch());
            redisInfo.setTransfusionSwitch(patientMonitorData.getTransfusionSwitch()==null?redisInfo.getTransfusionSwitch():patientMonitorData.getTransfusionSwitch());
            return redisUtil.hmset(monitorBaseInfoPrefix+redisInfo.getPatientId(),redisInfo.toMap());
        }
        return false;
    }

    @Override
    public List<Map<String, Object>> getTemperatureCharListById(Integer patientId) {
        return null;
    }
    // 开关操作 存放 redis
    @Override
    public String operatorSwitch(Map<String, Object> params) {
        // 病人ID
        Integer patientId = params.get("patientId") == null?null:Integer.parseInt(params.get("patientId").toString());
        // 尿袋开关
        Integer urineSwitch = params.get("urineSwitch") == null?null:Integer.parseInt(params.get("urineSwitch").toString());
        // 输液开关
        Integer transfusionSwitch = params.get("transfusionSwitch") == null?null:Integer.parseInt(params.get("transfusionSwitch").toString());
        // 修改标志 尿袋-> 1  输液 2
        Integer flag = params.get("flag") == null?null:Integer.parseInt(params.get("flag").toString());
        if (null != flag && null !=patientId && patientId > 0 && null != urineSwitch && urineSwitch > -1 && null != transfusionSwitch && transfusionSwitch > -1){
            PatientMonitorData patientMonitorData = getPatientMonitorInfo(patientId);   // 获取redis 缓存的病人信息
            patientMonitorData.setUrineSwitch(urineSwitch);
            patientMonitorData.setTransfusionSwitch(transfusionSwitch);
            patientMonitorData.setUrineSwitchFlag(flag == 1?1:0);
            patientMonitorData.setTransfusionSwitchFlag(flag == 2?1:0);
            boolean operatorRes = updatePatientMonitorInfo(patientMonitorData);   // 保留十天
            if (operatorRes){
                return "success";
            }else{
                return "error for insert to redis";
            }
        }else{
            return "error for params...";
        }
    }

    // 根据 病人ID和日期查询二级页面报表数据
    @Override
    public List<Map<String,Object>> selectMonitorDataCharList(Map<String,Object> params){
        return patientMonitorMapper.selectMonitorDataCharList(params);
    }
    // 膀胱训练计时修改
    @Override
    public String updatePatientBladder(Map<String, Object> params) {
        // 病人ID
        Integer patientId = params.get("patientId") == null?null:Integer.parseInt(params.get("patientId").toString());
        // 膀胱计时 5-180
        Integer bladder = params.get("bladder") == null?null:Integer.parseInt(params.get("bladder").toString());
        if (null != patientId && patientId > 0 && null != bladder && bladder > 4 && bladder < 181){
            PatientMonitorData patientMonitorData = getPatientMonitorInfo(patientId);   // 获取redis 缓存的病人信息
            patientMonitorData.setBladder(bladder);
            patientMonitorData.setBladderFlag(1);   // 膀胱训练修改标志
            boolean operatorRes = insertPatientMonitorInfo(patientMonitorData);   // 保留十天
            if (operatorRes){
                return "success";
            }else{
                return "error for insert to redis";
            }
        }else{
            return "error for params...";
        }
    }
    // 删除病人监护信息
    @Override
    public boolean deletePatientInfoById(Integer patientId) {
        if (null != patientId && patientId > 0){
            redisUtil.del(monitorBaseInfoPrefix+patientId);
        }else{
            return false;
        }
        return true;
    }
    // 病床 -> 病人绑定
    @Override
    public boolean patientBindBed(Integer sickedNum, Integer patientId) {
        if (null != sickedNum && sickedNum > 0 && null != patientId && patientId > 0){
            return redisUtil.hset(monitorSickedPrefix,String.valueOf(sickedNum),patientId);
        }
        return false;
    }
    // 根据病床ID  获取病人ID
    @Override
    public Integer getPatientIdBySickedNum(Integer sickedNum) {
        if (null != sickedNum && sickedNum > 0){
            Object res = redisUtil.hget(monitorSickedPrefix,String.valueOf(sickedNum));
            Integer patientId = res == null?null:Integer.parseInt(res.toString());
            if (null != patientId && patientId > 0){
                return patientId;
            }else{
                // 查询数据库 todo
            }
        }
        return null;
    }
    // 根据设备Id 获取病床编号
    @Override
    public Integer getSickedNumByDeviceId(String deviceId) {
        if (null != deviceId && !"".equals(deviceId)){
            Object res = redisUtil.hget(monitorDeviceIdPrefix,deviceId);
            Integer sickedNum = res == null?null:Integer.parseInt(res.toString());
            if (null != sickedNum && sickedNum > 0){
                return sickedNum;
            }else {
                // todo 查询数据库
            }
        }
        return null;
    }
    // 设备 病床绑定
    @Override
    public boolean deviceBindSicked(Integer sickedNum, String deviceId) {
        if (null != sickedNum && sickedNum > 0 && null != deviceId && !"".equals(deviceId)){
            return redisUtil.hset(monitorDeviceIdPrefix,deviceId,sickedNum);
        }
        return false;
    }

    // 根据设备ID 获取病人信息
    @Override
    public PatientMonitorData getPatientMonitorDataByDeviceId(String deviceId) {
        if (null != deviceId && !"".equals(deviceId)){
            Integer sickedNum = getSickedNumByDeviceId(deviceId);
            Integer patientId = getPatientIdBySickedNum(sickedNum);
            return getPatientMonitorInfo(patientId);
        }
        return null;
    }
    // 根据病床ID查询病人信息
    @Override
    public PatientMonitorData getPatientMonitorDataBySickedNum(Integer sickedNum) {
        // 获取病人ID
        if (null != sickedNum && !"".equals(sickedNum)){
            Integer patientId = getPatientIdBySickedNum(sickedNum);
            return getPatientMonitorInfo(patientId);
        }
        return null;
    }
    // 修改输液总量
    @Override
    public boolean updatePatientInfusion(Map<String, Object> params) {
        Integer patientId = params.get("patientId") == null?null:Integer.parseInt(params.get("patientId").toString());
        Integer transfusionSum = params.get("transfusionSum") == null?null:Integer.parseInt(params.get("transfusionSum").toString());
        if (null != patientId && patientId > 0 && null != transfusionSum && transfusionSum > 0){
            // 1 修改数据库
            int count = patientMonitorMapper.updatePatientInfusion(params);
            // 修改redis
            if (count > 0){
                PatientMonitorData patientMonitorData = new PatientMonitorData();
                patientMonitorData.setPatientId(patientId);
                patientMonitorData.setTransfusionSum(transfusionSum);
                patientMonitorData.setTransfusionSumFlag(1);
                return updatePatientMonitorInfo(patientMonitorData);
            }
        }
        return false;
    }
    // 标志位置零
    @Override
    public boolean resetPatientFlag(Integer patientId) {
        if (null != patientId && patientId > 0){
            PatientMonitorData redisInfo = getPatientMonitorInfo(patientId);
            redisInfo.setFlag2(0);
            redisInfo.setFlag1(0);
            redisInfo.setBladderFlag(0);
            redisInfo.setTransfusionSwitchFlag(0);
            redisInfo.setTransfusionSumFlag(0);
            redisInfo.setUrineSwitchFlag(0);
            return redisUtil.hmset(monitorBaseInfoPrefix+patientId,redisInfo.toMap());
        }
        return false;
    }
}
