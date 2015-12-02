package cn.concar.gw.socket;

import java.util.HashMap;

import cn.concar.gw.socket.device.Device;

public final class ConnectionPool {
	
	private static HashMap<String, Device> connections = new HashMap<String, Device>();
	
	public static synchronized void put(String key, Device device) {
		Device dev = connections.get(key);
		if (dev == null) {
			connections.put(key, device);
		} else {
			if (dev != device) {
				connections.remove(key);
				connections.put(key, device);
			}
		}

	}
	
	public static synchronized Device get(String key) {
		return connections.get(key);
	}
	
	public static synchronized Device delete(String key) {
		return connections.remove(key);
	}

	public static HashMap<String, Device> getConnections() {
		return connections;
	}
	
	

}
