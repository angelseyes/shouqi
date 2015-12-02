package cn.concar.gw.listener.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

import cn.concar.gw.listener.service.GwService;
import cn.concar.gw.listener.service.GwServiceFactory;

public class InProcessingQueueListenser implements MessageListener {
	
	private Logger LOG = Logger.getLogger(InProcessingQueueListenser.class);

	@Override
	public void onMessage(Message message) {
		LOG.info("Incoming vehicle message.");
		TextMessage msg = (TextMessage) message;
		GwService service = null;
		try {
			service = GwServiceFactory.getGwService();
		} catch (Exception e) {
			LOG.error("Failed to get gw service!", e);
			throw new RuntimeException(e);
		}
		
		if (service != null) {
			try {
				String text = msg.getText();
				LOG.info("Processing message: " + text);
				service.process(text);
			} catch (JMSException e) {
				LOG.error("JMS failure to get test message!", e);
				throw new RuntimeException(e);
			}
		} else {
			LOG.warn("Null gw service!");
		}
	}

}
