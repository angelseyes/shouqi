package cn.concar.gw.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.jms.core.MessageCreator;

public class SendJMSMessage {
	private static final Logger LOG = Logger.getLogger(SendJMSMessage.class);
	
	public static void send(final String message) {
		LOG.debug("Send message: " + message);
		InitJms.sender.send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage jmsMsg = session.createTextMessage();
				jmsMsg.setText(message);
				return jmsMsg;
			}
		
		});
	}
	
}
