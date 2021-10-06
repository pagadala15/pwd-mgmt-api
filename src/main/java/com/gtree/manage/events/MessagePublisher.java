package com.gtree.manage.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

/**
 * 
 * @author SrinivasuluP This class is used to publish the message to ActiveMQ
 */
@Component
public class MessagePublisher {

	private final JmsTemplate jmsTemplate;

	@Autowired
	public MessagePublisher(JmsTemplate jmsTemplate, MessageConverter messageConverter) {
		this.jmsTemplate = jmsTemplate;
		this.jmsTemplate.setMessageConverter(messageConverter);
	}

	public void publishMessage(PasswordResetEvent message) {
		jmsTemplate.convertAndSend("PasswordUpdateQueue", message);
	}

}