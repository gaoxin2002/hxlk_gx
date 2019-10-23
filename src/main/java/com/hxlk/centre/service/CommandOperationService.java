package com.hxlk.centre.service;

import com.hxlk.centre.entity.PatientBloodSuger;
import com.hxlk.centre.entity.PatientMonitorInfo;
import com.hxlk.centre.entity.vo.PatientMonitorData;
import com.hxlk.centre.mapper.PatientBloodSugerMapper;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class CommandOperationService {
//    private static final Logger logger = LoggerFactory.getLogger(CommandOperationService.class);

    @Autowired
    PatientMonitorService patientMonitorService;
    @Autowired
    HxlkHospService hxlkHospService;
    @Autowired
    HexToModelService hexToModelService;
    @Autowired
    PatientBloodSugerMapper bloodSugerMapper; // 血糖


    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // 保存数据 并响应
    public byte[] cmdOperation(byte[] msg, String hexStr) throws UnsupportedEncodingException {
        System.out.println(hexStr);
        Date d = new Date();
        byte[] sendWifiByte = new byte[0];
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
            String currentTime = sdf.format(d);
            // 传入数据转换 hex -> model
            PatientMonitorInfo patientMonitorInfo = hexToModelService.hexToPatient(hexStr);
            PatientMonitorData patientMonitorData = patientMonitorService.getPatientMonitorDataByDeviceId(patientMonitorInfo.getDevice_id());   // redis
            // 额外字段赋值
            patientMonitorInfo.setPatient_Id(patientMonitorData.getPatientId());
            patientMonitorInfo.setBedNum(patientMonitorData.getSickbedNum());
            patientMonitorInfo.setDoctorName(patientMonitorData.getDoctorName());
            patientMonitorInfo.setNurseName(patientMonitorData.getNurseName());
            patientMonitorInfo.setPatientSex(patientMonitorData.getSex());
            patientMonitorInfo.setPatientAge(patientMonitorData.getAge());
            String inHosDate = sdf.format(patientMonitorData.getInHosDate());
            patientMonitorInfo.setInHosDate(inHosDate);
            // 保存监护数据记录表
            hxlkHospService.insertPatientMonitorData(patientMonitorInfo);
            // 修改redis 膀胱倒计时 开关 进度
            updateRedisInfo(patientMonitorInfo);
            // 保存 当前监护信息表
            hxlkHospService.updateCurrentPatientMonitorByDevice(patientMonitorInfo);
            // 血糖添加
            if (patientMonitorInfo.getBloodSugerFlag() == 1){
                PatientBloodSuger bloodSuger = new PatientBloodSuger();
                bloodSuger.setPatientId(patientMonitorData.getPatientId());
                bloodSuger.setBloodSuger(patientMonitorInfo.getBlood_sugar());
                bloodSugerMapper.insertSelective(bloodSuger);
            }
            // 组装响应byte[]
            sendWifiByte = getSendWifiByte(msg, patientMonitorData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendWifiByte;
    }

    private boolean updateRedisInfo(PatientMonitorInfo patientMonitorInfo){
        PatientMonitorData toRedisInfo = new PatientMonitorData();
        toRedisInfo.setPatientId(patientMonitorInfo.getPatient_Id());
        toRedisInfo.setUrineSwitch(patientMonitorInfo.getUrine_switch());
        toRedisInfo.setTransfusionSwitch(patientMonitorInfo.getTransfusion_switch());
        toRedisInfo.setTransfusionProgress(patientMonitorInfo.getTransfusion_progress());
        toRedisInfo.setBladderTimer(patientMonitorInfo.getUrine_time());
        return patientMonitorService.updatePatientMonitorInfo(toRedisInfo);
    }


    // 相应数据
    public byte[] getSendWifiByte(byte[] recvByte, PatientMonitorData redisInfo) {
//        logger.warn("响应下位机数据: "+redisInfo.toMap());
        byte[] arr = new byte[64];
        Date date = new Date();
        byte[] dateBytes = dateToByte(date);
        try {
            arr[0] = recvByte[0];   // 编号
            arr[1] = recvByte[1];   // 设备号 1 - 4
            arr[2] = recvByte[2];   //
            arr[3] = recvByte[3];   //
            arr[4] = recvByte[4];   //
            // 时间戳 5-10
            for (int i = 0; i < dateBytes.length; i++) {
                arr[5 + i] = dateBytes[i];
            }
//            arr[11] = redisInfo.getFlag1() == 1 ? (byte) (arr[11] & 0x80) : arr[11];
//            arr[11] = redisInfo.getFlag2() == 1 ? (byte) (arr[11] & 0x7f) : arr[11];
            arr[11] = (byte)(redisInfo.getSickbedNum()/256);
            arr[11] = (byte) (arr[11] | 0x80);
            arr[11] = (byte) (arr[11] | 0x40);
            arr[12] = (byte) (redisInfo.getSickbedNum() - redisInfo.getSickbedNum()/256*256);
            for (int i=13;i<37;i++){    // 置零
                arr[i] = 0;
            }
            // 医生姓名 13-20
            byte[] doctorName = redisInfo.getDoctorName().getBytes("GBK");
            for (int i = 0; i < doctorName.length; i++) {
                arr[13 + i] = doctorName[i];
            }
            // 护士姓名 21-28
            byte[] nurseName = redisInfo.getNurseName().getBytes("GBK");
            for (int i = 0; i < nurseName.length; i++) {
                arr[21 + i] = nurseName[i];
            }
            // 患者姓名 29-36
            byte[] patientName = redisInfo.getPatientName().getBytes("GBK");
            for (int i = 0; i < patientName.length; i++) {
                arr[29 + i] = patientName[i];
            }
            // 性别年龄,最高位用于指示男女；0位女、1为男；1-127(低7位)表示年龄，
            String binaryAge = "00000000" + Integer.toBinaryString(redisInfo.getAge());
            binaryAge = binaryAge.substring(binaryAge.length() - 7);
            String binarySex = "男".equals(redisInfo.getSex()) ? "1" : "0";
            Integer sexAndAge = Integer.parseInt(binarySex + binaryAge, 2);
            arr[37] = (byte) (sexAndAge & 0xff);
            arr[38] = redisInfo.getWeight() == null?0:(byte) (redisInfo.getWeight().intValue() & 0xff);
            // 入院时间 38-43
            Date inHosDate = redisInfo.getInHosDate();
            byte[] inHosDateByte = dateToByte(inHosDate);
            for (int i = 0; i < inHosDateByte.length; i++) {
                arr[39 + i] = inHosDateByte[i];
            }
            arr[45] = recvByte[45];
            arr[46] = recvByte[46];
            arr[47] = recvByte[47];
            arr[48] = recvByte[48];
            arr[49] = recvByte[49];
            arr[50] = recvByte[50];
            arr[51] = recvByte[51];
            arr[52] = recvByte[52];
            arr[53] = recvByte[53];
            arr[55] = recvByte[55];
            arr[59] = recvByte[59];
            arr[60] = recvByte[60];
            arr[61] = recvByte[61];
            arr[62] = recvByte[62];
            arr[63] = recvByte[63];

            arr[54] = (byte) (redisInfo.getBladder() & 0xff);
//            开关
            if (redisInfo.getBladderFlag() == 1) {   // 膀胱训练标志位
                arr[56] = (byte) (arr[56] | 0x7f);
            }
            if (redisInfo.getUrineSwitchFlag() == 1) {   // 尿袋开关标志位
                arr[56] = (byte) (arr[56] | 0x20);
            }
            if (redisInfo.getTransfusionSumFlag() == 1) {   // 输液总量修改标志位
                arr[56] = (byte) (arr[56] | 0x04);
            }
            if (redisInfo.getTransfusionSwitchFlag() == 1) {   // 输液开关训练标志位
                arr[56] = (byte) (arr[56] | 0x02);
            }
//            logger.info("开关状态为: "+arr[56]);
            byte[] transferSum = intToBytes(redisInfo.getTransfusionSum() > 999?999:redisInfo.getTransfusionSum()); // 输液总量
            for (int i = 0; i < transferSum.length; i++) {
                arr[57 + i] = transferSum[i];
            }
            patientMonitorService.resetPatientFlag(redisInfo.getPatientId());
        } catch (UnsupportedEncodingException e) {
//            logger.warn("响应数据异常...");
        }

        return arr;
    }

    // 日期转换为 byte[]
    public static byte[] dateToByte(Date date) {
        byte[] arr = new byte[6];
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int year = c.get(Calendar.YEAR) - 2000;
        int month = c.get(Calendar.MONTH) + 1;
        int dd = c.get(Calendar.DAY_OF_MONTH);
        int HH = c.get(Calendar.HOUR);
        int mm = c.get(Calendar.MINUTE);
        int ss = c.get(Calendar.SECOND);
        arr[0] = (byte) year;
        arr[1] = (byte) month;
        arr[2] = (byte) dd;
        arr[3] = (byte) HH;
        arr[4] = (byte) mm;
        arr[5] = (byte) ss;
        return arr;
    }

    public String getSubString(String str, int length) throws Exception {

        int i;
        int n;
        byte[] bytes = str.getBytes("Unicode");      //使用Unicode字符集将字符串编码成byte序列
        i = 2;      //bytes的前两个字节是标志位，bytes[0] = -2, bytes[1] = -1, 故从第二位开始
        n = 0;
        for (; i < bytes.length && n < length; i++) {
            if (i % 2 == 1) {
                n++;
            } else {
                if (bytes[i] != 0) {
                    n++;
                }
            }
        }
        //去掉半个汉字
        if (i % 2 == 1) {
            if (bytes[i - 1] != 0) {
                i = i - 1;
            } else {
                i = i + 1;
            }
        }
        return new String(bytes, 0, i, "Unicode");
    }

    /**
     *  * 字符串转换成为16进制(无需Unicode编码)
     *  * @param str
     *  * @return
     *  
     */
    public static String str2HexStr(String str) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
        }
        return sb.toString().trim();
    }

    /**
     *  * 16进制直接转换成为字符串(无需Unicode解码)
     *  * @param hexStr
     *  * @return
     *  
     */
    public static String hexStr2Str(String hexStr) {
        String str = "0123456789ABCDEF";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;
        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes);
    }

    public byte[] intToBytes(int num) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) ((num >> 24) & 0xff);
        bytes[1] = (byte) ((num >> 16) & 0xff);
        bytes[2] = (byte) ((num >> 8) & 0xff);
        bytes[3] = (byte) (num & 0xff);
        return bytes;
    }
}
