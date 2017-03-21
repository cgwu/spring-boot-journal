package com.apress.spring.domain;

import java.sql.SQLException;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import org.postgresql.util.PGobject;;

/*
 * 此Converter使用EclipseLink，可以正常运作，而Hibernate下不行.
 * */
@Converter	//(autoApply = true)
public class String2JsonConverter implements AttributeConverter<String, PGobject> {

	public PGobject convertToDatabaseColumn(String arg0) {
		PGobject obj = new PGobject();
		obj.setType("json");
		try {
			obj.setValue(arg0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	public String convertToEntityAttribute(PGobject arg0) {
		return arg0.getValue();
	}

}
