package com.liberty.serializa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

public class JsonSmartSerializer<T> implements Serializer<T> {

	@Override
	public byte[] serialize(T t) {
		Map<String, Object> map = new HashMap<>();
		map.put("a",1);
		map.put("b",2);
		String value = JSONValue.toJSONString(map);
		return value.getBytes();
	}

	@Override
	public T deserialize(byte[] bytes, Class<T> class1) {
		return JSONValue.parse(bytes, class1);
	}

}
