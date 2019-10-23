package com.hxlk.centre.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hxlk.centre.entity.PatientMonitorInfo;
import com.hxlk.centre.entity.SickbedMonitorView;
import com.hxlk.centre.mapper.HxlkHospMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class PatientMonitorController {

    @Autowired
    HxlkHospMapper hxlkHospMapper;

    @RequestMapping(value = "/getcurrentpatientmonitor", method = {RequestMethod.POST})
    public String getPatientMonitor() throws JsonProcessingException {
        List<PatientMonitorInfo> patientMonitorInfoList = hxlkHospMapper.getCurrentPatientMonitor();
        ObjectMapper objectMapper = new ObjectMapper();
        String sjson = objectMapper.writeValueAsString(patientMonitorInfoList);
        return sjson;
    }
    // 原 实体类映射 当属性为null 则报错
//    @RequestMapping("/getsickbedmonitorview")
//    public String getSickbedMonitorView() throws JsonProcessingException {
//        List<SickbedMonitorView> sickbedMonitorViewList = hxlkHospMapper.getSickbedMonitorViewList();
//        ObjectMapper objectMapper = new ObjectMapper();
//        String sjson = objectMapper.writeValueAsString(sickbedMonitorViewList);
//
//        return sjson;
//    }
    // 改 -> 返回参数 map接受
    @RequestMapping("/getsickbedmonitorview")
    public PageInfo<Map<String,Object>> getSickbedMonitorView(@RequestParam Map<String, Object> params) {

        if (params.containsKey("page") && params.containsKey("pageSize")) {
            int page = Integer.valueOf(params.get("page").toString());
            int pageSize = Integer.valueOf(params.get("pageSize").toString());
            PageHelper.startPage(page, pageSize);
        }
        List<Map<String,Object>> sickbedMonitorViewList = hxlkHospMapper.getSickbedMonitorViewList2();
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(sickbedMonitorViewList);
        return pageInfo;
//        return sickbedMonitorViewList;
    }
}
