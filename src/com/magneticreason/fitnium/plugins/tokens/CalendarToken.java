package com.magneticreason.fitnium.plugins.tokens;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class CalendarToken extends FitniumToken {
    final static String CALENDAR_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public String getNow() {
		return toString(Calendar.getInstance());
	}

	public String getAfter(int days) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, days);
		return toString(cal);
	}

	public String getBefore(int days) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1 * days);
		return toString(cal);
	}

	public String toString(Calendar source) {
        SimpleDateFormat formatter = new SimpleDateFormat(CALENDAR_FORMAT);
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        return formatter.format(((Calendar)source).getTime());
	}

}
