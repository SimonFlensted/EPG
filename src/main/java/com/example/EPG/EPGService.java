package com.example.EPG;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EPGService {

	private ObjectMapper objectMapper;

	public EPGService() {
		objectMapper = new ObjectMapper();
	}

	public ProgramGuide GetHumanReadableEPG(String json) throws JsonMappingException, JsonProcessingException {
		TypeReference<HashMap<String, List<Program>>> daysToPrograms = new TypeReference<HashMap<String, List<Program>>>() {};

		HashMap<String, List<Program>> daysToProgramsMap = objectMapper.readValue(json, daysToPrograms);
		Map<String, List<CombinedProgram>> daysToCombinedPrograms = getCombinedPrograms(daysToProgramsMap);
		return new ProgramGuide(daysToCombinedPrograms);
	}

	private Map<String, List<CombinedProgram>> getCombinedPrograms(Map<String, List<Program>> daysToProgramsMap) {
		Map<String, Map<String, CombinedProgram>> daysToCombinedPrograms = new HashMap<>();
		List<Program> enrichedPrograms = getEnrichedPrograms(daysToProgramsMap);

		CombinedProgram temp = null;
		Date start = null;
		Date end = null;
		for (Program program : enrichedPrograms) {
			if (program.getState().equals("begin")) {
				temp = daysToCombinedPrograms.computeIfAbsent(program.getWeekday(), k -> new LinkedHashMap<>())
						.computeIfAbsent(program.getTitle(), k -> new CombinedProgram(program.getTitle()));
				start = program.getTime();
			} else if (program.getState().equals("end")) {
				end = program.getTime();
				temp.addPeriod(new DatePeriod(start, end));
			}
		}

		return daysToCombinedPrograms.entrySet().stream().collect(
				Collectors.toMap(k -> k.getKey(), v -> v.getValue().values().stream().collect(Collectors.toList())));
	}

	private List<Program> getEnrichedPrograms(Map<String, List<Program>> daysToProgramsMap) {
		daysToProgramsMap.keySet().forEach(k -> daysToProgramsMap.get(k).forEach(p -> p.setWeekday(k)));
		return daysToProgramsMap.values().stream().flatMap(l -> l.stream()).sorted().collect(Collectors.toList());
	}

}
