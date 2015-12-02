package baic.obd.gateway.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import baic.obd.gateway.utils.Constants;


public class BeanFactory {
	/**
	 * context Spring context.
	 */
	private static final ApplicationContext context = new ClassPathXmlApplicationContext(
			Constants.SPRING_CONEXT_XML);

	/**
	 * @param clazz
	 *            Service class.
	 * @return Service instance.
	 */
	public static <T> T getBean(final Class<T> clazz) {
		return context.getBean(clazz);
	}

	/**
	 * Construct method.
	 */
	private BeanFactory() {

	}
}
