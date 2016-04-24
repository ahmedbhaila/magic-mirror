package com.bhailaverse;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ChowkidaarRestController {
	
	private static final String AAINA_URL = "/aaina";
	
	@Autowired
	AainaService aainaService;
	
	@RequestMapping(AAINA_URL)
	public String getWeather() throws Exception {
		return aainaService.getMirrorData().toBlocking().single();
	}
}

