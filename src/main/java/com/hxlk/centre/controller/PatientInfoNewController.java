package com.hxlk.centre.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hxlk.centre.entity.PatientInOutRecord;
import com.hxlk.centre.entity.PatientInfo;
import com.hxlk.centre.entity.SickbedInfo;
import com.hxlk.centre.entity.vo.PatientMonitorData;
import com.hxlk.centre.mapper.PatientInfoDefMapper;
import com.hxlk.centre.mapper.PatientInoutRecordMapper;
//import com.hxlk.centre.service.PatientInfoService;
//import com.hxlk.centre.service.PatientInoutRecordService;
//import com.hxlk.centre.service.SickbedInfoService;
import com.hxlk.centre.mapper.SickbedInfoDefMapper;
import com.hxlk.centre.service.impl.PatientMonitorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class PatientInfoNewController {

    @Autowired
    PatientInfoDefMapper patientInfoMapper;

    @Autowired
    PatientInoutRecordMapper patientInoutRecordMapper;

    @Autowired
    SickbedInfoDefMapper sickbedInfoMapper;
    //redis接口
    @Autowired
    PatientMonitorServiceImpl patientMonitorService;

    //入院
    @RequestMapping("/patientIn")
    public String PatientIn(@RequestParam Map<String, Object> params) {
//
        //病人所需参数
        //病人姓名
        String patientInfoName = params.get("patientName") == null ? null : params.get("patientName").toString();
        //姓别
        String patientInfoSex = params.get("sex") == null ? null : params.get("sex").toString();
        //年龄
        Integer patientInfoAge = params.get("age") == null ? null : Integer.parseInt(params.get("age").toString());
        //手机号
        String patientInfoTelephone = params.get("patientPhone") == null ? null : params.get("patientPhone").toString();
        //输液量
        Integer patientInfoInfusionSum = params.get("transfusionSum") == null ? null : Integer.parseInt(params.get("transfusionSum").toString());
        //医生姓名
        String patientInfoDoctorName = params.get("doctorName") == null ? null : params.get("doctorName").toString();
        //护士姓名
        String patientInfoNurseName = params.get("nurseName") == null ? null : params.get("nurseName").toString();
        //入院时间
        String patientInfoInHosTime = params.get("inHosTime") == null ? null : params.get("inHosTime").toString();
        String InOut = "入院";
        //科室名称
        String departmentName = params.get("departmentName") == null ? null : params.get("departmentName").toString();
        //操作人
        String operator = params.get("operaterName") == null ? null : params.get("operaterName").toString();
        //病床编号
        String sickbedNum = params.get("sickbedNum") == null ? null : params.get("sickbedNum").toString();
        // 体重
        Double weight = params.get("weight") == null?null:Double.parseDouble(params.get("weight").toString());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date inHosTimeDate = null;
        try {
            inHosTimeDate = sdf.parse(patientInfoInHosTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        PatientInfo patientInfo = new PatientInfo(0, patientInfoName, patientInfoSex, null,
                patientInfoTelephone,
                1, 0,
                patientInfoInfusionSum,
                patientInfoAge,
                patientInfoDoctorName,
                patientInfoNurseName,
                inHosTimeDate,
                weight);

        //向数据库中添加一条病人数据
        patientInfoMapper.addPatientInfo(patientInfo);


        //要存放redis的对象
        PatientMonitorData patientMonitorData = new PatientMonitorData();

        patientMonitorData.setPatientId(patientInfo.getId());
        patientMonitorData.setSickbedNum(Integer.parseInt(sickbedNum));
        patientMonitorData.setPatientName(patientInfoName);
        patientMonitorData.setPatientPhone(patientInfoTelephone);
        patientMonitorData.setSex(patientInfoSex);
        patientMonitorData.setAge(patientInfoAge);
        patientMonitorData.setInHosDate(inHosTimeDate);
        patientMonitorData.setDoctorName(patientInfoDoctorName);
        patientMonitorData.setNurseName(patientInfoNurseName);
        patientMonitorData.setTransfusionSum(patientInfoInfusionSum);
        patientMonitorData.setWeight(weight);

        //向redis中更新一条数据
        patientMonitorService.insertPatientMonitorInfo(patientMonitorData);


//        //添加入院信息所需参数
        Integer PatientId = patientInfo.getId();//从上边添加的病人中直接获取编号

        //入院
        PatientInOutRecord patientInOutRecord = new PatientInOutRecord();
        patientInOutRecord.setPatient_id(PatientId);
        patientInOutRecord.setIn_out(InOut);
        patientInOutRecord.setOperate_time(patientInfoInHosTime.toString());
        patientInOutRecord.setDepartment(departmentName);
        patientInOutRecord.setOperator(operator);
        patientInOutRecord.setSickbed_id(sickbedNum);
        System.out.println(patientInOutRecord);

        //添加入院记录
        patientInoutRecordMapper.insertPatientInoutRecordInfo(patientInOutRecord);

        //修改病人的入院记录
//        PatientInfo updateRecordInfoObj = new PatientInfo();
//        updateRecordInfoObj.setId(patientInfo.getId());
//        updateRecordInfoObj.setRecord_id(patientInOutRecord.getId());
//
//        patientInfoMapper.updatePatientRecordId(updateRecordInfoObj);

        //修改病床信息
        sickbedInfoMapper.updateSickbedInfoPatientId(new SickbedInfo(0, patientInOutRecord
                .getSickbed_id(), "", patientInOutRecord.getPatient_id()));

        return "patientIn okk!!";
    }

    //出院
    @RequestMapping("/patientOut")
    public String PatientOut(@RequestParam Map<String, Object> params) {

//
        // 操作人
        String operator = params.get("operaterName") == null ? null : params.get("operaterName").toString();
        //病床编号
        String sickbedNum = params.get("sickbedNum") == null ? null : params.get("sickbedNum").toString();
        //病人编号
        Integer patientId = params.get("patientId") == null ? null : Integer.parseInt(params.get("patientId").toString());
        //出院时间
        String patientInfoInHosTime = params.get("inHosTime") == null ? null : params.get("inHosTime").toString();

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date inHosTimeDate = null;
//        try {
//            inHosTimeDate = sdf.parse(patientInfoInHosTime);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        //      ---------------------添加出院记录------------------------

        PatientInOutRecord patientInOutRecord = new PatientInOutRecord();
        patientInOutRecord.setOperator(operator);
        patientInOutRecord.setSickbed_id(sickbedNum);
        patientInOutRecord.setPatient_id(patientId);
        patientInOutRecord.setIn_out("出院");
        patientInOutRecord.setOperate_time(patientInfoInHosTime);
        System.out.println(patientInOutRecord);
        //添加出院记录
        patientInoutRecordMapper.insertPatientInoutRecordInfo(patientInOutRecord);
        //修改病人信息的出院记录编号
//        PatientInfo updateInfoObj = new PatientInfo();
//        updateInfoObj.setId(patientId);
//        updateInfoObj.setRecord_id(patientInOutRecord.getId());

//       -----------------------------------------------------------

        patientInfoMapper.updatePatientState(new PatientInfo(patientInOutRecord.getPatient_id(), "", "", "", "", 0,
                patientInOutRecord.getId(), 0, 0, "", "", null,0));

        sickbedInfoMapper.updateSickbedInfoPatientId(new SickbedInfo(0, patientInOutRecord.getSickbed_id(), "", 0));

        //出院时redis中删除该病人记录
        patientMonitorService.deletePatientInfoById(patientId);

        return "patientOut okk!!";
    }

    //二级界面修改病人信息
    @RequestMapping("/updatePatienInfoAndRecordInfo")
    public String updatePatienInfoAndRecordInfo(@RequestParam Map<String, Object> params) {

        //病人編号
        Integer patientId = params.get("patientId") == null ? null : Integer.parseInt(params.get("patientId").toString());
//        //病人姓名
        String patientName = params.get("patientName") == null ? null : params.get("patientName").toString();
//        //性别
        String patientSex = params.get("sex") == null ? null : params.get("sex").toString();
//        //手机号
        String patientTelephone = params.get("patientPhone") == null ? null : params.get("patientPhone").toString();
//        //年龄
        Integer patientAge = params.get("age") == null ? null : Integer.parseInt(params.get("age").toString());
        //医生姓名
        String doctorName = params.get("doctorName") == null ? null : params.get("doctorName").toString();
        //护士姓名
        String nurseName = params.get("nurseName") == null ? null : params.get("nurseName").toString();
        //入院时间
        String inHosTime = params.get("inHosTime") == null ? null : params.get("inHosTime").toString();
        // 体重修改
        Double weight = params.get("weight") == null?null:Double.parseDouble(params.get("weight").toString());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date inHosTimeDate = null;

        try {
            if (null != inHosTime){
                inHosTimeDate = sdf.parse(inHosTime);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
//
        PatientInfo patientInfo = new PatientInfo(patientId, patientName, patientSex, null,
                patientTelephone, 0, 0, 0, patientAge, doctorName, nurseName, inHosTimeDate,weight);

//        //数据库中修改病人信息
        patientInfoMapper.updatePatientInfo(patientInfo);

        //redis中修改病人的数据的对象
        PatientMonitorData patientMonitorData = new PatientMonitorData();
        patientMonitorData.setPatientId(patientId);
        patientMonitorData.setPatientName(patientName);
        patientMonitorData.setSex(patientSex);
        patientMonitorData.setPatientPhone(patientTelephone);
        patientMonitorData.setAge(patientAge);
        patientMonitorData.setDoctorName(doctorName);
        patientMonitorData.setNurseName(nurseName);
        patientMonitorData.setInHosDate(inHosTimeDate);
        patientMonitorData.setWeight(weight);
        //从redis中修改病人的数据
        patientMonitorService.updatePatientMonitorInfo(patientMonitorData);

        return "OK!!!!!";
    }


    //二级界面显示病人信息(根据病床编号查询病人信息)

    @RequestMapping("/findPatientInfoById")
    public Map<String, Object> qwe(@RequestParam Map<String, Object> params) {
        //病床编号
        String sickbedNum = params.get("sickbedNum") == null ? null : params.get("sickbedNum").toString();

        Map<String, Object> patientInfoById = patientInfoMapper.findPatientInfoById(new SickbedInfo(0, sickbedNum, "", 0));

        return patientInfoById;
    }


}
