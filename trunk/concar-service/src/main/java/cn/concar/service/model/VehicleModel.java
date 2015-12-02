package cn.concar.service.model;

import java.math.BigInteger;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class VehicleModel {
	private BigInteger vehicleId;
	private String plateNumber;
	private String vin;
	private String model;
	private String displacement;
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
	private String imei;
	private Long belongDepartment;
	private BigInteger active;
	private Integer intActive;
	private Boolean location;
	private Boolean inFence;
	private Double longitude;
	private Double latitude;

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

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getDisplacement() {
		return displacement;
	}

	public void setDisplacement(String displacement) {
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

	public Boolean getPurchase() {
		return purchase;
	}

	public void setPurchase(Boolean purchase) {
		this.purchase = purchase;
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

	public Boolean getTax() {
		return tax;
	}

	public void setTax(Boolean tax) {
		this.tax = tax;
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

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public Long getBelongDepartment() {
		return belongDepartment;
	}

	public void setBelongDepartment(Long belongDepartment) {
		this.belongDepartment = belongDepartment;
	}

	public BigInteger getActive() {
		return active;
	}

	public void setActive(BigInteger active) {
		this.active = active;
	}

	public Boolean getLocation() {
		return location;
	}

	public void setLocation(Boolean location) {
		this.location = location;
	}

	public Boolean getInFence() {
		return inFence;
	}

	public void setInFence(Boolean inFence) {
		this.inFence = inFence;
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

	public Boolean getCertificate() {
		return certificate;
	}

	public void setCertificate(Boolean certificate) {
		this.certificate = certificate;
	}

	public Integer getIntActive() {
		return intActive;
	}

	public void setIntActive(Integer intActive) {
		this.intActive = intActive;
	}
}
