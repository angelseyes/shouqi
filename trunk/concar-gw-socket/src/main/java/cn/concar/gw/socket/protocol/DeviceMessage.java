package cn.concar.gw.socket.protocol;

public abstract class DeviceMessage {

	protected String deviceId;
	protected String message;
	protected boolean isPartial;
	
	public DeviceMessage() {
		isPartial = false;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isPartial() {
		return isPartial;
	}

	public void setPartial(boolean isPartial) {
		this.isPartial = isPartial;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	
}
