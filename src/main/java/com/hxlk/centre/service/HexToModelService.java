package com.hxlk.centre.service;

import com.alibaba.fastjson.JSONObject;
import com.hxlk.centre.entity.PatientMonitorInfo;
import com.hxlk.centre.entity.vo.PatientMonitorData;
import com.hxlk.centre.mapper.HxlkHospMapper;
import com.hxlk.centre.mapper.PatientBloodSugerMapper;
import com.hxlk.centre.mapper.PatientMonitorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// 下位机协议数据转换
@Component
public class HexToModelService {

    @Autowired
    HxlkHospMapper hxlkHospMapper;// 编号转换
    @Autowired
    PatientMonitorMapper patientMonitorMapper;
    @Autowired
    PatientMonitorService patientMonitorService;


    // 根据 hex 返回实体类
    public PatientMonitorInfo hexToPatient(String hexStr) {
        String flagNum = hexStr.substring(0, 2); // 编号 0
        getFlagNum(flagNum);    // 编号暂时不用
        String equipmentHex = hexStr.substring(2, 10);   // 设备信息 1-4
        String deviceNum = getDeviceNum(equipmentHex); // 设备编号
        String baseInfoHex = hexStr.substring(22, 90);  // 基础信息
        JSONObject baseinfo = getBaseInfo(baseInfoHex);


        String monitorHex = hexStr.substring(90, 122);   // 病人监护数据
        JSONObject monitorInfo = getMonitorDataInfo(monitorHex);
        String errorCodeHex = hexStr.substring(122, 128);   // 错误码

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        String currentTime = sdf.format(d);
        PatientMonitorInfo patientMonitorInfo = new PatientMonitorInfo(
                0, currentTime, deviceNum, monitorInfo.getDouble("temper"), monitorInfo.getInteger("pulse"), monitorInfo.getInteger("spo2"),
                monitorInfo.getDouble("bloodSuger"), monitorInfo.getInteger("urineVol"), monitorInfo.getInteger("progress"),
                monitorInfo.getInteger("bladder"), monitorInfo.getInteger("bladdertimer"), monitorInfo.getInteger("urineSwitch"),
                monitorInfo.getInteger("infusionSwitch"), 0, baseinfo.getString("patientName"));
        patientMonitorInfo.setQmax(monitorInfo.getInteger("qmax"));
        patientMonitorInfo.setBloodSugerFlag(monitorInfo.getInteger("bloodSugerFlag"));
//        patientMonitorInfo.setFlag1(healthInfo.getInteger("flag"));
//        patientMonitorInfo.setFlag2(patientInfo.getInteger("flag"));
        return patientMonitorInfo;
    }

    // 获取编号
    public Integer getFlagNum(String hex) {
        return Integer.parseInt(hex, 16);
    }

    // 设备编号
    public String getDeviceNum(String hex) {
        String res = hex.substring(6, 8) + hex.substring(4, 6) + hex.substring(2, 4) + hex.substring(0, 2);
        return res;
    }

    // 基础信息
    public JSONObject getBaseInfo(String baseInfoHex) {
        JSONObject res = new JSONObject();
        String sickedNum = baseInfoHex.substring(1, 4); // 床号
        String doctorNameHex = baseInfoHex.substring(4, 20); // 医生姓名
        String nurseNameHex = baseInfoHex.substring(20, 36); // 医生姓名
        String patientNameHex = baseInfoHex.substring(36, 52); // 患者姓名
        String sexAgeHex = baseInfoHex.substring(52, 54);
        String weightHex = baseInfoHex.substring(54,56);
        String inHosDateHex = baseInfoHex.substring(56, 68);  // 入院时间


        String doctorName = toStringHex2(doctorNameHex);
        String nurseName = toStringHex2(nurseNameHex);
        String patientName = toStringHex2(patientNameHex);
        Integer age = Integer.parseInt(sexAgeHex,16);
        String str = Integer.toBinaryString(age);
        if (str.length() < 8){
            res.put("sex", "女");
        }else if(str.length() == 8 && str.startsWith("1")){
            res.put("sex", "男");
            age = age & 0x7f;   // 高位置零
        }
        Integer weight = Integer.parseInt(weightHex,16);
        String inHosDate = hexToDate(inHosDateHex);
        res.put("doctorName", doctorName);
        res.put("nurseName", nurseName);
        res.put("sickedNum",Integer.parseInt(sickedNum,16));
        res.put("age", age);
        res.put("weight", weight);
        res.put("patientName", patientName);
        return res;
    }

