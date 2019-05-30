package com.activemq.local.activemqlocal;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Listener {

	

	@JmsListener(destination = "outbound.queue")
	public void receiveMessageFromInboundQueue(final Message jsonMessage) throws JMSException {
		String messageData = null;
		System.out.println("Received message " + jsonMessage);
		String response = null;
		if (jsonMessage instanceof TextMessage) {
			TextMessage textMessage = (TextMessage) jsonMessage;
			messageData = textMessage.getText();

			response = messageData;
		}
	}
}
