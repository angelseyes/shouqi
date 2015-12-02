package cn.concar.service.db.dao;

import java.util.Date;

public class FilterByTime {
	private String timeFieldName;
	private Date startTime;
	private Date endTime;
	
	
	public FilterByTime(String timeFieldName, Date startTime, Date endTime) {
		super();
		this.timeFieldName = timeFieldName;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public String getTimeFieldName() {
		return timeFieldName;
	}

	public void setTimeFieldName(String timeFieldName) {
		this.timeFieldName = timeFieldName;
	}

	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
