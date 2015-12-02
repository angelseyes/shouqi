package cn.concar.service.model.stat;

import java.math.BigDecimal;

public class VehStatusStatModel {

	private BigDecimal dtcCount;
	private BigDecimal insuranceCount;
	private BigDecimal inspectionCount;
	private BigDecimal sleepCount;
	private BigDecimal normalCount;

	public BigDecimal getDtcCount() {
		return dtcCount;
	}

	public void setDtcCount(BigDecimal dtcCount) {
		this.dtcCount = dtcCount;
	}

	public BigDecimal getInsuranceCount() {
		return insuranceCount;
	}

	public void setInsuranceCount(BigDecimal insuranceCount) {
		this.insuranceCount = insuranceCount;
	}

	public BigDecimal getInspectionCount() {
		return inspectionCount;
	}

	public void setInspectionCount(BigDecimal inspectionCount) {
		this.inspectionCount = inspectionCount;
	}

	public BigDecimal getSleepCount() {
		return sleepCount;
	}

	public void setSleepCount(BigDecimal sleepCount) {
		this.sleepCount = sleepCount;
	}

	public BigDecimal getNormalCount() {
		return normalCount;
	}

	public void setNormalCount(BigDecimal normalCount) {
		this.normalCount = normalCount;
	}

	@Override
	public String toString() {
		return "[VehStatusStatModel] dtcCount: " + dtcCount + " insuranceCount: " + insuranceCount
				+ " inspectionCount: " + inspectionCount + " sleepCount: " + sleepCount + " normalCount: "
				+ normalCount;
	}

}