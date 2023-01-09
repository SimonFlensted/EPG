package com.example.EPG;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class ProgramDeserializer extends StdDeserializer<Program>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -515588736102974008L;

	public ProgramDeserializer() {
		this(null);
	}

	public ProgramDeserializer(Class<?> vc) {
		super(vc);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Program deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
		JsonNode node = p.getCodec().readTree(p);
		String title = node.get("title").asText();
		String state = node.get("state").asText();
		
		Date time = Date.from(Instant.ofEpochSecond(node.get("time").asInt()));
		
		return new Program(title, state, time);
	}

}
