package baic.common.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class CommonVehicle {
	private Long vehicleId;
	private String plateNumber;
	private String vin;
	private String model;
	private Double displacement;
	private String color;
	private Double mileage;
	private Date registeredDate;
	private Boolean valid;
	private Boolean sameName;
	private Integer keyNumber;
	private Boolean license;
	private Boolean certificate;
	private Boolean purchase;
	private Boolean tax;
	private Boolean clivta;
	private Date clivtaDate;
	private Boolean insurance;
	private Date insuranceDate;
	private Date inspectionDate;
	private Boolean violation;
	private Long belongDepartment;
	private String departmentName;

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Double getDisplacement() {
		return displacement;
	}

	public void setDisplacement(Double displacement) {
		this.displacement = displacement;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Double getMileage() {
		return mileage;
	}

	public void setMileage(Double mileage) {
		this.mileage = mileage;
	}

	public Date getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public Boolean getSameName() {
		return sameName;
	}

	public void setSameName(Boolean sameName) {
		this.sameName = sameName;
	}

	public Integer getKeyNumber() {
		return keyNumber;
	}

	public void setKeyNumber(Integer keyNumber) {
		this.keyNumber = keyNumber;
	}

	public Boolean getLicense() {
		return license;
	}

	public void setLicense(Boolean license) {
		this.license = license;
	}

	public Boolean getCertificate() {
		return certificate;
	}

	public void setCertificate(Boolean certificate) {
		this.certificate = certificate;
	}

	public Boolean getPurchase() {
		return purchase;
	}

	public void setPurchase(Boolean purchase) {
		this.purchase = purchase;
	}

	public Boolean getTax() {
		return tax;
	}

	public void setTax(Boolean tax) {
		this.tax = tax;
	}

	public Boolean getClivta() {
		return clivta;
	}

	public void setClivta(Boolean clivta) {
		this.clivta = clivta;
	}

	public Date getClivtaDate() {
		return clivtaDate;
	}

	public void setClivtaDate(Date clivtaDate) {
		this.clivtaDate = clivtaDate;
	}

	public Boolean getInsurance() {
		return insurance;
	}

	public void setInsurance(Boolean insurance) {
		this.insurance = insurance;
	}

	public Date getInsuranceDate() {
		return insuranceDate;
	}

	public void setInsuranceDate(Date insuranceDate) {
		this.insuranceDate = insuranceDate;
	}

	public Date getInspectionDate() {
		return inspectionDate;
	}

	public void setInspectionDate(Date inspectionDate) {
		this.inspectionDate = inspectionDate;
	}

	public Boolean getViolation() {
		return violation;
	}

	public void setViolation(Boolean violation) {
		this.violation = violation;
	}

	public Long getBelongDepartment() {
		return belongDepartment;
	}

	public void setBelongDepartment(Long belongDepartment) {
		this.belongDepartment = belongDepartment;
	}

}
