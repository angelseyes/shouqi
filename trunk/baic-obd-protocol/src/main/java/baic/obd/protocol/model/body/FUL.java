package baic.obd.protocol.model.body;

public class FUL {
	public static final String FLAG = "FUL";

	private Long fuelConsume;

	public Long getFuelConsume() {
		return fuelConsume;
	}

	public void setFuelConsume(Long fuelConsume) {
		this.fuelConsume = fuelConsume;
	}

	@Override
    public String toString() {
	    return "FUL [fuelConsume=" + fuelConsume + "]";
    }
}
