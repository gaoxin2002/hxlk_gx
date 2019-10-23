package com.hxlk.centre.service;

import com.hxlk.centre.entity.*;
import com.hxlk.centre.mapper.HxlkHospMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HxlkHospService {

    @Autowired
    private HxlkHospMapper hxlkHospMapper;

    void patientRegister(PatientInfo patientInfo) {
        hxlkHospMapper.patientRegister(patientInfo);
    }

    void patientDelete(PatientInfo patientInfo) { hxlkHospMapper.patientDelete(patientInfo); }

    void patientEdit(PatientInfo patientInfo) { hxlkHospMapper.patientEdit(patientInfo); }

    void editPatientStatus(PatientInfo patientInfo) { hxlkHospMapper.editPatientStatus(patientInfo);}

    void inOutPatient(PatientInOutRecord patientInOutRecord) { hxlkHospMapper.inOutPatient(patientInOutRecord); }

    List<PatientInfo> getPatientInfoAll() {
        return hxlkHospMapper.getPatientList();
    }

    List<MedicalWorkerInfo> getMedicalWorkList() { return hxlkHospMapper.getMedicalWorkerList(); }

    void medicalWorkerRegister(MedicalWorkerInfo medicalWorkerInfo) { hxlkHospMapper.medicalWorkerRegister(medicalWorkerInfo); }

    void updateMedicalWorker(MedicalWorkerInfo medicalWorkerInfo) { hxlkHospMapper.updateMedicalWorker(medicalWorkerInfo); }

    void medicalWorkerDelete(MedicalWorkerInfo medicalWorkerInfo) { hxlkHospMapper.medicalWorkerDelete(medicalWorkerInfo); }

    List<PatientMonitorInfo> getCurrentPatientMonitor() {
        return hxlkHospMapper.getCurrentPatientMonitor();
    }

    void insertPatientMonitorData(PatientMonitorInfo patientMonitorInfo) { hxlkHospMapper.insertPatientMonitorData(patientMonitorInfo); }

    void deviceRegister(DeviceInfo deviceInfo) { hxlkHospMapper.deviceRegister(deviceInfo); }

    void updateDeviceInfo(DeviceInfo deviceInfo) { hxlkHospMapper.updateDeviceInfo(deviceInfo); }

    List<DeviceInfo> getDeviceList() { return hxlkHospMapper.getDeviceList(); }

    void deviceDelete(DeviceInfo deviceInfo) { hxlkHospMapper.deviceDelete(deviceInfo); }

    void sickbedRegister(SickbedInfo sickbedInfo) { hxlkHospMapper.sickbedRegister(sickbedInfo); }

    List<SickbedInfo> getSickbedList() { return hxlkHospMapper.getSickbedList(); }

    List<SickbedPatientView> getSickbedPatientViewList() { return hxlkHospMapper.getSickbedPatientViewList(); }

    List<SickbedMonitorView> getSickbedMonitorViewList() { return hxlkHospMapper.getSickbedMonitorViewList(); }

    void updateSickbedInfoDeviceId(SickbedInfo sickbedInfo) { hxlkHospMapper.updateSickbedInfoDeviceId(sickbedInfo); }

    void updateSickbedInfoPatientId(SickbedInfo sickbedInfo) { hxlkHospMapper.updateSickbedInfoPatientId(sickbedInfo); }

    void insertDeviceIntoCurrentPatientMointor(PatientMonitorInfo patientMonitorInfo) { hxlkHospMapper.insertDeviceIntoCurrentPatientMointor(patientMonitorInfo); }

    void updateCurrentPatientMonitorByDevice(PatientMonitorInfo patientMonitorInfo) { hxlkHospMapper.updateCurrentPatientMonitorByDevice(patientMonitorInfo); }

    void insertPatientInOutRecord(PatientInOutRecord patientInOutRecord) { hxlkHospMapper.insertPatientInOutRecord(patientInOutRecord); }
}
