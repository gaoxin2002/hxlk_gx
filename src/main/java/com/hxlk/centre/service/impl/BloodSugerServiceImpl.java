package com.hxlk.centre.service.impl;

import com.hxlk.centre.entity.PatientBloodSuger;
import com.hxlk.centre.mapper.PatientBloodSugerMapper;
import com.hxlk.centre.service.BloodSugerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BloodSugerServiceImpl implements BloodSugerService {
    @Autowired
    PatientBloodSugerMapper bloodSugerMapper;
    @Override
    public int insertBloodSuger(PatientBloodSuger patientBloodSuger) {
        return bloodSugerMapper.insertSelective(patientBloodSuger);
    }
}
