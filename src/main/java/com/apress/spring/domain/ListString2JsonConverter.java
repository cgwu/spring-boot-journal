package com.apress.spring.domain;

import java.io.IOException;
import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;;

/*
 * */
@Converter
public class ListString2JsonConverter implements AttributeConverter<List<String>, String> {

	@Override
	public String convertToDatabaseColumn(List<String> attribute) {
		if(attribute == null || attribute.size() == 0) return "[]";
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try {
			json = mapper.writeValueAsString(attribute);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> convertToEntityAttribute(String dbData) {
		ObjectMapper mapper = new ObjectMapper();
		List<String> list = null;
		try {
			list = (List<String>) mapper.readValue(dbData, List.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

}
