package cn.concar.gw.socket;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import cn.concar.gw.socket.device.Device;
import cn.concar.gw.socket.device.DeviceFactory;
import cn.concar.gw.socket.processor.DataProcessor;
import cn.concar.gw.socket.protocol.DeviceMessage;
import cn.concar.gw.util.Constants;

public class MessageHandler implements IoHandler {
	private static final Logger LOG = Logger.getLogger(MessageHandler.class);
	private static ScheduledExecutorService  executor = Executors.newSingleThreadScheduledExecutor();

	private static boolean open_session_event = false;

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		LOG.info("Inside messageReceived.");
		byte data[] = (byte[]) message;
		Device device = (Device) session.getAttribute(Constants.SESSION_ATTR_DEV);
		DeviceMessage deviceMessage = null;
		if (device == null) {
			device = DeviceFactory.getDevice();
			device.setSession(session);
			device.setConnectedHost(Constants.HOST);
			session.setAttribute(Constants.SESSION_ATTR_DEV, device);
			data = device.getProtocol().getFirstMsg(data);
		} 
		deviceMessage= device.handle(data);
		if (deviceMessage != null) {
			if (open_session_event) {
				if (deviceMessage.getDeviceId() != null) {
					SocketSessionUtil.openSession(device);
				} else {
					LOG.warn("No device id could be identified in the message.");
					LOG.info("Close null device id session.");
					device.setStatCode(SocketSessionUtil.SESSION_STAT_NULL_KEY);
					session.close(false);
				}
			}
			if (!deviceMessage.isPartial()) {
				DataProcessor processor = new DataProcessor(deviceMessage.getMessage());
				executor.schedule(processor, 0, TimeUnit.MILLISECONDS);
			} else {
				//TODO Add waiting message logic
				LOG.warn("Waiting message logic not supported.");
			}
		} else {
			LOG.info("Get null device message.");
		}
		open_session_event = false;
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		LOG.debug("Session created.");
		
		
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		LOG.debug("Session opened, session id: " + session.getId());
		open_session_event = true;
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		LOG.debug("Inside sessionClosed.");
		Device device = (Device) session.getAttribute(Constants.SESSION_ATTR_DEV);
		if (device != null) {
			SocketSessionUtil.closeSession(device);
		}
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		LOG.debug("Session id: " + session.getId() + " idel count: " + session.getBothIdleCount());
		if (session.getBothIdleCount() * Constants.IDEL_TIME >= Constants.MAX_SESSION_IDEL_TIME) {
			Device device = (Device) session.getAttribute(Constants.SESSION_ATTR_DEV);
			if (device != null) {
				LOG.info("Session has been idle for more than " + session.getBothIdleCount() * Constants.IDEL_TIME + "s." + " Session key: " + device.getId() + " session id: " + session.getId() +" closing.");
				device.setStatCode(SocketSessionUtil.SESSION_STAT_EXCEED_MX_IDEL_TIME);
			}
			session.close(false);
		}
		
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
