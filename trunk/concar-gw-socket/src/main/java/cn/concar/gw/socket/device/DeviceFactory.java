package cn.concar.gw.socket.device;

import java.util.HashMap;

import org.apache.log4j.Logger;

import cn.concar.gw.util.Constants;

public class DeviceFactory {
	private static final Logger LOG = Logger.getLogger(DeviceFactory.class);
	private static HashMap<String, Class<?>> pool = new HashMap<String, Class<?>>();
	
	public static Device getDevice() throws Exception {
		LOG.debug("Inside getDevice.");
		String device = Constants.DEVICE_TYPE;
		Class<?> clazz = pool.get(device);
		if (clazz == null) {
			try {
				clazz = Class.forName(device);
				pool.put(device, clazz);
				return (Device) clazz.newInstance();
			} catch (Exception e) {
				LOG.error("Failed to new device due to unexpected error!", e);
				throw e;
			}
		} else {
			try {
				return (Device) clazz.newInstance();
			} catch (Exception e) {
				LOG.error("Failed to new device due to unexpected error!", e);
				throw e;
			}
		}

	}
}
