package com.hxlk.centre.controller;

import com.hxlk.centre.entity.SickbedInfo;
import com.hxlk.centre.entity.vo.PatientMonitorData;
import com.hxlk.centre.mapper.PatientBloodSugerDefMapper;
import com.hxlk.centre.mapper.PatientInfoDefMapper;
import com.hxlk.centre.service.PatientMonitorService;
import com.hxlk.centre.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.ClassGeneratingEntityInstantiator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("patient-monitor")
public class PatientMonitorNewController {

    @Autowired
    PatientMonitorService patientMonitorService;
    @Autowired
    PatientInfoDefMapper patientInfoMapper;
    @Autowired
    PatientBloodSugerDefMapper patientBloodSugerDefMapper;

    // 根据病人ID查询二级报表数据
    @RequestMapping(value = "selectMonitorDataCharList", method = RequestMethod.POST)
    public List<Map<String, Object>> selectMonitorDataCharList(@RequestParam Map<String, Object> params) {
        return patientMonitorService.selectMonitorDataCharList(params);
    }

    // 根据病人ID查询二级血糖报表
    @RequestMapping("/selectPatientBloodSugerDef")
    public List<Map<String, Object>> selectPatientBloodSugerDef(@RequestParam Map<String, Object> params) {
        System.out.println(params);
        List<Map<String, Object>> maps = patientBloodSugerDefMapper.selectPatientBloodSuger(params);
        System.out.println(maps);
        return maps;
    }

    //导出时查询的多天血糖数据
    @RequestMapping("/selectPatientBloodSugerByDate")
    public List<Map<String, Object>> selectPatientBloodSugerByDate(@RequestParam Map<String, Object> params) {
        System.out.println(params);
        List<Map<String, Object>> maps = patientBloodSugerDefMapper.selectPatientBloodSugerByDate(params);
        return maps;
    }

    //导出时查询的多天(体温,血氧,尿量,脉搏)数据
//    @RequestMapping(value = "/selectMonitorByDate", method = RequestMethod.POST)
//    public List<Map<String, Object>> selectMonitorByDate(@RequestParam Map<String, Object> params) {
//        //存放传给前端的数据
//        List<Map<String, Object>> maps = new ArrayList<>();
//        System.out.println(params.get("startTime").toString());
//        System.out.println(params.get("endTime").toString());
//        //使用工具类获取到两个日期之间的所有日期
//        List<String> betweenDates = DateUtil.getBetweenDates(params.get("startTime").toString(), params.get("endTime").toString());
//        //获取到病人编号
//        String patientId = params.get("patientId").toString();
//        //清空map
//        params.clear();
//        //循环查询存放到集合中
//        for (int i = 0; i < betweenDates.size(); i++) {
//            //添加数据到map
//            params.put("time", betweenDates.get(i));
//            params.put("patientId", patientId);
//            List<Map<String, Object>> OneDayData = patientBloodSugerDefMapper.selectMonitorByDate(params);
//            for (Map<String, Object> one : OneDayData) {
//                maps.add(one);
//            }
//            params.clear();
//        }
//        return maps;
//    }

    @RequestMapping(value = "/selectMonitorByDate", method = RequestMethod.POST)
    public List<Map<String, Object>> selectMonitorByDate(@RequestParam Map<String, Object> params) {
//        System.out.println(params);
        params.clear();
        params.put("time","2019-9-23");
        params.put("patientId",1);
        return patientBloodSugerDefMapper.selectMonitorByDate(params);
    }


    // 开关尿袋开关 / 输液开关
    @RequestMapping(value = "operatorSwitch", method = RequestMethod.POST)
    public String operatorSwitch(@RequestParam Map<String, Object> params) {
        return patientMonitorService.operatorSwitch(params);
    }

    // 修改膀胱训练
    @RequestMapping(value = "updatePatientBladder", method = RequestMethod.POST)
    public String updatePatientBladder(@RequestParam Map<String, Object> params) {

        return patientMonitorService.updatePatientBladder(params);
    }

    // 根据病床ID查询 病人信息
    @RequestMapping(value = "getPatientMonitorDataBySickedNum", method = RequestMethod.POST)
    public PatientMonitorData getPatientMonitorDataBySickedNum(@RequestParam Map<String, Object> params) {
        Integer sickbedNum = params.get("sickbedNum") == null ? null : Integer.parseInt(params.get("sickbedNum").toString());

        return patientMonitorService.getPatientMonitorDataBySickedNum(sickbedNum);
    }

    // 修改病人输液总量
    @RequestMapping(value = "updatePatientInfusion", method = RequestMethod.POST)
    public boolean updatePatientInfusion(@RequestParam Map<String, Object> params) {
        return patientMonitorService.updatePatientInfusion(params);
    }

    // 根据病床查询病人信息
    @RequestMapping(value = "selectPatientInfoByBedNum", method = RequestMethod.POST)
    public Map<String, Object> selectPatientInfoByBedNum(@RequestParam Map<String, Object> params) {
        Integer sickbedNum = params.get("sickbedNum") == null ? null : Integer.parseInt(params.get("sickbedNum").toString());
        if (null != sickbedNum && sickbedNum > 0) {
            PatientMonitorData redisInfo = patientMonitorService.getPatientMonitorDataBySickedNum(sickbedNum);
            if (null != redisInfo && redisInfo.getPatientId() > 0) {
                Map<String, Object> redisMap = redisInfo.toMap();
                redisMap.put("inHosTime", redisInfo.getInHosDate());
                redisMap.put("urineTimer", redisInfo.getBladderTimer());
                return redisMap;
            } else {
                return patientInfoMapper.findPatientInfoById(new SickbedInfo(0, String.valueOf(sickbedNum), "", 0));
            }
        }

        return null;
    }

}
