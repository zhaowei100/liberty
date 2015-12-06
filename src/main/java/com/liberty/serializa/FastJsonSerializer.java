package com.liberty.serializa;

import com.alibaba.fastjson.JSON;

public class FastJsonSerializer<T> implements Serializer<T>{

	@Override
	public byte[] serialize(T t) throws Exception {
		return JSON.toJSONBytes(t);
	}

	@Override
	public T deserialize(byte[] bytes, Class<T> class1) throws Exception {
		return JSON.parseObject(bytes, class1);
	}

}
