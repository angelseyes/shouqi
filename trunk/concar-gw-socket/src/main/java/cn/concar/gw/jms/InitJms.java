package cn.concar.gw.jms;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import cn.concar.gw.util.Constants;

public final class InitJms {
	private static final Logger LOG = Logger.getLogger(InitJms.class);
	private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
			Constants.SPRING_CONTEXT);
	public static JmsTemplate sender;
	private static DefaultMessageListenerContainer outBoundQueueContainer;
	static {
		LOG.info("Init JMS context.");
		try {
			sender = (JmsTemplate) applicationContext.getBean("jmsTemplate");
			outBoundQueueContainer = (DefaultMessageListenerContainer) applicationContext.getBean("outBoundQueueContainer");
		} catch (Exception e) {
			LOG.error("Init JMS failure!", e);
			throw new RuntimeException(e.getMessage());
		}
		LOG.info("Init JMS context completed.");
	}

	/**
	 * startJMSContainer:(Describe the usage of this method). Start JMS
	 * container if it's not running up.
	 * 
	 * @author haoli
	 */
	public synchronized static void startJMSContainer() {
		LOG.debug("Inside startJMSContainer.");
		if (!outBoundQueueContainer.isRunning()) {
			outBoundQueueContainer.start();
		} else {
			LOG.info("JMS listener container had already started.");
		}
		LOG.info("JMS listener container started.");
	}

	/**
	 * stopJMSContainer:(Describe the usage of this method). Stop JMS container.
	 * 
	 * @author haoli
	 */
	public synchronized static void stopJMSContainer() {
		LOG.info("Inside stopJMSContainer.");
		if (outBoundQueueContainer.isRunning()) {
			outBoundQueueContainer.stop();
		} else {
			LOG.info("JMS listener container had already stopped.");
		}
		LOG.info("JMS listener container stoped.");
	}
}
