package com.hxlk.centre.mapper;

import com.hxlk.centre.entity.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface HxlkHospMapper {
    void patientRegister(PatientInfo patientInfo);
    List<PatientInfo> getPatientList();
    void patientDelete(PatientInfo patientInfo);
    void patientEdit(PatientInfo patientInfo);
    void editPatientStatus(PatientInfo patientInfo);
    void inOutPatient(PatientInOutRecord patientInOutRecord);

    List<MedicalWorkerInfo> getMedicalWorkerList();
    void medicalWorkerRegister(MedicalWorkerInfo medicalWorkerInfo);
    void updateMedicalWorker(MedicalWorkerInfo medicalWorkerInfo);
    void medicalWorkerDelete(MedicalWorkerInfo medicalWorkerInfo);

    void deviceRegister(DeviceInfo deviceInfo);
    void updateDeviceInfo(DeviceInfo deviceInfo);
    List<DeviceInfo> getDeviceList();
    void deviceDelete(DeviceInfo deviceInfo);

    void sickbedRegister(SickbedInfo sickbedInfo);
    List<SickbedInfo> getSickbedList();
    List<SickbedPatientView> getSickbedPatientViewList();
    void updateSickbedInfoDeviceId(SickbedInfo sickbedInfo);
    void updateSickbedInfoPatientId(SickbedInfo sickbedInfo);

    void insertDeviceIntoCurrentPatientMointor(PatientMonitorInfo patientMonitorInfo);
    List<PatientMonitorInfo> getCurrentPatientMonitor();
    List<SickbedMonitorView> getSickbedMonitorViewList();
    List<Map<String,Object>> getSickbedMonitorViewList2();
    void updateCurrentPatientMonitorByDevice(PatientMonitorInfo patientMonitorInfo);

    void insertPatientMonitorData(PatientMonitorInfo patientMonitorInfo);

    void insertPatientInOutRecord(PatientInOutRecord patientInOutRecord);

    Map<String,Object> getPatientInfoByDeviceNum(String deviceNum);//根据机器编码 查询病人的出院记录和信息
}
