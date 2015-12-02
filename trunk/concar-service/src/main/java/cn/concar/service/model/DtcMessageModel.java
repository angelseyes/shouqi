package cn.concar.service.model;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class DtcMessageModel {

	private String dtc;
	private String dtcMessage;

	public String getDtc() {
		return dtc;
	}

	public void setDtc(String dtc) {
		this.dtc = dtc;
	}

	public String getDtcMessage() {
		return dtcMessage;
	}

	public void setDtcMessage(String dtcMessage) {
		this.dtcMessage = dtcMessage;
	}

}
