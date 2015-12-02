package baic.common.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class CommonDepartment {

	private Long departmentId;
	private CommonDepartment pDepartment;
	private String departmentName;
	private String shortName;
	private String departmentCode;
	private String address;
	private Double longitude;
	private Double latitude;
	private Long vehicleNumber;
	private List<CommonDepartment> departments;

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public CommonDepartment getpDepartment() {
		return pDepartment;
	}

	public void setpDepartment(CommonDepartment pDepartment) {
		this.pDepartment = pDepartment;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
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

	public List<CommonDepartment> getDepartments() {
		return departments;
	}

	public void setDepartments(List<CommonDepartment> departments) {
		this.departments = departments;
	}

	public Long getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(Long vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
}
