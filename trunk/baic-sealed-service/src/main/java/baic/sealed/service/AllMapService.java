package baic.sealed.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.core.type.TypeReference;

import baic.common.bean.CommonDepartment;
import baic.common.bean.CommonVehicle;
import baic.common.client.ClientFactory;
import baic.common.model.Response;
import baic.common.service.BaseService;
import baic.common.utils.ResponseConstants;
import baic.common.utils.ServiceConstants;
import baic.obd.model.LatestRecordModel;
import baic.sealed.service.model.AllMapDepartment;
import baic.sealed.service.model.AllMapTrack;
import baic.sealed.service.model.AllMapVehicle;

public class AllMapService extends BaseService {

	private static final Logger LOG = Logger.getLogger(AllMapService.class);

	@SuppressWarnings("unchecked")
	@GET
	@Path("/findDepartments")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON })
	public String findDepartments(@QueryParam("deptId") String departmentId, @QueryParam("callback") String callback) {
		LOG.info("Inside AllMapService.findDepartments.");
		LOG.info("departmentId: " + departmentId);
		LOG.info("callback: " + callback);
		Response<List<AllMapDepartment>> resp = new Response<List<AllMapDepartment>>();
		try {
			WebClient baseClient = ClientFactory
					.getClient(ServiceConstants.Base.Department.Url.FIND_DEPARTMENTS_BY_ID_URL);
			String respStr = baseClient.post(Long.parseLong(departmentId), String.class);
			Response<?> response = readResponse(respStr, new TypeReference<Response<List<CommonDepartment>>>() {
			});

			if (!response.isSuccess() || null == response.getData()) {
				return formatCallBackResponse(resp, callback);
			}

			List<CommonDepartment> commonDepartments = (List<CommonDepartment>) response.getData();
			if (null != commonDepartments && !commonDepartments.isEmpty()) {
				List<AllMapDepartment> allMapDepartments = new ArrayList<AllMapDepartment>();
				for (CommonDepartment commonDepartment : commonDepartments) {
					allMapDepartments.add(transType(commonDepartment));
				}
				resp.setData(allMapDepartments);
			}
			return formatCallBackResponse(resp, callback);
		} catch (Exception e) {
			LOG.error("Failed in AllMapService.findDepartments!", e);
			resp.setFailureMsg(ResponseConstants.FAILURE + ": " + e.getMessage());
			return formatCallBackResponse(resp, callback);
		}
	}

	@SuppressWarnings("unchecked")
	@POST
	@Path("/findVehicles")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON })
	public String findVehicles(Long departmentId) {
		LOG.info("Inside AllMapService.findVehicles.");
		Response<List<AllMapVehicle>> resp = new Response<List<AllMapVehicle>>();
		try {
			WebClient baseClient = ClientFactory
					.getClient(ServiceConstants.Base.Vehicle.Url.FIND_VEHICLES_BY_DEPARTMENT_ID_URL);
			String respStr = baseClient.post(departmentId, String.class);
			Response<?> response = readResponse(respStr, new TypeReference<Response<List<CommonVehicle>>>() {
			});
			if (!response.isSuccess() || null == response.getData()) {
				return formatResponse(resp);
			}
			List<CommonVehicle> commonVehicles = (List<CommonVehicle>) response.getData();
			if (null != commonVehicles && !commonVehicles.isEmpty()) {
				List<AllMapVehicle> allMapVehicles = new ArrayList<AllMapVehicle>();
				for (CommonVehicle commonVehicle : commonVehicles) {
					allMapVehicles.add(transType(commonVehicle));
				}
				resp.setData(allMapVehicles);
			}
			return formatResponse(resp);
		} catch (Exception e) {
			LOG.error("Failed in AllMapService.findVehicles!", e);
			resp.setFailureMsg(ResponseConstants.FAILURE + ": " + e.getMessage());
			return formatResponse(resp);
		}
	}

	@POST
	@Path("/findTrackByImei")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON })
	public String findTrackByImei(String imei) {
		LOG.info("Inside AllMapService.findTrack.");
		Response<AllMapTrack> resp = new Response<AllMapTrack>();
		try {
			// Get latest record from obd service.
			WebClient obdClient = ClientFactory
					.getClient(ServiceConstants.Obd.LatestRecord.Url.FIND_LATEST_RECORD_BY_IMEI_URL);
			String respStr = obdClient.post(imei, String.class);
			Response<?> response = readResponse(respStr, new TypeReference<Response<LatestRecordModel>>() {
			});

			if (!response.isSuccess() || null == response.getData()) {
				return formatResponse(resp);
			}

			LatestRecordModel latestRecordModel = (LatestRecordModel) response.getData();
			if (null != latestRecordModel) {
				resp.setData(transType(latestRecordModel));
			}
			return formatResponse(resp);
		} catch (Exception e) {
			LOG.error("Failed in AllMapService.findTrack!", e);
			resp.setFailureMsg(ResponseConstants.FAILURE + ": " + e.getMessage());
			return formatResponse(resp);
		}
	}

	@POST
	@Path("/findTrackByVehicleId")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON })
	public String findTrackByVehicleId(Long vehicleId) {
		LOG.info("Inside AllMapService.findTrack.");
		Response<AllMapTrack> resp = new Response<AllMapTrack>();
		try {
			// Get IMEI from device service.
			WebClient deviceClient = ClientFactory
					.getClient(ServiceConstants.Device.DeviceVehicle.Url.FIND_IMEI_BY_VEHICLE_ID_URL);
			String respStr = deviceClient.post(vehicleId, String.class);
			Response<?> response = readResponse(respStr, new TypeReference<Response<String>>() {
			});

			if (!response.isSuccess() || null == response.getData()) {
				return formatResponse(resp);
			}

			String imei = (String) response.getData();

			// Get latest record from obd service.
			WebClient obdClient = ClientFactory
					.getClient(ServiceConstants.Obd.LatestRecord.Url.FIND_LATEST_RECORD_BY_IMEI_URL);
			respStr = obdClient.post(imei, String.class);
			response = readResponse(respStr, new TypeReference<Response<LatestRecordModel>>() {
			});

			if (!response.isSuccess() || null == response.getData()) {
				return formatResponse(resp);
			}

			LatestRecordModel latestRecordModel = (LatestRecordModel) response.getData();
			if (null != latestRecordModel) {
				resp.setData(transType(latestRecordModel));
			}
			return formatResponse(resp);
		} catch (Exception e) {
			LOG.error("Failed in AllMapService.findTrack!", e);
			resp.setFailureMsg(ResponseConstants.FAILURE + ": " + e.getMessage());
			return formatResponse(resp);
		}
	}

	private AllMapDepartment transType(CommonDepartment commonDepartment) {
		AllMapDepartment allMapDepartment = new AllMapDepartment();
		BeanUtils.copyProperties(commonDepartment, allMapDepartment);
		return allMapDepartment;
	}

	private AllMapVehicle transType(CommonVehicle commonVehicle) {
		AllMapVehicle allMapVehicle = new AllMapVehicle();
		BeanUtils.copyProperties(commonVehicle, allMapVehicle);
		return allMapVehicle;
	}

	private AllMapTrack transType(LatestRecordModel latestRecord) {
		AllMapTrack allMapTrack = new AllMapTrack();
		BeanUtils.copyProperties(latestRecord, allMapTrack);
		return allMapTrack;
	}
}
