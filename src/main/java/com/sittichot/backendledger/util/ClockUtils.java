package com.sittichot.backendledger.util;

import java.util.Calendar;
import java.util.Date;

public class ClockUtils {

    public static Date now() {
        return new Date();
    }

    public static Date createDate(int year, int month, int day, int hour, int minute, int second, int millisecond) {
        Calendar calendar = getCalendarInstance();

        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, millisecond);

        return calendar.getTime();
    }

    public static Date createDate(int year, int month, int day) {
        return createDate(year, month, day, 0, 0, 0, 0);
    }

    public static Date createDateFuture(int year, int month, int day) {
        return createDateFuture(year, month, day, 0, 0, 0, 0);
    }

    public static Date createDateFuture(int year, int month, int day, int hour, int minute, int second, int millisecond) {
        Calendar calendar = getCalendarInstance();

        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.YEAR);
        int currentDay = calendar.get(Calendar.YEAR);
        int currentHour = calendar.get(Calendar.YEAR);
        int currentMinute = calendar.get(Calendar.YEAR);
        int currentSecond = calendar.get(Calendar.YEAR);
        int currentMillisecond = calendar.get(Calendar.YEAR);

        calendar.set(Calendar.YEAR, currentYear + year);
        calendar.set(Calendar.MONTH, currentMonth + month);
        calendar.set(Calendar.DAY_OF_MONTH, currentDay + day);
        calendar.set(Calendar.HOUR, currentHour + hour);
        calendar.set(Calendar.MINUTE, currentMinute + minute);
        calendar.set(Calendar.SECOND, currentSecond + second);
        calendar.set(Calendar.MILLISECOND, currentMillisecond + millisecond);

        return calendar.getTime();
    }

    public static Date createDateFutureByField(int field, int time) {
        Calendar calendar = getCalendarInstance();

        int currentDate = calendar.get(field);
        calendar.set(field, currentDate + time);

        return calendar.getTime();
    }

    private static Calendar getCalendarInstance() {
        return Calendar.getInstance();
    }
}
