package com.hxlk.centre.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hxlk.centre.entity.PatientInOutRecord;
import com.hxlk.centre.entity.PatientInfo;
import com.hxlk.centre.entity.SickbedInfo;
import com.hxlk.centre.mapper.HxlkHospMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class PatientInfoController {

    @Autowired
    HxlkHospMapper hxlkHospMapper;

    @RequestMapping("/patientregister")
    public String patientRegister(@ModelAttribute PatientInfo patientInfo) {
        System.out.println("Name: " + patientInfo.getName() + ", Sex: " + patientInfo.getSex() + ", birthdate: " + patientInfo.getBirthdate() + ", Telephone: " + patientInfo.getTelephone());
        hxlkHospMapper.patientRegister(patientInfo);
        return "inPatient Okkk!";
    }

    @RequestMapping("/getpatientlist")
    public String getPatientList() throws JsonProcessingException {
        List<PatientInfo> patientInfoList = hxlkHospMapper.getPatientList();
        ObjectMapper objectMapper = new ObjectMapper();
        String sjson = objectMapper.writeValueAsString(patientInfoList);
        return sjson;
    }

    @RequestMapping("/inpatient")
    public String inPatient(@ModelAttribute PatientInOutRecord patientInOutRecord) {
        System.out.println(patientInOutRecord.getDoctor());

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        String currentTime = sdf.format(d);
        patientInOutRecord.setOperate_time(currentTime);

        hxlkHospMapper.inOutPatient(patientInOutRecord);
        //System.out.println("InoutPatient insert_id: " + patientInOutRecord.getId());

        hxlkHospMapper.editPatientStatus(new PatientInfo(patientInOutRecord.getPatient_id(), "", "", "", "",
                0, patientInOutRecord.getId(),0,0,"","",new Date(),0));

        hxlkHospMapper.updateSickbedInfoPatientId(new SickbedInfo(0, patientInOutRecord.getSickbed_id(), "", patientInOutRecord.getPatient_id()));
        return "inoutpatient Okkk!";
    }

    @RequestMapping("/patientdelete")
    public String patientDelete(@ModelAttribute PatientInfo patientInfo) {
        System.out.println("patientDelete : " + patientInfo.getId());
        hxlkHospMapper.patientDelete(patientInfo);
        return "patientDelete Okkk!";
    }

    @RequestMapping("/patientedit")
    public String patientEdit(@ModelAttribute PatientInfo patientInfo) {
        System.out.println("patientEdit : " + patientInfo.getId());
        hxlkHospMapper.patientEdit(patientInfo);
        return "patientEdit Okkk!";
    }
}
