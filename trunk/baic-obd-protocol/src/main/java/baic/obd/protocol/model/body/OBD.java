package baic.obd.protocol.model.body;

import java.util.Arrays;

public class OBD {
	public static final String FLAG = "OBD";

	private Integer rpm;
	// km/h
	private Integer speed;
	// Engine coolant Temperature
	private Integer temperature;
	// %
	private Double fuelLevel;
	private String[] dtcArray;

	public Integer getRpm() {
		return rpm;
	}

	public void setRpm(Integer rpm) {
		this.rpm = rpm;
	}

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	public Integer getTemperature() {
		return temperature;
	}

	public void setTemperature(Integer temperature) {
		this.temperature = temperature;
	}
	
	public Double getFuelLevel() {
		return fuelLevel;
	}

	public void setFuelLevel(Double fuelLevel) {
		this.fuelLevel = fuelLevel;
	}

	public String[] getDtcArray() {
		return dtcArray;
	}

	public void setDtcArray(String[] dtcArray) {
		this.dtcArray = dtcArray;
	}

	@Override
    public String toString() {
	    return "OBD [rpm=" + rpm + ", speed=" + speed + ", temperature=" + temperature + ", fuelLevel=" + fuelLevel
	            + ", dtcArray=" + Arrays.toString(dtcArray) + "]";
    }
}
