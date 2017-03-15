package com.pl;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CustomDateSerializer extends JsonSerializer<Date>{

	@Override
	public void serialize(
			Date date, 
			JsonGenerator jsonGenerator, 
			SerializerProvider serializerProvider
		) throws IOException, JsonProcessingException {
		
		//System.out.println("Serializando fechas...");

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		jsonGenerator.writeString(format.format(date));
		
	}

}
