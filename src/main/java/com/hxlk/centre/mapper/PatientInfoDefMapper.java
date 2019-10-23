package com.hxlk.centre.mapper;

import com.hxlk.centre.entity.PatientInfo;
import com.hxlk.centre.entity.SickbedInfo;
import org.springframework.stereotype.Repository;

import java.util.Map;

//病人信息接口
@Repository
public interface PatientInfoDefMapper {

    //出入院时修改病人出入院状态字段
    void updatePatientState(PatientInfo patientInfo);

    //添加病人入院
    void addPatientInfo(PatientInfo patientInfo);

    //修改病人信息
    void updatePatientInfo(PatientInfo patientInfo);

    //通过角色编号获取对象  (出入院记录编号)
    PatientInfo getPatientInfoById(PatientInfo patientInfo);

    //修改病人的入院编号
    void updatePatientRecordId(PatientInfo patientInfo);

    //二级界面通过病人编号查询病人相关信息
    Map<String,Object> findPatientInfoById(SickbedInfo sickbedInfo);

}
