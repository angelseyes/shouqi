package cn.concar.gw.socket;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

import cn.concar.gw.service.exception.ConcarGwServiceException;
import cn.concar.gw.service.proxy.SocketSessionEventServiceProxy;
import cn.concar.gw.socket.device.Device;
import cn.concar.gw.util.Constants;

public class SocketSessionUtil {
	private static final Logger LOG = Logger.getLogger(SocketSessionUtil.class);
	
	public static final String SESSION_CLOSED_MSG = "SESSION_CLOSED";
	public static final String SESSION_OPENED_MSG = "SESSION_OPENED";
	public static final String SESSION_STAT_DB_ERR= "DB_ERR";
	public static final String SESSION_STAT_NULL_KEY= "NULL_KEY";
	public static final String SESSION_STAT_EXCEED_MX_IDEL_TIME = "EX_MX_TIME";
	
	public static void closeSession(Device device) {
		IoSession session = device.getSession();
		if (device.getId() != null) {
			Device dev = ConnectionPool.delete(device.getId());
			if (Constants.CLUSTER_MODE) {
				try {
					String session_close_msg = "";
					if (device.getStatCode() != null) {
						session_close_msg = device.getStatCode();
					} else {
						session_close_msg = SESSION_CLOSED_MSG;
					}
					SocketSessionEventServiceProxy.sessionClosed(device.getId(), session.getId(), session_close_msg, device.getDeviceIp(), device.getConnectedHost(), device.getType());
				} catch (ConcarGwServiceException e) {
					LOG.error("Close session failure! key: " + device.getId() + " session id: " + session.getId(), e);
					if (dev != null) {
						ConnectionPool.put(dev.getId(), dev);
					}
				}
			}
		} else {
			LOG.debug("Close null device id session. session id: " + session.getId());
		}
	}
	
	public static void openSession(Device device) {
		IoSession session = device.getSession();
		ConnectionPool.put(device.getId(), device);
		if (Constants.CLUSTER_MODE) {
			try {
				SocketSessionEventServiceProxy.sessionOpened(device.getId(), session.getId(), SESSION_OPENED_MSG, device.getDeviceIp(), device.getConnectedHost(), device.getType());
			} catch (Exception e) {
				LOG.error("Open session failure!  key: " + device.getId() + " session id: " + session.getId(), e);
				LOG.info("Open session failure, close it.");
				device.setStatCode(SESSION_STAT_DB_ERR);
				session.close(false);
			}
		}
	}

}
