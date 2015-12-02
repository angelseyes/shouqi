package cn.concar.gw.socket;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class CodecFactory implements ProtocolCodecFactory {
	
	private static final Logger LOG = Logger.getLogger(CodecFactory.class);
	
	private ProtocolDecoder messageDecoder;
	private ProtocolEncoder messageEncoder;

	public CodecFactory() {
		messageDecoder = new MessageDecoder();
		messageEncoder = new MessageEncoder();
	}

	@Override
	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		LOG.debug("Inside getDecoder.");
		return messageDecoder;
	}

	@Override
	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		LOG.debug("Inside getEncoder");
		return messageEncoder;
	}

}
