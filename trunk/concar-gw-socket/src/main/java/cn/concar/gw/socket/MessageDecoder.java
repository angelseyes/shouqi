package cn.concar.gw.socket;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import cn.concar.gw.socket.device.Device;
import cn.concar.gw.util.Constants;

public class MessageDecoder extends CumulativeProtocolDecoder {
	
	private static final Logger LOG = Logger.getLogger(MessageDecoder.class);
	
	public MessageDecoder() {
		LOG.debug("New MessageDecoder.");
	}

	@Override
	protected boolean doDecode(IoSession session, IoBuffer buffer, ProtocolDecoderOutput out) throws Exception {
		LOG.debug("Inside doDecode.");
		int dataLength = buffer.remaining();
		try {
			if (dataLength > 0) {
				Device device = (Device) session.getAttribute(Constants.SESSION_ATTR_DEV);
				if (device == null) {
					byte data[] = new byte[dataLength];
					buffer.get(data, 0, dataLength);
					out.write(data);
				} else {
					while (buffer.hasRemaining()) {
						byte data[] = device.getProtocol().getRemainingMsg(buffer);
						if (data != null)
							out.write(data);
					}
				}
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			LOG.error("Decode failure!", e);
			throw e;
		}
	}





}
