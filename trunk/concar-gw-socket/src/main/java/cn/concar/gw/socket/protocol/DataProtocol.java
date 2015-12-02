package cn.concar.gw.socket.protocol;

import org.apache.mina.core.buffer.IoBuffer;

public abstract class DataProtocol {
	
	public abstract byte[] getRemainingMsg(IoBuffer dataStream);
	public abstract byte[] getFirstMsg(byte[] dataStream);
	public abstract DeviceMessage decode(byte bytes[]);
	public abstract byte[] encode(String message);

}
