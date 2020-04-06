package com.windea.study.java8;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatThreadLocal {
    private static final ThreadLocal<DateFormat> format = ThreadLocal
        .withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    public static Date convert(String source) throws Exception {
        return format.get().parse(source);
    }
}
