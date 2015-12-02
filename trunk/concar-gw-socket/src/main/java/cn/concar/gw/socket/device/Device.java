package cn.concar.gw.socket.device;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

import cn.concar.gw.socket.protocol.DataProtocol;
import cn.concar.gw.socket.protocol.DataProtocolFactory;
import cn.concar.gw.socket.protocol.DeviceMessage;

public abstract class Device {
	private static final Logger LOG = Logger.getLogger(Device.class);
	protected String id;
	protected String type;
	protected DataProtocol protocol;
	protected String deviceIp;
	protected String connectedHost;
	protected IoSession session;
	protected String statCode;
	protected byte[] message;
	
	public Device() {
		try {
			protocol = DataProtocolFactory.getDataProtocol();
		} catch (Exception e) {
			LOG.error("Failed to get protocol!", e);
			throw new RuntimeException(e);
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public DataProtocol getProtocol() {
		return protocol;
	}

	public void setProtocol(DataProtocol protocol) {
		this.protocol = protocol;
	}

	public String getDeviceIp() {
		return this.session.getRemoteAddress().toString();
	}

	public IoSession getSession() {
		return session;
	}

	public void setSession(IoSession session) {
		this.session = session;
	}

	public byte[] getMessage() {
		return message;
	}

	public void setMessage(byte[] message) {
		this.message = message;
	}

	public String getConnectedHost() {
		return connectedHost;
	}

	public void setConnectedHost(String connectedHost) {
		this.connectedHost = connectedHost;
	}
	


	public String getStatCode() {
		return statCode;
	}

	public void setStatCode(String statCode) {
		this.statCode = statCode;
	}

	public abstract String getDeviceType();
	public abstract DeviceMessage handle(byte bytes[]);
	public abstract void send(String msgBody);

}
