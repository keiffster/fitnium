package com.magneticreason.fitnium.plugins.tokens;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateToken extends FitniumToken {

    final static String DATE_FORMAT = "yyyy-MM-dd";

    public String getNow() {
        return toString(new java.util.Date());
    }

    public String getAfter(int days) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, days);
        return toString(cal.getTime());
    }

    public String getBefore(int days) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1 * days);
        return toString(cal.getTime());
    }

    public String toString(Object source) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        String result = formatter.format((Date) source);
        return result;
    }
}
