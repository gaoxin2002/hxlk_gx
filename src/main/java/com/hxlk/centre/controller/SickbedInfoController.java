package com.hxlk.centre.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hxlk.centre.entity.PatientInOutRecord;
import com.hxlk.centre.entity.PatientInfo;
import com.hxlk.centre.entity.SickbedInfo;
import com.hxlk.centre.entity.SickbedPatientView;
import com.hxlk.centre.mapper.HxlkHospMapper;
import com.hxlk.centre.mapper.SickbedInfoDefMapper;
import com.hxlk.centre.service.SickbedInfoService;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class SickbedInfoController {

    @Autowired
    HxlkHospMapper hxlkHospMapper;

    @Autowired
    SickbedInfoDefMapper sickbedInfoMapper;

    @Autowired
    SickbedInfoService sickbedInfoService;

    @RequestMapping("/sickbedregister")
    public String sickbedRegister(@ModelAttribute SickbedInfo sickbedInfo) {

        hxlkHospMapper.sickbedRegister(sickbedInfo);
        return "sickbedRegister Okkk!";
    }

    @RequestMapping("/getsickbedlist")
    public String getSickbedList() throws JsonProcessingException {
        List<SickbedInfo> sickbedInfoList = hxlkHospMapper.getSickbedList();
        ObjectMapper objectMapper = new ObjectMapper();
        String sjson = objectMapper.writeValueAsString(sickbedInfoList);
        return sjson;
    }

    @RequestMapping("/getsickbedpatientview")
    public String getSickbedPatientView() throws JsonProcessingException {
        List<SickbedPatientView> sickbedPatientViewList = hxlkHospMapper.getSickbedPatientViewList();
        ObjectMapper objectMapper = new ObjectMapper();
        String sjson = objectMapper.writeValueAsString(sickbedPatientViewList);
        return sjson;
    }

    @RequestMapping("/updatesickbedinfodeviceid")
    public String updateSickbedInfo(@ModelAttribute SickbedInfo sickbedInfo) {
        hxlkHospMapper.updateSickbedInfoDeviceId(sickbedInfo);
        return "updateSickbedInfo Okkk!";
    }

    @RequestMapping("/outpatient")
    public String outPatient(@ModelAttribute PatientInOutRecord patientInOutRecord) {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        String currentTime = sdf.format(d);
        patientInOutRecord.setOperate_time(currentTime);

        hxlkHospMapper.inOutPatient(patientInOutRecord);

        hxlkHospMapper.editPatientStatus(new PatientInfo(patientInOutRecord.getPatient_id(), "", "", "", "",
                0, patientInOutRecord.getId(), 0, 0, "", "", new Date(),0));

        hxlkHospMapper.updateSickbedInfoPatientId(new SickbedInfo(0, patientInOutRecord.getSickbed_id(), "", 0));
        return "inoutpatient Okkk!";
    }

    @RequestMapping("/patientexchangesickbed")
    public String patientExchangeSickbed(@ModelAttribute PatientInOutRecord patientInOutRecord) {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        String currentTime = sdf.format(d);
        patientInOutRecord.setOperate_time(currentTime);

        hxlkHospMapper.inOutPatient(patientInOutRecord);

        hxlkHospMapper.editPatientStatus(new PatientInfo(patientInOutRecord.getPatient_id(), "", "", "", "",
                0, patientInOutRecord.getId(), 0, 0, "", "", new Date(),0));

        hxlkHospMapper.updateSickbedInfoPatientId(new SickbedInfo(0, patientInOutRecord.getDepartment(), "", 0));
        hxlkHospMapper.updateSickbedInfoPatientId(new SickbedInfo(0, patientInOutRecord.getSickbed_id(), "", patientInOutRecord.getPatient_id()));
        return "inoutpatient Okkk!";
    }

    //查询可用病床
    @RequestMapping("/findUsableSickbed")
    public List<Map<String, Object>> findUsableSickbed() {

        List<Map<String, Object>> usableSickbed = sickbedInfoMapper.findUsableSickbed();

        return usableSickbed;
    }

    //换床
    @RequestMapping("/changeBed")
    public String Operation(@RequestParam Map<String, Object> params) {

//        //编号
//        Integer id = params.get("id") == null ? null : Integer.parseInt(params.get("id").toString());
        //旧病床编号
        Integer  oldSickbedNum= params.get("oldSickbedNum") == null ? null : Integer.parseInt(params.get("oldSickbedNum").toString());
        //新床编号
        Integer  newSickbedNum= params.get("newSickbedNum") == null ? null : Integer.parseInt(params.get("newSickbedNum").toString());
        //病人编号
        Integer patientId=params.get("patientId") ==null?null :Integer.parseInt(params.get("patientId").toString());

        try {
            sickbedInfoService.changeBed(oldSickbedNum.toString(),newSickbedNum.toString(),patientId.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }


//        //通过旧床号和id把旧床的病人编号改为0
//        int rows1=sickbedInfoMapper.updateSickbedInfo(new SickbedInfo(0,oldSickbedNum.toString(),"",0));
//
//        //通过新床编号修改成要换床的病人编号
//        int rows2=sickbedInfoMapper.updateOperation(new SickbedInfo(0,newSickbedNum.toString(),"",patientId));

//        if(rows1!=1){
//            return "error";
//        }
//        if(rows2!=1){
//            return "error";
//        }
        return "success";
    }

}
