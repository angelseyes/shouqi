package baic.device.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import baic.common.model.Response;
import baic.common.service.BaseService;
import baic.common.utils.ResponseConstants;
import baic.common.utils.ServiceConstants;
import baic.device.service.idao.IDeviceVehicleDao;

public class DeviceVehicleService extends BaseService {

	private static final Logger LOG = Logger.getLogger(DeviceVehicleService.class);

	@Autowired
	private IDeviceVehicleDao deviceVehicleDao;

	@POST
	@Path(ServiceConstants.Device.DeviceVehicle.FIND_VEHICLE_ID_BY_IMEI)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON })
	public String findVehicleIdByImei(String imei) {
		LOG.info("Inside DeviceVehicleService.findVehicleIdByImei.");
		Response<Long> resp = new Response<Long>();
		try {
			Long vehicleId = deviceVehicleDao.findVehicleIdByImei(imei);
			resp.setData(vehicleId);
			return formatResponse(resp);
		} catch (Exception e) {
			LOG.error("Failed in DeviceVehicleService.findVehicleIdByImei!", e);
			resp.setFailureMsg(ResponseConstants.FAILURE + ": " + e.getMessage());
			return formatResponse(resp);
		}
	}

	@POST
	@Path(ServiceConstants.Device.DeviceVehicle.FIND_IMEI_BY_VEHICLE_ID)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON })
	public String findImeiByVehicleId(Long vehicleId) {
		LOG.info("Inside DeviceVehicleService.findImeiByVehicleId.");
		Response<String> resp = new Response<String>();
		try {
			String imei = deviceVehicleDao.findImeiByVehicleId(vehicleId);
			resp.setData(imei);
			return formatResponse(resp);
		} catch (Exception e) {
			LOG.error("Failed in DeviceVehicleService.findImeiByVehicleId!", e);
			resp.setFailureMsg(ResponseConstants.FAILURE + ": " + e.getMessage());
			return formatResponse(resp);
		}
	}
}
