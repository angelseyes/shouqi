package baic.obd.protocol.model.body;

public class ADC {
	public static final String FLAG = "ADC";

	private Double extVol;
	private Double intVol;

	public Double getExtVol() {
		return extVol;
	}

	public void setExtVol(Double extVol) {
		this.extVol = extVol;
	}

	public Double getIntVol() {
		return intVol;
	}

	public void setIntVol(Double intVol) {
		this.intVol = intVol;
	}

	@Override
    public String toString() {
	    return "ADC [extVol=" + extVol + ", intVol=" + intVol + "]";
    }
}
