package com.activemq.local.activemqlocal;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class TestController {

	@Autowired
	private JmsTemplate jmsTemplate;

	@GetMapping("/send")
	public void test() throws JmsException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> map = new HashMap<>();
		map.put("name", "test");
		jmsTemplate.convertAndSend("inbound.queue", mapper.writeValueAsString(map));
	}

}
