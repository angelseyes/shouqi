package cn.concar.service.model;

import java.math.BigInteger;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class LatestRecordModel {

	private BigInteger recordId;
	private String imei;
	private Date traceTime;
	private Double longitude;
	private Double latitude;
	private Integer altitude;
	private Double hdop;
	private Double vdop;
	private BigInteger mileage;
	private Integer speed;
	private Integer rpm;
	private Integer temperature;
	private Double voltage;
	private Boolean engine;
	private String dtc;
	private Boolean location;
	private BigInteger error;

	public BigInteger getRecordId() {
		return recordId;
	}

	public void setRecordId(BigInteger recordId) {
		this.recordId = recordId;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public Date getTraceTime() {
		return traceTime;
	}

	public void setTraceTime(Date traceTime) {
		this.traceTime = traceTime;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Integer getAltitude() {
		return altitude;
	}

	public void setAltitude(Integer altitude) {
		this.altitude = altitude;
	}

	public Double getHdop() {
		return hdop;
	}

	public void setHdop(Double hdop) {
		this.hdop = hdop;
	}

	public Double getVdop() {
		return vdop;
	}

	public void setVdop(Double vdop) {
		this.vdop = vdop;
	}

	public BigInteger getMileage() {
		return mileage;
	}

	public void setMileage(BigInteger mileage) {
		this.mileage = mileage;
	}

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	public Integer getRpm() {
		return rpm;
	}

	public void setRpm(Integer rpm) {
		this.rpm = rpm;
	}

	public Integer getTemperature() {
		return temperature;
	}

	public void setTemperature(Integer temperature) {
		this.temperature = temperature;
	}

	public Double getVoltage() {
		return voltage;
	}

	public void setVoltage(Double voltage) {
		this.voltage = voltage;
	}

	public Boolean getEngine() {
		return engine;
	}

	public void setEngine(Boolean engine) {
		this.engine = engine;
	}

	public String getDtc() {
		return dtc;
	}

	public void setDtc(String dtc) {
		this.dtc = dtc;
	}

	public Boolean getLocation() {
		return location;
	}

	public void setLocation(Boolean location) {
		this.location = location;
	}

	public BigInteger getError() {
		return error;
	}

	public void setError(BigInteger error) {
		this.error = error;
	}

}
