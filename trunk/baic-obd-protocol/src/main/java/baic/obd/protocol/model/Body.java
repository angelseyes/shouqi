package baic.obd.protocol.model;

import java.util.ArrayList;
import java.util.List;

import baic.obd.protocol.model.body.ADC;
import baic.obd.protocol.model.body.COT;
import baic.obd.protocol.model.body.DTT;
import baic.obd.protocol.model.body.ETD;
import baic.obd.protocol.model.body.FUL;
import baic.obd.protocol.model.body.GPS;
import baic.obd.protocol.model.body.GSM;
import baic.obd.protocol.model.body.OBD;
import baic.obd.protocol.model.body.SYS;



public class Body {
	private SYS sys = new SYS();
	private GPS gps = new GPS();
	private List<GSM> gsmList = new ArrayList<GSM>();
	private COT cot = new COT();
	private ADC adc = new ADC();
	private DTT dtt = new DTT();
	private ETD etd = new ETD();
	private OBD obd = new OBD();
	private FUL ful = new FUL();

	public SYS getSys() {
		return sys;
	}

	public void setSys(SYS sys) {
		this.sys = sys;
	}

	public GPS getGps() {
		return gps;
	}

	public void setGps(GPS gps) {
		this.gps = gps;
	}

	public List<GSM> getGsmList() {
		return gsmList;
	}

	public void setGsmList(List<GSM> gsmList) {
		this.gsmList = gsmList;
	}

	public COT getCot() {
		return cot;
	}

	public void setCot(COT cot) {
		this.cot = cot;
	}

	public ADC getAdc() {
		return adc;
	}

	public void setAdc(ADC adc) {
		this.adc = adc;
	}

	public DTT getDtt() {
		return dtt;
	}

	public void setDtt(DTT dtt) {
		this.dtt = dtt;
	}

	public ETD getEtd() {
		return etd;
	}

	public void setEtd(ETD etd) {
		this.etd = etd;
	}

	public OBD getObd() {
		return obd;
	}

	public void setObd(OBD obd) {
		this.obd = obd;
	}

	public FUL getFul() {
		return ful;
	}

	public void setFul(FUL ful) {
		this.ful = ful;
	}

	@Override
	public String toString() {
		return "Body [sys=" + sys.toString() + ", gps=" + gps.toString() + ", gsmList=" + gsmList + ", cot="
		        + cot.toString() + ", adc=" + adc.toString() + ", dtt=" + dtt.toString() + ", etd=" + etd.toString()
		        + ", obd=" + obd.toString() + ", ful=" + ful.toString() + "]";
	}
}
