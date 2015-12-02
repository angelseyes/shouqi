package cn.concar.service.model;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class DepartmentModel {

	private BigInteger departmentId;
	private String departmentName;
	private String departmentCode;
	private String address;
	private Double longitude;
	private Double latitude;
	private BigInteger vehicleNumber;
	private BigDecimal countNumber;
	private BigDecimal abnormalNumber;
	private BigDecimal normalNumber;
	private BigDecimal normalRate;
	private BigDecimal inactiveNumber;
	private BigDecimal activeNumber;
	private BigDecimal activeRate;

	public BigInteger getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(BigInteger departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public BigInteger getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(BigInteger vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public BigDecimal getAbnormalNumber() {
		return abnormalNumber;
	}

	public void setAbnormalNumber(BigDecimal abnormalNumber) {
		this.abnormalNumber = abnormalNumber;
	}

	public BigDecimal getNormalNumber() {
		return normalNumber;
	}

	public void setNormalNumber(BigDecimal normalNumber) {
		this.normalNumber = normalNumber;
	}

	public BigDecimal getInactiveNumber() {
		return inactiveNumber;
	}

	public void setInactiveNumber(BigDecimal inactiveNumber) {
		this.inactiveNumber = inactiveNumber;
	}

	public BigDecimal getActiveNumber() {
		return activeNumber;
	}

	public void setActiveNumber(BigDecimal activeNumber) {
		this.activeNumber = activeNumber;
	}

	public BigDecimal getNormalRate() {
		return normalRate;
	}

	public void setNormalRate(BigDecimal normalRate) {
		this.normalRate = normalRate;
	}

	public BigDecimal getActiveRate() {
		return activeRate;
	}

	public void setActiveRate(BigDecimal activeRate) {
		this.activeRate = activeRate;
	}

	public BigDecimal getCountNumber() {
		return countNumber;
	}

	public void setCountNumber(BigDecimal countNumber) {
		this.countNumber = countNumber;
	}

	@Override
	public String toString() {
		return "DepartmentModel [departmentId=" + departmentId + ", departmentName=" + departmentName
				+ ", departmentCode=" + departmentCode + ", address=" + address + ", longitude=" + longitude
				+ ", latitude=" + latitude + ", vehicleNumber=" + vehicleNumber + ", abnormalNumber=" + abnormalNumber
				+ ", normalNumber=" + normalNumber + ", normalRate=" + normalRate + ", inactiveNumber=" + inactiveNumber
				+ ", activeNumber=" + activeNumber + ", activeRate=" + activeRate + "]";
	}
}
