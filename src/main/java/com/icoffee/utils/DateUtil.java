package com.icoffee.utils;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *  класс для работы с датой
 */
@ManagedBean(name = "dater")
@ApplicationScoped
public class DateUtil {

    private static final String datePattern = "dd/MM/yyyy HH:mm";

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(datePattern);

    /**
     *  добавляем часы к дате
     */
    public static Date plusHours(Date date, int hours){
        Calendar calendar = GregorianCalendar.getInstance();
        if(date != null){
            calendar.setTime(date);
        }
        calendar.add(Calendar.HOUR, hours);
        return calendar.getTime();
    }

    public static SimpleDateFormat getSimpleDateFormat() {
        return SIMPLE_DATE_FORMAT;
    }

    public String getDatePattern() {
        return datePattern;
    }
}
