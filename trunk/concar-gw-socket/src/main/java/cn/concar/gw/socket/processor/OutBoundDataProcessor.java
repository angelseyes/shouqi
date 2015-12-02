package cn.concar.gw.socket.processor;

import org.apache.log4j.Logger;

import cn.concar.gw.socket.ConnectionPool;
import cn.concar.gw.socket.device.Device;

public class OutBoundDataProcessor {
	private static final Logger LOG = Logger.getLogger(OutBoundDataProcessor.class);
	
	public static void send(String sessionKey, String msgBody) {
		LOG.debug("Inside send.");
		Device dev = ConnectionPool.get(sessionKey);
		if (dev != null) {
			dev.send(msgBody);
		} else {
			//TODO Write error message that the session can not be found in the cache into DB for further retry.
		}
	}

}
