package com.gtree.manage.events;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.messaging.converter.MessageConversionException;
import org.springframework.stereotype.Component;

/**
 * 
 * @author SrinivasuluP this class is used by the framework to automatically
 *         convert Event to message, message to event class.
 */
@Component
public class JmsMessageConverter implements MessageConverter {

	/**
	 * Method to convert object to Event class
	 */
	@Override
	public Message toMessage(Object messageObject, Session session) throws JMSException, MessageConversionException {
		PasswordResetEvent msg = (PasswordResetEvent) messageObject;
		MapMessage message = session.createMapMessage();
		message.setString("message", msg.toString());
		return message;
	}

	/**
	 * Method to convert Event class to message object
	 */
	@Override
	public Object fromMessage(Message message) throws JMSException, MessageConversionException {
		MapMessage mapMessage = (MapMessage) message;
		return mapMessage.getString("message");
	}
}
