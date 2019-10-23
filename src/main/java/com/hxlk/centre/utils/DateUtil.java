package com.hxlk.centre.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public  class DateUtil {

    public static   List<String> getBetweenDates(String start, String end) {

        List<String> result = new ArrayList<String>();

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date start_date = sdf.parse(start);
            Date end_date = sdf.parse(end);
            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start_date);
            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end_date);
            while (tempStart.before(tempEnd) || tempStart.equals(tempEnd)) {
                result.add(sdf.format(tempStart.getTime()));
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> betweenDates = DateUtil.getBetweenDates("2019-9-23","2019-9-23");
        System.out.println(betweenDates);
    }
}
