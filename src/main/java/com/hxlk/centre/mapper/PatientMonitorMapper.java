package com.hxlk.centre.mapper;

import com.hxlk.centre.entity.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PatientMonitorMapper {
    // 根据 病床号和日期查询二级页面报表数据
    List<Map<String,Object>> selectMonitorDataCharList(Map<String,Object> params);
    // 根据设备ID 查询病人ID
    Integer selectPatientIdByDeviceId(String deviceId);
    // 根据病人ID 修改 输液总量
    int updatePatientInfusion(Map<String,Object> params);
}
