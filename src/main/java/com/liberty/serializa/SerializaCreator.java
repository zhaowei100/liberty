package com.liberty.serializa;

import java.util.Date;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.liberty.user.AddressEntry;
import com.liberty.user.UserEntry;

public class SerializaCreator {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		/**
		 * 初始化java bean
		 */
		UserEntry userEntry = new UserEntry();
		AddressEntry addressEntry = new AddressEntry();

		userEntry.setAge(25);
		userEntry.setSex(1);
		userEntry.setName("吴题");

		addressEntry.setCountry("china");
		addressEntry.setEmail("test");

		userEntry.setAddressEntry(addressEntry);

		/**
		 * 初始化各个序列化器
		 */
		JdkSerializer<UserEntry> jdkSerializer = new JdkSerializer<>();
		GsonSerializer<UserEntry> gsonSerializer = new GsonSerializer<>();
		JsonLibSerializer<UserEntry> jsonLibSerializer = new JsonLibSerializer<>();
		JsonSmartSerializer<UserEntry> jsonSmartSerializer = new JsonSmartSerializer<>();
		FastJsonSerializer<UserEntry> fastJsonSerializer = new FastJsonSerializer<>();
		JacksonSerializer<UserEntry> jacksonSerializer = new JacksonSerializer<>();

		int amount = 10000;
		// 性能测试
		Date date = new Date();
		for (int i = 0; i < amount; i++) {
			byte[] bytes = jsonLibSerializer.serialize(userEntry);
			UserEntry userEntry2 = jsonLibSerializer.deserialize(bytes,
					UserEntry.class);
		}
		System.out
				.println("jsonlib:" + (new Date().getTime() - date.getTime()));

		// 性能测试
		date = new Date();
		for (int i = 0; i < amount; i++) {
			byte[] bytes = jdkSerializer.serialize(userEntry);
			UserEntry userEntry2 = jdkSerializer.deserialize(bytes,
					UserEntry.class);
		}
		System.out.println("jdk:" + (new Date().getTime() - date.getTime()));

		// 性能测试
		date = new Date();
		for (int i = 0; i < amount; i++) {
			byte[] bytes = gsonSerializer.serialize(userEntry);
			UserEntry userEntry2 = gsonSerializer.deserialize(bytes,
					UserEntry.class);
		}
		System.out.println("gson:" + (new Date().getTime() - date.getTime()));

		// 性能测试
		date = new Date();
		for (int i = 0; i < amount; i++) {
			byte[] bytes = fastJsonSerializer.serialize(userEntry);
			UserEntry userEntry2 = fastJsonSerializer.deserialize(bytes,
					UserEntry.class);
		}
		System.out.println("fastJson:"
				+ (new Date().getTime() - date.getTime()));

		// 性能测试
		date = new Date();
		byte[] bytes = null;
		for (int i = 0; i < amount; i++) {
			bytes = jacksonSerializer.serialize(userEntry);
			UserEntry userEntry2 = jacksonSerializer.deserialize(bytes,
					UserEntry.class);
		}
		System.out
				.println("jackson:" + (new Date().getTime() - date.getTime()));

		/**
		 * 带null的json转换为jsonobject测试
		 * 
		 */
		// json smart
		JSONObject jsonObject = JSONValue.parse(bytes, JSONObject.class);

		// json lib
		net.sf.json.JSONObject jsonObject2 = net.sf.json.JSONObject
				.fromObject(new String(bytes));

		// gson
		Gson gson = new Gson();
		JsonObject jsonObject3 = gson.fromJson(new String(bytes),
				JsonObject.class);

		// fastjson
		com.alibaba.fastjson.JSONObject jsonObject4 = JSON
				.parseObject(new String(bytes));
		jsonObject4.get("id");
		System.out.println(new String(bytes));

		// bytes = jsonSmartSerializer.serialize(userEntry);
		// System.out.println(new String(bytes));
		// JSONObject jsonObject = JSONValue.parse(bytes, JSONObject.class);
		// System.out.println();
		// userEntry2 = jsonSmartSerializer.deserialize(bytes, UserEntry.class);
		// System.out.println(bytes);
	}
}
