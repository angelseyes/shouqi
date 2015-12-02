package cn.concar.gw.socket.baic.protocol;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.codehaus.jackson.map.ObjectMapper;

import cn.concar.device.baic.protocol.model.ModelPackage;
import cn.concar.gw.socket.protocol.DataProtocol;
import cn.concar.gw.socket.protocol.DeviceMessage;

public class BaicDeviceProtocol extends DataProtocol {
	
	private static final Logger LOG = Logger.getLogger(BaicDeviceProtocol.class);

	@Override
	public byte[] getRemainingMsg(IoBuffer dataStream) {
		byte[] buffer = new byte[dataStream.remaining()];
		dataStream.get(buffer);
		return buffer;
	}

	@Override
	public byte[] getFirstMsg(byte[] dataStream) {
		return dataStream;
	}

	@Override
	public DeviceMessage decode(byte[] bytes) {
		LOG.debug("Inside decode.");
		String messageStr = new String(bytes);
		ModelPackage mp = Parser.parse(messageStr);
		DeviceMessage baicMessage = new BaicDeviceMessage();
		baicMessage.setDeviceId(mp.getImei());
		baicMessage.setPartial(false);
		ObjectMapper mapper = new ObjectMapper();
		try {
			baicMessage.setMessage(mapper.writeValueAsString(mp));
		} catch (Exception e) {
			LOG.error("Failed to get device message!", e);
			throw new RuntimeException(e);
		}
		return baicMessage;
	}
	
	public byte[] encode(String message) {
		return null;
	}

}
