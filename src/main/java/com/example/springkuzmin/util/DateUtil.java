package com.example.springkuzmin.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static Date addMonth(Date date, int months) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, months);
        return calendar.getTime();
    }
}
