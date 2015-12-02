package cn.concar.ws.model.in;

public class Coordinate {
	private double lat;
	private double lon;

	public Coordinate() {
	};

	public Coordinate(double lon, double lat) {
		this.lon = lon;
		this.lat = lat;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	@Override
	public String toString() {
		return "Coordinate [lat=" + lat + ", lon=" + lon + "]";
	}

}
