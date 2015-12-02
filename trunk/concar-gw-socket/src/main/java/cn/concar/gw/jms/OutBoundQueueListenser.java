package cn.concar.gw.jms;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.log4j.Logger;

import cn.concar.gw.service.GwServiceConstants;
import cn.concar.gw.socket.processor.OutBoundDataProcessor;

public class OutBoundQueueListenser implements MessageListener {
	
	private static final Logger LOG = Logger.getLogger(OutBoundQueueListenser.class);


	@Override
	public void onMessage(Message message) {
		LOG.debug("Inisde onMessage.");
		MapMessage msg = (MapMessage) message;

		try {
			String sessionKey = msg.getString(GwServiceConstants.OUT_QUEUE_MAP_MSG_SESSION_KEY);
			String msgBody = msg.getString(GwServiceConstants.OUT_QUEUE_MAP_MSG_MSG_BODY);
			LOG.debug("Send message via session: " + sessionKey);
			OutBoundDataProcessor.send(sessionKey, msgBody);
		} catch (JMSException e) {
			LOG.error("Reading JMS message failure!", e);
			throw new RuntimeException(e);
		}
		
	}

}
