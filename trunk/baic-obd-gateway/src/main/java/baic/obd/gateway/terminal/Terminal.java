package baic.obd.gateway.terminal;

import org.apache.mina.core.session.IoSession;

public class Terminal {
	
	private String terminalNo;
	
	private String messageStr ="";

	private IoSession session;

	public void setTerminalNo(String terminalNo) {
		this.terminalNo = terminalNo;
	}

	public String getTerminalNo() {
		return this.terminalNo;
	}

	public void setSession(IoSession session) {
		this.session = session;
	}

	public String getIP() {
		return this.session.getRemoteAddress().toString();
	}

	public String getMessageStr() {
		String completeMessageStr = null;
		if (messageStr.endsWith("#")) {
			completeMessageStr = new String(messageStr);
			messageStr = "";
		}
		return completeMessageStr;
	}

	public void setMessageStr(String messageStr) {
		this.messageStr += messageStr;
	}
}