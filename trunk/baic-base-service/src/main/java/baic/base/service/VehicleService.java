package baic.base.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import baic.base.service.idao.IVehicleDao;
import baic.common.bean.CommonVehicle;
import baic.common.model.Response;
import baic.common.service.BaseService;
import baic.common.utils.ResponseConstants;
import baic.common.utils.ServiceConstants;

public class VehicleService extends BaseService {

	private static final Logger LOG = Logger.getLogger(DepartmentService.class);

	@Autowired
	private IVehicleDao vehicleDao;

	@GET
	@Path(ServiceConstants.Base.Vehicle.FIND_ALL_VEHICLES)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON })
	public String findAllVehicles() {
		LOG.info("Inside VehicleService.findAllVehicles.");
		Response<List<CommonVehicle>> resp = new Response<List<CommonVehicle>>();
		try {
			List<CommonVehicle> vehicles = vehicleDao.findAllVehicles();
			resp.setData(vehicles);
			return formatResponse(resp);
		} catch (Exception e) {
			LOG.error("Failed in VehicleService.findAllVehicles!", e);
			resp.setFailureMsg(ResponseConstants.FAILURE + ": " + e.getMessage());
			return formatResponse(resp);
		}
	}

	@POST
	@Path(ServiceConstants.Base.Vehicle.FIND_VEHICLES_BY_DEPARTMENT_ID)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON })
	public String findVehiclesByDepartmentId(Long departmentId) {
		LOG.info("Inside VehicleService.findVehiclesByDepartment.");
		Response<List<CommonVehicle>> resp = new Response<List<CommonVehicle>>();
		try {
			List<CommonVehicle> vehicles = vehicleDao.findVehiclesByDepartmentId(departmentId);
			resp.setData(vehicles);
			return formatResponse(resp);
		} catch (Exception e) {
			LOG.error("Failed in VehicleService.findAllVehicles!", e);
			resp.setFailureMsg(ResponseConstants.FAILURE + ": " + e.getMessage());
			return formatResponse(resp);
		}
	}

	@POST
	@Path(ServiceConstants.Base.Vehicle.FIND_SUB_VEHICLES_BY_DEPARTMENT_ID)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON })
	public String findSubVehiclesByDepartmentId(Long departmentId) {
		LOG.info("Inside VehicleService.findSubVehiclesByDepartmentId.");
		Response<List<CommonVehicle>> resp = new Response<List<CommonVehicle>>();
		try {
			List<CommonVehicle> vehicles = vehicleDao.findSubVehiclesByDepartmentId(departmentId);
			resp.setData(vehicles);
			return formatResponse(resp);
		} catch (Exception e) {
			LOG.error("Failed in VehicleService.findSubVehiclesByDepartmentId!", e);
			resp.setFailureMsg(ResponseConstants.FAILURE + ": " + e.getMessage());
			return formatResponse(resp);
		}
	}

	@POST
	@Path(ServiceConstants.Base.Vehicle.FIND_VEHICLES_BY_ADMIN_ID)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON })
	public String findVehiclesByAdminId(Long adminId) {
		LOG.info("Inside VehicleService.findVehiclesByAdminId.");
		Response<List<CommonVehicle>> resp = new Response<List<CommonVehicle>>();
		try {
			List<CommonVehicle> vehicles = vehicleDao.findVehiclesByAdminId(adminId);
			resp.setData(vehicles);
			return formatResponse(resp);
		} catch (Exception e) {
			LOG.error("Failed in VehicleService.findVehiclesByAdminId!", e);
			resp.setFailureMsg(ResponseConstants.FAILURE + ": " + e.getMessage());
			return formatResponse(resp);
		}
	}
}
