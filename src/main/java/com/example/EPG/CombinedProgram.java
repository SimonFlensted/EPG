package com.example.EPG;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CombinedProgram {
	private String title;
	private List<DatePeriod> periods = new ArrayList<>();
	
	public CombinedProgram(String title) {
		this.title = title;
	}

	public void addPeriod(DatePeriod period) {
		periods.add(period);
	}

	public String getTitle() {
		return title;
	}
	
	@Override
	public String toString() {
		return title + " " + periods.stream().map(DatePeriod::toString).collect(Collectors.joining(", "));
	}
	
	
}
