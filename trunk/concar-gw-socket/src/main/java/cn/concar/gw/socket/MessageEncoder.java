package cn.concar.gw.socket;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class MessageEncoder implements ProtocolEncoder {

	private static final Logger LOG = Logger.getLogger(MessageEncoder.class);

	public MessageEncoder() {
		LOG.debug("New message encoder.");
	}

	@Override
	public void dispose(IoSession session) throws Exception {
		LOG.debug("Inside dispose.");

	}

	@Override
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
		LOG.debug("Inside encode.");
		byte value[] = (byte[]) message;
		IoBuffer buf = IoBuffer.allocate(value.length);
		buf.setAutoExpand(true);
		if (value != null) {
			buf.put(value);
		}
		buf.flip();
		out.write(buf);
		out.flush();
	}

}
