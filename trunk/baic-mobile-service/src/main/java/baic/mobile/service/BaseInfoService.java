package baic.mobile.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.type.TypeReference;

import baic.common.bean.CommonDepartment;
import baic.common.bean.CommonVehicle;
import baic.common.client.ClientFactory;
import baic.common.model.Response;
import baic.common.service.BaseService;
import baic.common.utils.ResponseConstants;
import baic.common.utils.ServiceConstants;

public class BaseInfoService extends BaseService {

	private static final Logger LOG = Logger.getLogger(BaseInfoService.class);

	@POST
	@Path("/findDepartmentsByDepId")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON })
	public String findDepartmentsByDepId(Long departmentId) {
		LOG.info("Inside BaseInfoService.findDepartmentsByDepId.");
		Response<List<CommonDepartment>> resp = new Response<List<CommonDepartment>>();
		try {
			// Get departments from base service.
			WebClient baseClient = ClientFactory
					.getClient(ServiceConstants.Base.Department.Url.FIND_DEPARTMENTS_BY_ID_URL);
			String respStr = baseClient.post(departmentId, String.class);
			Response<?> response = readResponse(respStr, new TypeReference<Response<List<CommonDepartment>>>() {
			});

			if (!response.isSuccess() || null == response.getData()) {
				return formatResponse(resp);
			}

			List<CommonDepartment> departments = (List<CommonDepartment>) response.getData();
			if (null != departments) {
				resp.setData(departments);
			}
			return formatResponse(resp);
		} catch (Exception e) {
			LOG.error("Failed in BaseInfoService.findDepartmentsByDepId!", e);
			resp.setFailureMsg(ResponseConstants.FAILURE + ": " + e.getMessage());
			return formatResponse(resp);
		}
	}
	
	@POST
	@Path("/findVehiclesByDepId")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON })
	public String findVehiclesByDepId(Long departmentId) {
		LOG.info("Inside BaseInfoService.findVehiclesByDepId.");
		Response<List<CommonVehicle>> resp = new Response<List<CommonVehicle>>();
		try {
			WebClient baseClient = ClientFactory
			.getClient(ServiceConstants.Base.Vehicle.Url.FIND_VEHICLES_BY_DEPARTMENT_ID_URL);
			String respStr = baseClient.post(departmentId, String.class);
			Response<?> response = readResponse(respStr, new TypeReference<Response<List<CommonVehicle>>>(){});
			if (!response.isSuccess() || null == response.getData()) {
				return formatResponse(resp);
			}
			List<CommonVehicle> commonVehicles = (List<CommonVehicle>) response.getData();
			resp.setData(commonVehicles);
			return formatResponse(resp);
		} catch (Exception e) {
			LOG.error("Failed in BaseInfoService.findVehiclesByDepId!", e);
			resp.setFailureMsg(ResponseConstants.FAILURE + ": " + e.getMessage());
			return formatResponse(resp);
		}
	}
}
