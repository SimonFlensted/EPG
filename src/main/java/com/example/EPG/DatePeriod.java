package com.example.EPG;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DatePeriod {
	private Date start;
	private Date end;
	
	public DatePeriod(Date start, Date end) {
		this.start = start;
		this.end = end;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
		String startString = formatter.format(start).replaceFirst("^0+(?!$)", "").replace(":00", "");
		String endString = formatter.format(end).replaceFirst("^0+(?!$)", "").replace(":00", "");;
		return startString + " - " + endString;
	}
	
}
