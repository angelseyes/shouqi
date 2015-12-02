package baic.sealed.service.model;

import java.util.List;

import baic.common.bean.CommonMap;

public class ReportChart {

	private List<CommonMap> ageChart;
	private List<CommonMap> mileageChart;
	private List<CommonMap> departmentChart;
	private List<CommonMap> dateChart;

	public List<CommonMap> getAgeChart() {
		return ageChart;
	}

	public void setAgeChart(List<CommonMap> ageChart) {
		this.ageChart = ageChart;
	}

	public List<CommonMap> getMileageChart() {
		return mileageChart;
	}

	public void setMileageChart(List<CommonMap> mileageChart) {
		this.mileageChart = mileageChart;
	}

	public List<CommonMap> getDepartmentChart() {
		return departmentChart;
	}

	public void setDepartmentChart(List<CommonMap> departmentChart) {
		this.departmentChart = departmentChart;
	}

	public List<CommonMap> getDateChart() {
		return dateChart;
	}

	public void setDateChart(List<CommonMap> dateChart) {
		this.dateChart = dateChart;
	}

}
