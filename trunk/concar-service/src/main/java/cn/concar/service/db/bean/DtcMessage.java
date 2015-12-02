package cn.concar.service.db.bean;

// Generated 2015-10-13 15:43:18 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DtcMessage generated by hbm2java
 */
@Entity
@Table(name = "dtc_message", catalog = "baic_vs")
public class DtcMessage implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4593184139283436544L;
	
	private String dtc;
	private String dtcMessage;

	public DtcMessage() {
	}

	public DtcMessage(String dtc) {
		this.dtc = dtc;
	}

	public DtcMessage(String dtc, String dtcMessage) {
		this.dtc = dtc;
		this.dtcMessage = dtcMessage;
	}

	@Id
	@Column(name = "dtc", unique = true, nullable = false, length = 10)
	public String getDtc() {
		return this.dtc;
	}

	public void setDtc(String dtc) {
		this.dtc = dtc;
	}

	@Column(name = "dtc_message", length = 100)
	public String getDtcMessage() {
		return this.dtcMessage;
	}

	public void setDtcMessage(String dtcMessage) {
		this.dtcMessage = dtcMessage;
	}

}
