package com.liberty.serializa;

public interface Serializer<T> {

	public byte[] serialize(T t) throws Exception;
	
	public T deserialize(byte[] bytes, Class<T> class1) throws Exception;
}
