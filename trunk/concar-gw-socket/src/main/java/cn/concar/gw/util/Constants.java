package cn.concar.gw.util;



public final class Constants {

	public static final String SPRING_CONTEXT = "context.xml";


	public static final int PORT = Integer.valueOf(PropertiesLoader.getAppProperty("gw.socket.port"));
	public static final String HOST = PropertiesLoader.getAppProperty("gw.socket.host");
	public static final int READ_BUFFER = Integer.valueOf(PropertiesLoader.getAppProperty("gw.socket.readbuffer"));
	public static final int IDEL_TIME = Integer.valueOf(PropertiesLoader.getAppProperty("gw.socket.ideltime"));
	public static final boolean CLUSTER_MODE = Boolean.valueOf(PropertiesLoader.getAppProperty("gw.socket.clustermode"));
	public static final String DEVICE_TYPE = PropertiesLoader.getAppProperty("gw.socket.devicetype");
	public static final String PROTOCOL = PropertiesLoader.getAppProperty("gw.socket.protocol");
	public static final int MAX_SESSION_IDEL_TIME = Integer.valueOf(PropertiesLoader.getAppProperty("gw.socket.session.max.ideltime"));
	public static final boolean INBOUND_MSG_RECORD = Boolean.valueOf(PropertiesLoader.getAppProperty("gw.socket.inbound.msg.record"));
	public static final boolean OUTBOUND_MSG_RECORD = Boolean.valueOf(PropertiesLoader.getAppProperty("gw.socket.outbound.msg.record"));
	public static final String SESSION_ATTR_DEV = "DEVICE";

	


}
