package com.hxlk.centre.service.impl;

import com.hxlk.centre.entity.SickbedInfo;
import com.hxlk.centre.mapper.SickbedInfoDefMapper;
import com.hxlk.centre.service.SickbedInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class SickbedInfoServiceImpl implements SickbedInfoService{

    @Autowired
    private SickbedInfoDefMapper sickbedInfoMapper;


    @Transactional
    @Override
    public void changeBed(String oldSickbedNum, String newSickbedNum, String patientId)  throws  Exception{
        sickbedInfoMapper.updateSickbedInfo(new SickbedInfo(0,oldSickbedNum.toString(),"",0));
        sickbedInfoMapper.updateOperation(new SickbedInfo(0,newSickbedNum,"",Integer.parseInt(patientId)));
    }
}
