package com.liberty.serializa;

import java.io.StringWriter;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonSerializer<T> implements Serializer<T>{

	@Override
	public byte[] serialize(T t) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		StringWriter writer = new StringWriter();
		JsonGenerator jsonGenerator = new JsonFactory().createGenerator(writer);
		objectMapper.writeValue(jsonGenerator, t);
		jsonGenerator.close();
		return writer.toString().getBytes();
	}

	@Override
	public T deserialize(byte[] bytes, Class<T> class1) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(bytes, class1);
	}

}
