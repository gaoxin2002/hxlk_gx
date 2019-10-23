package com.hxlk.centre.mapper;

import com.hxlk.centre.entity.PatientInOutRecord;
import org.springframework.stereotype.Repository;

//出入院信息接口
@Repository
public interface PatientInoutRecordMapper {

    //添加出入院信息
    void insertPatientInoutRecordInfo(PatientInOutRecord patientInOutRecord);

    //修改出入院信息

    void updatePatientInoutRecordInfo(PatientInOutRecord patientInOutRecord);

}
