package com.hxlk.centre.mapper;

import com.hxlk.centre.entity.SickbedInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SickbedInfoDefMapper {
    void updateSickbedInfoPatientId(SickbedInfo sickbedInfo);

    //查用可用病床
    List<Map<String,Object>> findUsableSickbed();

    void update(SickbedInfo sickbedInfo);

    //换床时将病人当前病床为0(代表没人状态)
    int updateSickbedInfo(SickbedInfo sickbedInfo);

    //换床
    int updateOperation(SickbedInfo sickbedInfo);

}
