package com.hxlk.centre.controller;

import com.hxlk.centre.entity.PatientInfo;
import com.hxlk.centre.entity.PatientMonitorInfo;
import com.hxlk.centre.mapper.HxlkHospMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ThymeleafTestController {

    @Autowired
    HxlkHospMapper hxlkHospMapper;

    @RequestMapping("/tftest")
    public String getData(Model model) {

        List<PatientInfo> list_patient_info = hxlkHospMapper.getPatientList();

        //List<PatientMonitorInfo> list_patient_monitor_info = hxlkHospMapper.getPatientMonitorInfoAll();

//        List<PatientInfo> list_patient_info = new ArrayList<PatientInfo>();
//        list_patient_info.add(new PatientInfo(1, "Toney",  SexEnum.male, 28, "Ophthalmology"));
//        list_patient_info.add(new PatientInfo(2, "Tom",  SexEnum.male, 30, "Internal Medicine"));
//        list_patient_info.add(new PatientInfo(3, "John",  SexEnum.female, 15, "Stomatology"));
//        list_patient_info.add(new PatientInfo(4, "Max",  SexEnum.male, 60, "Surgery"));
//        list_patient_info.add(new PatientInfo(5, "Steve",  SexEnum.female, 100, "Neurology"));

//        model.addAttribute("list_patient_info", list_patient_info);
//        model.addAttribute("list_patient_monitor_info", list_patient_monitor_info);
//        return "/thymeleaf/index.html";
        return "/static/index.html";
    }
}
