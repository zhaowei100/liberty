package com.liberty.serializa;

import com.google.gson.Gson;

public class GsonSerializer<T> implements Serializer<T>{

	private Gson gson = new Gson();
	
	@Override
	public byte[] serialize(T t) {
		return gson.toJson(t).getBytes();
	}

	@Override
	public T deserialize(byte[] bytes, Class<T> class1) {
		return gson.fromJson(new String(bytes), class1);
	}

}
