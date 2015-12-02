package cn.concar.service.model;

import java.math.BigInteger;

// Generated 2015-10-13 15:43:18 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class TraceModel {
	private BigInteger traceId;
	private String imei;
	private Date traceDate;
	private String traceLine;

	public BigInteger getTraceId() {
		return traceId;
	}

	public void setTraceId(BigInteger traceId) {
		this.traceId = traceId;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public Date getTraceDate() {
		return traceDate;
	}

	public void setTraceDate(Date traceDate) {
		this.traceDate = traceDate;
	}

	public String getTraceLine() {
		return traceLine;
	}

	public void setTraceLine(String traceLine) {
		this.traceLine = traceLine;
	}

}
