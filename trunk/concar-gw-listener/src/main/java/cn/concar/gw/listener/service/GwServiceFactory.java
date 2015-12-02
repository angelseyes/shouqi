package cn.concar.gw.listener.service;

import java.util.HashMap;

import org.apache.log4j.Logger;

import cn.concar.gw.listener.util.Constants;

public class GwServiceFactory {
	private static final Logger LOG = Logger.getLogger(GwServiceFactory.class);
	private static HashMap<String, GwService> pool = new HashMap<String, GwService>();
	
	public static GwService getGwService() throws Exception {
		LOG.debug("Inside getGwService.");
		String service = Constants.SERVICE;
		GwService serviceInstance = pool.get(service);
		if (serviceInstance == null) {
			try {
				Class<?> clazz = Class.forName(service);
				serviceInstance = (GwService) clazz.newInstance();
				pool.put(service, serviceInstance);
				return serviceInstance;
			} catch (Exception e) {
				LOG.error("Failed to new gateway service due to unexpected error!", e);
				throw e;
			}
		} else {
			return serviceInstance;
		}
	}


}
