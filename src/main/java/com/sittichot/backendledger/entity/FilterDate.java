package com.sittichot.backendledger.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class FilterDate {
    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private String dateFormat = "yyyy-MM-dd";
    private int year;
    private int month;
    private int day;

    public Date getDate() {
        Calendar date = Calendar.getInstance();

        if (year != 0 && month != 0 && day != 0) {
            date.set(Calendar.YEAR, year);
            date.set(Calendar.MONTH, month - 1);//base on zero
            date.set(Calendar.DAY_OF_MONTH, day);
        }

        return convertToDateFormat(date.getTimeInMillis());
    }

    public Date convertToDateFormat(Long date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            return sdf.parse(sdf.format(date));
        } catch (ParseException er) {
            log.error("date={}", date);
            throw new RuntimeException("Parse date Error");
        }
    }
}