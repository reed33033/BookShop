package com.mypack.utils;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatter implements Formatter<Date> {
    //日期格式化对象
    private SimpleDateFormat dateFormat;

    public SimpleDateFormat getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(SimpleDateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    //解析
    @Override
    public Date parse(String s, Locale locale) throws ParseException {
        return dateFormat.parse(s);
    }
    //显示
    @Override
    public String print(Date date, Locale locale) {
        return dateFormat.format(date);
    }
}