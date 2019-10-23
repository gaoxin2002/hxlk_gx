package com.hxlk.centre.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hxlk.centre.entity.DeviceInfo;
import com.hxlk.centre.entity.PatientMonitorInfo;
import com.hxlk.centre.mapper.HxlkHospMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class DeviceInfoController {

    @Autowired
    HxlkHospMapper hxlkHospMapper;

    @RequestMapping(value = "/deviceregister", method = {RequestMethod.POST})
    public String deviceRegister(@ModelAttribute DeviceInfo deviceInfo) {
        System.out.println("Device_Id: " + deviceInfo.getDevice_id());

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        String currentTime = sdf.format(d);
        deviceInfo.setSetup_date(currentTime);

        hxlkHospMapper.deviceRegister(deviceInfo);

        PatientMonitorInfo patientMonitorInfo = new PatientMonitorInfo(
                0, "", deviceInfo.getDevice_id(),0, 0, 0, 0,
                0, 0, 0, 0,0,0,0,null);

        hxlkHospMapper.insertDeviceIntoCurrentPatientMointor(patientMonitorInfo);
        return "deviceregister Okkk!";
    }

    @RequestMapping("/updatedeviceinfo")
    public String updateDeviceInfo(@ModelAttribute DeviceInfo deviceInfo) {
        System.out.println("Device_Id: " + deviceInfo.getDevice_id());
        hxlkHospMapper.updateDeviceInfo(deviceInfo);
        return "updateDeviceInfo Okkk!";
    }

    @RequestMapping("/getdevicelist")
    public String getDeviceList() throws JsonProcessingException {
        List<DeviceInfo> deviceInfoList = hxlkHospMapper.getDeviceList();
        ObjectMapper objectMapper = new ObjectMapper();
        String sjson = objectMapper.writeValueAsString(deviceInfoList);
        return sjson;
    }

    @RequestMapping("/devicedelete")
    public String patientDelete(@ModelAttribute DeviceInfo deviceInfo) {
        hxlkHospMapper.deviceDelete(deviceInfo);
        return "patientDelete Okkk!";
    }
}
