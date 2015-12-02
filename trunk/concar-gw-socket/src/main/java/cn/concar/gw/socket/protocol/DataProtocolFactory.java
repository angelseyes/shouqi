package cn.concar.gw.socket.protocol;

import java.util.HashMap;

import org.apache.log4j.Logger;

import cn.concar.gw.util.Constants;

public class DataProtocolFactory {
	private static final Logger LOG = Logger.getLogger(DataProtocolFactory.class);
	private static HashMap<String, Class<?>> pool = new HashMap<String, Class<?>>();
	
	public static DataProtocol getDataProtocol() throws Exception {
		LOG.debug("Inside getDataProtocol.");
		String protocol = Constants.PROTOCOL;
		Class<?> clazz = pool.get(protocol);
		if (clazz == null) {
			try {
				clazz = Class.forName(protocol);
				pool.put(protocol, clazz);
				return (DataProtocol) clazz.newInstance();
			} catch (Exception e) {
				LOG.error("Failed to new data protocol due to unexpected error!", e);
				throw e;
			}
		} else {
			try {
				return (DataProtocol) clazz.newInstance();
			} catch (Exception e) {
				LOG.error("Failed to new data protocol due to unexpected error!", e);
				throw e;
			}
		}
	}
}
