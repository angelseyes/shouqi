package cn.concar.service.model;

import java.math.BigInteger;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class MonitorModel {
	private BigInteger vehicleId;
	private String plateNumber;
	private String departmentCode;
	private Double voltage;
	private Integer lackVoltage;
	private String dtc;
	private Integer error;
	private Date insuranceDate;
	private Integer needInsurance;
	private Date inspectionDate;
	private Integer needInspection;
	private Date traceTime;
	private Integer sleepDayNumber;
	private Integer needCheck;
	private Integer status;

	public BigInteger getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(BigInteger vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public Double getVoltage() {
		return voltage;
	}

	public void setVoltage(Double voltage) {
		this.voltage = voltage;
	}

	public Integer getLackVoltage() {
		return lackVoltage;
	}

	public void setLackVoltage(Integer lackVoltage) {
		this.lackVoltage = lackVoltage;
	}

	public String getDtc() {
		return dtc;
	}

	public void setDtc(String dtc) {
		this.dtc = dtc;
	}

	public Integer getError() {
		return error;
	}

	public void setError(Integer error) {
		this.error = error;
	}

	public Date getInsuranceDate() {
		return insuranceDate;
	}

	public void setInsuranceDate(Date insuranceDate) {
		this.insuranceDate = insuranceDate;
	}

	public Integer getNeedInsurance() {
		return needInsurance;
	}

	public void setNeedInsurance(Integer needInsurance) {
		this.needInsurance = needInsurance;
	}

	public Date getInspectionDate() {
		return inspectionDate;
	}

	public void setInspectionDate(Date inspectionDate) {
		this.inspectionDate = inspectionDate;
	}

	public Integer getNeedInspection() {
		return needInspection;
	}

	public void setNeedInspection(Integer needInspection) {
		this.needInspection = needInspection;
	}

	public Date getTraceTime() {
		return traceTime;
	}

	public void setTraceTime(Date traceTime) {
		this.traceTime = traceTime;
	}

	public Integer getSleepDayNumber() {
		return sleepDayNumber;
	}

	public void setSleepDayNumber(Integer sleepDayNumber) {
		this.sleepDayNumber = sleepDayNumber;
	}

	public Integer getNeedCheck() {
		return needCheck;
	}

	public void setNeedCheck(Integer needCheck) {
		this.needCheck = needCheck;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
