package cn.concar.gw.socket;

import java.util.HashMap;
import java.util.Set;

import org.apache.log4j.Logger;

import cn.concar.gw.socket.device.Device;

public class GwServerShutdownHook extends Thread {
	
	private static final Logger LOG = Logger.getLogger(GwServerShutdownHook.class);

	@Override
	public void run() {
		LOG.info("Shuting down GW Socket Server.");
		HashMap<String, Device> conns = ConnectionPool.getConnections();
		Set<String> keys = conns.keySet();
		for (String key : keys) {
			Device dev = conns.get(key);
			LOG.info("Closing connection for session key: " + dev.getId() + " session id: " + dev.getId());
			dev.getSession().close(false);
		}
		LOG.info("All connections have been closed, GW Socket Server stopped.");
		System.exit(0);
	}

}
