package cn.concar.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.concar.service.util.ServiceConstants;

/**
 * <p>
 * <b>Class name</b>: BeanFactory
 * </p>
 * <p>
 * <b>Class description</b>: BeanFactory loads the spring context and produce
 * beans from spring context.
 * </p>
 * <p>
 * <b>Author</b>: haoli
 * </p>
 * <b>Change History</b>:<br/>
 * <p>
 * 
 * <pre>
 * Date          Author       Revision     Comments
 * ----------    ----------   --------     ------------------
 * 2015-11-20   	 haoli      1.0          Initial Creation
 * 
 * </pre>
 * 
 * </p>
 */
public class BeanFactory {
	/**
	 * context Spring context.
	 */
	private static final ApplicationContext context = new ClassPathXmlApplicationContext(
			ServiceConstants.SPRING_CONEXT_XML);

	/**
	 * @param clazz
	 *            Service class.
	 * @return Service instance.
	 */
	public static <T> T getService(final Class<T> clazz) {
		return context.getBean(clazz);
	}

	/**
	 * Construct method.
	 */
	private BeanFactory() {

	}
}
