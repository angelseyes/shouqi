package baic.obd.protocol.model.body;

public class COT {

	public static final String FLAG = "COT";

	private Long mileage;
	private Long engineRunTime;

	public Long getMileage() {
		return mileage;
	}

	public void setMileage(Long mileage) {
		this.mileage = mileage;
	}

	public Long getEngineRunTime() {
		return engineRunTime;
	}

	public void setEngineRunTime(Long engineRunTime) {
		this.engineRunTime = engineRunTime;
	}

	@Override
    public String toString() {
	    return "COT [mileage=" + mileage + ", engineRunTime=" + engineRunTime + "]";
    }

}
