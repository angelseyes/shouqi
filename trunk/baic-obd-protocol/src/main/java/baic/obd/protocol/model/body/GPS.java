package baic.obd.protocol.model.body;

public class GPS {
	public static final String FLAG = "GPS";
	public static final String SET = "A";
	public static final String NOT_SET = "V";
	
	public static final String SOUTH = "S";
	public static final String WEST = "W";

	private Boolean isSet;
	private Integer SatelliteNum;
	private Double lat;
	private Double lon;
	private Integer speed;
	private Integer bearing;
	private Integer alt;
	private Float hdop;
	private Float vdop;

	public Boolean getIsSet() {
		return isSet;
	}

	public void setIsSet(Boolean isSet) {
		this.isSet = isSet;
	}

	public Integer getSatelliteNum() {
		return SatelliteNum;
	}

	public void setSatelliteNum(Integer satelliteNum) {
		SatelliteNum = satelliteNum;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	public Integer getBearing() {
		return bearing;
	}

	public void setBearing(Integer bearing) {
		this.bearing = bearing;
	}

	public Integer getAlt() {
		return alt;
	}

	public void setAlt(Integer alt) {
		this.alt = alt;
	}

	public Float getHdop() {
		return hdop;
	}

	public void setHdop(Float hdop) {
		this.hdop = hdop;
	}

	public Float getVdop() {
		return vdop;
	}

	public void setVdop(Float vdop) {
		this.vdop = vdop;
	}

	public static String getFlag() {
		return FLAG;
	}

	@Override
    public String toString() {
	    return "GPS [isSet=" + isSet + ", SatelliteNum=" + SatelliteNum + ", lat=" + lat + ", lon=" + lon + ", speed="
	            + speed + ", bearing=" + bearing + ", alt=" + alt + ", hdop=" + hdop + ", vdop=" + vdop + "]";
    }

}
