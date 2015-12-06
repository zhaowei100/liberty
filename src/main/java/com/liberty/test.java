package com.liberty;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class test {

	@RequestMapping(value = "test", method = RequestMethod.GET)
	public Map<String, Object> obtainToken() {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("code", "0");
		map.put("msg", "success");

		return map;
	}
}
