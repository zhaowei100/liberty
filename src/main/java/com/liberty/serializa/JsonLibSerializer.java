package com.liberty.serializa;

import net.sf.json.JSONObject;

public class JsonLibSerializer<T> implements Serializer<T>{

	@Override
	public byte[] serialize(T t) {
		return JSONObject.fromObject(t).toString().getBytes();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T deserialize(byte[] bytes, Class<T> class1) {
		return (T) JSONObject.toBean(JSONObject.fromObject(new String(bytes)), class1);
	}

}
