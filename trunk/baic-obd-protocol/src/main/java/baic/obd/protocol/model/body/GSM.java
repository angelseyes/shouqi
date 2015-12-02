package baic.obd.protocol.model.body;

public class GSM {
	public static final String FLAG = "GSM";

	private Integer reg;
	private Integer csq;
	private String mcc;
	private String mnc;
	private String lac;
	private String cid;
	private Integer rssi;

	public Integer getReg() {
		return reg;
	}

	public void setReg(Integer reg) {
		this.reg = reg;
	}

	public Integer getCsq() {
		return csq;
	}

	public void setCsq(Integer csq) {
		this.csq = csq;
	}

	public String getMcc() {
		return mcc;
	}

	public void setMcc(String mcc) {
		this.mcc = mcc;
	}

	public String getMnc() {
		return mnc;
	}

	public void setMnc(String mnc) {
		this.mnc = mnc;
	}

	public String getLac() {
		return lac;
	}

	public void setLac(String lac) {
		this.lac = lac;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public Integer getRssi() {
		return rssi;
	}

	public void setRssi(Integer rssi) {
		this.rssi = rssi;
	}

	@Override
    public String toString() {
	    return "GSM [reg=" + reg + ", csq=" + csq + ", mcc=" + mcc + ", mnc=" + mnc + ", lac=" + lac + ", cid=" + cid
	            + ", rssi=" + rssi + "]";
    }

}
