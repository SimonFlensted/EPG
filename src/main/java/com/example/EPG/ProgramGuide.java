package com.example.EPG;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProgramGuide {

	public static final String[] WEEKDAYS = new String[] { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday",
	"Sunday" };
	
	public Map<String, List<CombinedProgram>> daysToCombinedPrograms;

	public ProgramGuide(Map<String, List<CombinedProgram>> daysToCombinedPrograms) {
		this.daysToCombinedPrograms = daysToCombinedPrograms;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(String weekday : WEEKDAYS) {
			List<CombinedProgram> combinedPrograms = daysToCombinedPrograms.getOrDefault(weekday.toLowerCase(), Collections.emptyList());
			sb.append(weekday + ": " + (combinedPrograms.isEmpty() ? "Nothing aired today\n" : combinedPrograms.stream().map(CombinedProgram::toString).collect(Collectors.joining(" / ")) + "\n"));
		}
		
		return sb.toString();
	}

}
