package com.liberty;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liberty.solr.SearchBean;
import com.liberty.solr.service.SearchService;

@Controller
public class HomeController {

	private Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private SearchService searchService;

	@RequestMapping("")
	public String homeIndex() {

		return "index";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(String userId, String title, String content,
			String tags, Float score, Integer amount) {

		SearchBean searchBean = new SearchBean();
		searchBean.setAmount(amount);
		searchBean.setContent(content);
		searchBean.setCreateTime(new Date());
		searchBean.setScore(score);
		searchBean.setTags(tags.split(","));
		searchBean.setTitle(title);
		searchBean.setUserId(userId);
		searchBean.setId(UUID.randomUUID().toString());

		searchService.addBean(searchBean);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("data", searchBean);

		return map;
	}
}
