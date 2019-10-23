package com.hxlk.centre.controller;

import com.alibaba.fastjson.JSON;
import com.hxlk.centre.service.PatientMonitorService;
import com.hxlk.centre.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class TestController {
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    PatientMonitorService patientMonitorService;

    @RequestMapping(value = "/test", method = {RequestMethod.POST})
    public String test() {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("name","csq");
        map.put("age","123");
        map.put("date",new Date());
        redisUtil.hmset("user",map);
        map.put("name","csq2");
        redisUtil.hmset("user",map);
//        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
//        list.add(map);
//        redisUtil.lSet("list",list);
//        List<Object> resList = redisUtil.lGet("list",0,-1);
//        JSONArray array = JSONArray.parseArray(JSON.toJSONString(resList));
//        System.out.println(array.getJSONObject(5));
//        Map<String,Object> map = new HashMap<String, Object>();
//        map.put("patientId",123);
//        map.put("sickbedNum",123);
//        map.put("patientName","csq");
//        map.put("deviceId","2453256");
//        map.put("sex","男");
//        map.put("age",45);
//        map.put("inHosDate",new Date());
//        map.put("doctorName","刘玄德");
//        map.put("nurseName","孙尚香");
//        map.put("transfusionSum",600);
//
//        PatientMonitorData patientMonitorData = JSONObject.parseObject(JSON.toJSONString(map),PatientMonitorData.class);


        return JSON.toJSONString(map);
    }

    @RequestMapping(value = "/test2", method = {RequestMethod.POST})
    public List<Map<String, Object>> test2(@RequestParam Map<String,Object> params) {

        return patientMonitorService.selectMonitorDataCharList(params);
    }
}
