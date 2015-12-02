package baic.obd.protocol.model.body;

public class DTT {
	public static final String FLAG = "DTT";

	private String engineStatus;

	public String getEngineStatus() {
		return engineStatus;
	}

	public void setEngineStatus(String engineStatus) {
		this.engineStatus = engineStatus;
	}

	@Override
	public String toString() {
		return "DTT [engineStatus=" + engineStatus + "]";
	}

}
