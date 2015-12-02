package baic.sealed.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.type.TypeReference;

import baic.common.bean.CommonMap;
import baic.common.bean.CommonVehicle;
import baic.common.client.ClientFactory;
import baic.common.model.Response;
import baic.common.service.BaseService;
import baic.common.utils.ResponseConstants;
import baic.common.utils.ServiceConstants;
import baic.common.utils.TimeUtils;
import baic.sealed.service.model.ReportChart;

public class ReportService extends BaseService {

	private static final Logger LOG = Logger.getLogger(ReportService.class);

	@SuppressWarnings("unchecked")
	@GET
	@Path("/findReports")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON })
	public String findReports(@QueryParam("deptId") String departmentId, @QueryParam("callback") String callback) {
		LOG.info("Inside ReportService.findReports.");
		LOG.info("departmentId: " + departmentId);
		LOG.info("callback: " + callback);
		Response<ReportChart> resp = new Response<ReportChart>();
		try {
			WebClient baseClient = ClientFactory
					.getClient(ServiceConstants.Base.Vehicle.Url.FIND_SUB_VEHICLES_BY_DEPARTMENT_ID_URL);
			String respStr = baseClient.post(Long.parseLong(departmentId), String.class);
			Response<?> response = readResponse(respStr, new TypeReference<Response<List<CommonVehicle>>>() {
			});
			if (!response.isSuccess() || null == response.getData()) {
				return formatCallBackResponse(resp, callback);
			}
			List<CommonVehicle> commonVehicles = (List<CommonVehicle>) response.getData();
			if (null != commonVehicles && !commonVehicles.isEmpty()) {
				resp.setData(countEngine(commonVehicles));
			}
			return formatCallBackResponse(resp, callback);
		} catch (Exception e) {
			LOG.error("Failed in ReportService.findReports!", e);
			resp.setFailureMsg(ResponseConstants.FAILURE + ": " + e.getMessage());
			return formatCallBackResponse(resp, callback);
		}
	}

	private ReportChart countEngine(List<CommonVehicle> vehicles) {
		int age0to2 = 0, age2to5 = 0, age5to10 = 0, age10 = 0;
		int mileage0to2 = 0, mileage2to5 = 0, mileage5to10 = 0, mileage10 = 0;
		int clivta = 0, inspection = 0, insurance = 0, normal = 0;
		HashMap<String, Integer> departmentVehicle = new HashMap<String, Integer>();
		for (CommonVehicle vehicle : vehicles) {

			// Count age.
			long age = TimeUtils.timeBetween(vehicle.getRegisteredDate(), new Date(), Calendar.YEAR);
			if (age >= 0 && age < 2) {
				age0to2++;
			} else if (age >= 2 && age < 5) {
				age2to5++;
			} else if (age >= 5 && age < 10) {
				age5to10++;
			} else if (age >= 10) {
				age10++;
			}

			// Count mileage.
			int mileage = (int) (vehicle.getMileage() / 10000);
			if (mileage >= 0 && mileage < 2) {
				mileage0to2++;
			} else if (mileage >= 2 && mileage < 5) {
				mileage2to5++;
			} else if (mileage >= 5 && mileage < 10) {
				mileage5to10++;
			} else if (mileage >= 10) {
				mileage10++;
			}

			// Count date.
			long clivtaMonth = TimeUtils.timeBetween(vehicle.getClivtaDate(), new Date(), Calendar.MONTH);
			long inspectionMonth = TimeUtils.timeBetween(vehicle.getInspectionDate(), new Date(), Calendar.MONTH);
			long insuranceMonth = TimeUtils.timeBetween(vehicle.getInsuranceDate(), new Date(), Calendar.MONTH);
			boolean isNormal = true;

			if (clivtaMonth <= 1) {
				clivta++;
				isNormal = false;
			}

			if (inspectionMonth <= 1) {
				inspection++;
				isNormal = false;
			}

			if (insuranceMonth <= 1) {
				insurance++;
				isNormal = false;
			}

			if (isNormal) {
				normal++;
			}

			// Count department.
			Integer count = departmentVehicle.get(vehicle.getDepartmentName());
			if (null == count) {
				departmentVehicle.put(vehicle.getDepartmentName(), 1);
			} else {
				departmentVehicle.put(vehicle.getDepartmentName(), count++);
			}
		}
		List<CommonMap> ageList = new ArrayList<CommonMap>();
		ageList.add(new CommonMap("0~2年", age0to2));
		ageList.add(new CommonMap("2~5年", age2to5));
		ageList.add(new CommonMap("5~10年", age5to10));
		ageList.add(new CommonMap("10年以上", age10));

		List<CommonMap> mileageList = new ArrayList<CommonMap>();
		mileageList.add(new CommonMap("0~2万公里", mileage0to2));
		mileageList.add(new CommonMap("2~5万公里", mileage2to5));
		mileageList.add(new CommonMap("5~10万公里", mileage5to10));
		mileageList.add(new CommonMap("10万公里以上", mileage10));

		List<CommonMap> departmentList = new ArrayList<CommonMap>();
		for (String departmentName : departmentVehicle.keySet()) {
			departmentList.add(new CommonMap(departmentName, departmentVehicle.get(departmentName)));
		}

		List<CommonMap> dateList = new ArrayList<CommonMap>();
		dateList.add(new CommonMap("待续交强险", clivta));
		dateList.add(new CommonMap("待续商业险", insurance));
		dateList.add(new CommonMap("待保养", inspection));
		dateList.add(new CommonMap("正常", normal));

		ReportChart reportChart = new ReportChart();
		reportChart.setAgeChart(ageList);
		reportChart.setMileageChart(mileageList);
		reportChart.setDepartmentChart(departmentList);
		reportChart.setDateChart(dateList);

		return reportChart;
	}
}
