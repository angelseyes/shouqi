package baic.obd.protocol.model.body;

public class SYS {
	public static final String FLAG = "SYS";

	private String deviceName;
	private String fwVer;
	private String hwVer;

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getFwVer() {
		return fwVer;
	}

	public void setFwVer(String fwVer) {
		this.fwVer = fwVer;
	}

	public String getHwVer() {
		return hwVer;
	}

	public void setHwVer(String hwVer) {
		this.hwVer = hwVer;
	}

	@Override
    public String toString() {
	    return "SYS [deviceName=" + deviceName + ", fwVer=" + fwVer + ", hwVer=" + hwVer + "]";
    }

}
