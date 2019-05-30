package com.activemq.local.activemqlocal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class TestController {

	@Autowired
	private JmsTemplate jmsTemplate;

	@GetMapping("/send")
	public void test() throws JmsException, JsonProcessingException {
		String message = "{\r\n" + "   \"log_timeStamp\":\"\",\r\n" + "   \"log_date\":\"May 9, 2019 5:33:56 PM\",\r\n"
				+ "   \"loghostname\":\"\",\r\n" + "   \"timestamp\":\"2019-05-09T17:33:56.022\",\r\n"
				+ "   \"fromExchange\":\"\",\r\n" + "   \"fromEndpoint\":\"MTS BOI\",\r\n"
				+ "   \"fromRoute\":\"\",\r\n" + "   \"node\":\"\",\r\n" + "   \"paymentMessage\":{\r\n"
				+ "      \"meta\":{\r\n" + "         \"mssageId\":\"121233\",\r\n"
				+ "         \"applicationName\":{\r\n" + "            \"name\":\"MTS - Wires\",\r\n"
				+ "            \"description\":\"\"\r\n" + "         },\r\n" + "         \"state\":\"DEBIT\",\r\n"
				+ "         \"stateDescription\":\"\",\r\n" + "         \"paymentType\":{\r\n"
				+ "            \"name\":\"OUTBOUND\",\r\n" + "            \"description\":\"\"\r\n" + "         },\r\n"
				+ "         \"creationDateTime\":\"2012-07-09T17:33:50.900-07:00\",\r\n"
				+ "         \"effectiveEntryDateTime\":\"20112-07-09T17:33:50.900-07:00\",\r\n"
				+ "         \"processingDateTime\":\"2012-07-09T17:33:50.900-07:00\"\r\n" + "      },\r\n"
				+ "      \"common\":{\r\n" + "         \"referenceNumber\":\"232312\",\r\n"
				+ "         \"amount\":{\r\n" + "            \"money\":{\r\n" + "               \"currency\":{\r\n"
				+ "                  \"code\":\"CAD\",\r\n" + "                  \"numericCode\":123,\r\n"
				+ "                  \"decimalPlaces\":2\r\n" + "               },\r\n"
				+ "               \"amount\":41.00\r\n" + "            }\r\n" + "         },\r\n"
				+ "         \"debitAccountNumber\":\"ach\",\r\n" + "         \"debitAccountTransit\":\"1233\",\r\n"
				+ "         \"creditAccountNumber\":\"mas\",\r\n"
				+ "         \"creditAccountMemberNumber\":\"sdsd\",\r\n"
				+ "         \"creditSettlementPartyId\":\"asasa\",\r\n"
				+ "         \"debitSettlementPartyId\":\"asasa\"\r\n" + "      },\r\n"
				+ "      \"appToString\":\"test\"\r\n" + "   }\r\n" + "}";
		jmsTemplate.convertAndSend("inbound.queue", message);
	}

}
