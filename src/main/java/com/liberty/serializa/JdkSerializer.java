package com.liberty.serializa;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class JdkSerializer<T> implements Serializer<T> {

	@Override
	public byte[] serialize(T t) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(
				256);
		ObjectOutputStream outputStream = new ObjectOutputStream(
				byteArrayOutputStream);
		outputStream.writeObject(t);
		outputStream.flush();
		return byteArrayOutputStream.toByteArray();
	}

	@Override
	public T deserialize(byte[] bytes, Class<T> class1) throws IOException,
			ClassNotFoundException {
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
				bytes);
		ObjectInputStream inputStream = new ObjectInputStream(
				byteArrayInputStream);
		return (T) inputStream.readObject();
	}

}
