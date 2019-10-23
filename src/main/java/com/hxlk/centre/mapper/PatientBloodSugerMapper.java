package com.hxlk.centre.mapper;

import com.hxlk.centre.entity.PatientBloodSuger;
import com.hxlk.centre.entity.PatientBloodSugerExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface PatientBloodSugerMapper {
    int countByExample(PatientBloodSugerExample example);

    int deleteByExample(PatientBloodSugerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PatientBloodSuger record);

    int insertSelective(PatientBloodSuger record);

    List<PatientBloodSuger> selectByExample(PatientBloodSugerExample example);

    PatientBloodSuger selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PatientBloodSuger record, @Param("example") PatientBloodSugerExample example);

    int updateByExample(@Param("record") PatientBloodSuger record, @Param("example") PatientBloodSugerExample example);

    int updateByPrimaryKeySelective(PatientBloodSuger record);

    int updateByPrimaryKey(PatientBloodSuger record);
}