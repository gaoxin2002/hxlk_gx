package com.hxlk.centre.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hxlk.centre.entity.DeviceInfo;
import com.hxlk.centre.entity.MedicalWorkerInfo;
import com.hxlk.centre.mapper.HxlkHospMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class MedicalWorkerController {

    @Autowired
    HxlkHospMapper hxlkHospMapper;

    @RequestMapping("/getmedicalworkerlist")
    public String getMedicalWorkerList() throws JsonProcessingException {
        List<MedicalWorkerInfo> medicalWorkList = hxlkHospMapper.getMedicalWorkerList();
        ObjectMapper objectMapper = new ObjectMapper();
        String sjson = objectMapper.writeValueAsString(medicalWorkList);
        return sjson;
    }

    @RequestMapping("/medicalworkerregister")
    public String medicalWorkerRegister(@ModelAttribute MedicalWorkerInfo medicalWorkerInfo) {
        hxlkHospMapper.medicalWorkerRegister(medicalWorkerInfo);
        return "medicalworker register Okkk!";
    }

    @RequestMapping("/updatemedicalworker")
    public String updateMedicalWorker(@ModelAttribute MedicalWorkerInfo medicalWorkerInfo) {
        hxlkHospMapper.updateMedicalWorker(medicalWorkerInfo);
        return "updateDeviceInfo Okkk!";
    }

    @RequestMapping("/medicalworkerdelete")
    public String medicalWorkerDelete(@ModelAttribute MedicalWorkerInfo medicalWorkerInfo) {
        hxlkHospMapper.medicalWorkerDelete(medicalWorkerInfo);
        return "patientDelete Okkk!";
    }
}
