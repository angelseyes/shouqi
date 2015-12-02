package baic.obd.protocol.model;

import java.util.Date;

public class Head {
	public static final String VER = "*GS16";

	private String imei;
	private Date time;
	private Integer eventId;
	private String eventFlag;

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public String getEventFlag() {
		return eventFlag;
	}

	public void setEventFlag(String eventFlag) {
		this.eventFlag = eventFlag;
	}

	@Override
	public String toString() {
		return "Head [imei=" + imei + ", time=" + time + ", eventId=" + eventId + ", eventFlag=" + eventFlag + "]";
	}

}
