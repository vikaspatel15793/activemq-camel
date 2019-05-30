package com.activemq.local.activemqlocal;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class InsertProcessor implements org.apache.camel.Processor {
	
	
	
	public void process(Exchange exchange) throws Exception {
		String message = (String) exchange.getIn().getBody();
		System.out.println("Input to be persisted : " + message);

		JsonFactory factory = new JsonFactory();

		ObjectMapper mapper = new ObjectMapper(factory);
		JsonNode rootNode = mapper.readTree(message);

		String effectiveEntryDateTime = null;
		String processingDateTime = null;
		String debitAccountNumber = null;
		String creditAccountNumber = null;
		Double amount = null;
		String transId = null;

		JsonNode paymentMessageNode = getNode(rootNode, "paymentMessage");

		JsonNode metaNode = getNode(paymentMessageNode, "meta");
		effectiveEntryDateTime = getStringField(metaNode, "effectiveEntryDateTime");
		processingDateTime = getStringField(metaNode, "processingDateTime");

		JsonNode commonNode = getNode(paymentMessageNode, "common");
		debitAccountNumber = getStringField(commonNode, "debitAccountNumber");
		creditAccountNumber = getStringField(commonNode, "creditAccountNumber");
		transId = getStringField(commonNode, "referenceNumber");
		JsonNode amountNode = getNode(commonNode, "amount");
		JsonNode moneyNode = getNode(amountNode, "money");
		amount = getDoubleField(moneyNode, "amount");

		Map<String, Object> answer = new HashMap<>();
		answer.put("effectiveEntryDateTime", effectiveEntryDateTime);
		answer.put("processingDateTime", processingDateTime);
		answer.put("debitAccountNumber", debitAccountNumber);
		answer.put("creditAccountNumber", creditAccountNumber);
		answer.put("amount", amount);
		answer.put("transId", transId);

		String insertQuery = "insert into transaction (TRANSID, EFFECTIVEENTRYDATETIME,PROCESSINGDATETIME,DEBITACCOUNTNUMBER,CREDITACCOUNTNUMBER,AMOUNT)"
				+ " values ('" + transId + "','" + effectiveEntryDateTime + "','" + processingDateTime + "','"
				+ debitAccountNumber + "','" + creditAccountNumber + "'," + amount + ")";
		System.out.println("Insert Query is : " + insertQuery);
		exchange.getIn().setBody(insertQuery);
	}

	private static JsonNode getNode(JsonNode node, String field) {
		if (node.has(field)) {
			return node.path(field);
		}
		return null;
	}

	private static String getStringField(JsonNode node, String field) {
		if (node != null) {
			return node.get(field).asText();
		}
		return null;
	}

	private static Double getDoubleField(JsonNode node, String field) {
		if (node != null) {
			return node.get(field).asDouble();
		}
		return null;
	}
}