package com.example.EPG;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = ProgramDeserializer.class)
public class Program implements Comparable<Program> {
	private String title;
	private String state;
	private String weekday;
	private Date time;

	public Program(String title, String state, Date time) {
		this.title = title;
		this.state = state;
		this.time = time;
	}

	public String getTitle() {
		return title;
	}

	public String getState() {
		return state;
	}

	public Date getTime() {
		return time;
	}

	public String getWeekday() {
		return weekday;
	}

	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}

	@Override
	public int compareTo(Program o) {
		List<String> weekdays = Arrays.asList(ProgramGuide.WEEKDAYS).stream().map(w -> w.toLowerCase())
				.collect(Collectors.toList());
		if (weekdays.indexOf(this.weekday) == weekdays.indexOf(o.getWeekday())) {
				return this.time.compareTo(o.time);
		} else {
			return Integer.compare(weekdays.indexOf(this.weekday), weekdays.indexOf(o.getWeekday()));
		}
	}

}
