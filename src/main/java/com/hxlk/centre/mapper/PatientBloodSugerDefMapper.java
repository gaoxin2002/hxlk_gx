package com.hxlk.centre.mapper;

import com.hxlk.centre.entity.PatientBloodSuger;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PatientBloodSugerDefMapper {

    //根据时间和病人编号查询血糖//二级界面
    List<Map<String,Object>> selectPatientBloodSuger(Map<String,Object> params);

    //根据时间和病人编号查询
    List<Map<String,Object>> selectPatientBloodSugerByDate(Map<String,Object> params);

    //根据时间和病人编号查询除血糖外所有的数据(10分钟制)
    List<Map<String,Object>> selectMonitorByDate(Map<String,Object> params);


    public void insertDate(String date);


}

