package cn.concar.gw.socket.processor;

import org.apache.log4j.Logger;

import cn.concar.gw.jms.SendJMSMessage;

public class DataProcessor implements Runnable{
	
	private static final Logger LOG = Logger.getLogger(DataProcessor.class);
	
	private String data;
	
	public DataProcessor(String data) {
		this.data = data;
	}
	

	@Override
	public void run() {
		LOG.debug("Sending message to JMS.");
		SendJMSMessage.send(data);
	}

}