    //  Hex -> Date format: yyMMddHHmmss
    public String hexToDate(String hex) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder builder = new StringBuilder("20");
        // 日期
        int year = Integer.parseInt(hex.substring(0, 2), 16);
        builder.append(year + "-");
        int month = Integer.parseInt(hex.substring(2, 4), 16);
        builder.append(month + "-");
        int day = Integer.parseInt(hex.substring(4, 6), 16);
        builder.append(day + " ");
        // 时间
        int hh = Integer.parseInt(hex.substring(6, 8), 16);
        builder.append(hh + ":");
        int mm = Integer.parseInt(hex.substring(8, 10), 16);
        builder.append(mm + ":");
        int ss = Integer.parseInt(hex.substring(10, 12), 16);
        builder.append(ss);
//        Date date = sdf.parse(builder.toString());
        return builder.toString();
    }



    // 病人监护数据
    public JSONObject getMonitorDataInfo(String hex) {
        JSONObject json = new JSONObject();
        double temperBef = Integer.parseInt(hex.substring(2, 4) + hex.substring(0, 2),16);   // 体温高低位互换
        int pulse = Integer.parseInt(hex.substring(4, 6), 16);   // 脉搏
        int spo2 = Integer.parseInt(hex.substring(6, 8), 16);   // spo2
        double bloodSugerBef = Integer.parseInt(hex.substring(10, 12)+hex.substring(8, 10),16); // 血糖
        Integer urineVol = Integer.parseInt(hex.substring(14, 16)+hex.substring(12, 14),16); // 当日尿量
        int qmax = Integer.parseInt(hex.substring(18, 20)+hex.substring(16, 18),16);    // 尿流率
        int bladder = Integer.parseInt(hex.substring(20, 22), 16);   // 膀胱训练
        int bladdertimer = Integer.parseInt(hex.substring(22, 24), 16);   // 计时
        // 开关
        Integer allSwitch = Integer.parseInt(hex.substring(24,26),16);
        String switchStr = "00000000"+Integer.toBinaryString(allSwitch);
        switchStr = switchStr.substring(switchStr.length()-8);
        int urineSwitch = Integer.parseInt(switchStr.substring(3,4));   // 尿袋开关
        int infusionSwitch = Integer.parseInt(switchStr.substring(7));   // 输液开关
        int infusionSum = Integer.parseInt(hex.substring(24, 26)+hex.substring(22, 24), 16);   // 输液总量
        int progress = Integer.parseInt(hex.substring(26, 28), 16);   // 进度
        double temper = temperBef / 10;
        int bloodSugerFlag = bloodSugerBef > 32768?1:0; // 血糖测试标志位
        double bloodSuger = bloodSugerBef / 10;
        json.put("temper", temper);
        json.put("urineVol", urineVol);
        json.put("pulse", pulse);
        json.put("progress", progress);
        json.put("spo2", spo2);
        json.put("bloodSuger", bloodSuger);
        json.put("bladder", bladder);
        json.put("bladdertimer", bladdertimer);
        json.put("urineSwitch", urineSwitch);
        json.put("infusionSwitch", infusionSwitch);
        json.put("infusionSum", infusionSum);
        json.put("qmax", qmax);
        json.put("bloodSugerFlag", bloodSugerFlag);

        return json;
    }

    public JSONObject getErrorCodeInfo(String hex) {
        JSONObject json = new JSONObject();
        int errorCode = Integer.parseInt(hex.substring(0, 4), 16);   // 错误编码
        // errinfo todo 待定具体内容


        return json;
    }


    // 16进制转 ascii码
    public static String toStringHex2(String s) {
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(
                        i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "gbk");// UTF-16le:Not
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;

    }
}
