package cn.concar.gw.socket.baic.device;

import org.apache.log4j.Logger;

import cn.concar.gw.socket.device.Device;
import cn.concar.gw.socket.protocol.DeviceMessage;

public class BaicDevice extends Device {
	private static final Logger LOG = Logger.getLogger(BaicDevice.class);

	@Override
	public String getDeviceType() {
		return "OBD";
	}

	@Override
	public DeviceMessage handle(byte[] bytes) {
		LOG.debug("Inside handle.");
		DeviceMessage msg = super.getProtocol().decode(bytes);
		this.setId(msg.getDeviceId());
		return msg;
	}

	@Override
	public void send(String msgBody) {
		LOG.debug("Sending message: " + msgBody);
		byte[] data = super.getProtocol().encode(msgBody);
		super.getSession().write(data);
	}

}
