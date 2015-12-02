package cn.concar.portal.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.common.util.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.concar.portal.model.ResponseModel;
import cn.concar.portal.model.StateResponseModel;
import cn.concar.portal.util.PortalConstants;
import cn.concar.service.model.MonitorModel;
import cn.concar.service.model.VehicleModel;
import cn.concar.service.proxy.VehicleServiceProxy;
import cn.concar.ws.client.BaiduClient;
import cn.concar.ws.model.in.Coordinate;

@Controller
@RequestMapping("/vehicle")
public class VehicleController extends BaseController {
	private static final Logger LOG = Logger.getLogger(VehicleController.class);

	@RequestMapping(value = "/loadVehicleByDepartmentId", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String loadVehicleByDepartmentId(String departmentId) {
		LOG.info("Inside VehicleController.loadVehicleByDepartmentId.");
		ResponseModel<List<VehicleModel>> resp = new ResponseModel<List<VehicleModel>>();
		try {
			List<VehicleModel> out = new ArrayList<VehicleModel>();
			if (!StringUtils.isEmpty(departmentId)) {
				out = VehicleServiceProxy.findVehicleByDepartmentId(Long.parseLong(departmentId));
			}
			resp.setData(out);
			return formatResponse(resp, null, PortalConstants.SERVICE_SUCCESS);
		} catch (Exception e) {
			LOG.error("Failed in Inside VehicleController.loadVehicleByDepartmentId.!", e);
			return formatFailureResponse(new StateResponseModel(), PortalConstants.SERVICE_FAILURE,
					PortalConstants.SERVICE_FAILURE_MSG + ": " + e.getMessage());
		}
	}

	@RequestMapping(value = "/loadSimpleVehicleByDepartmentId", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String loadSimpleVehicleByDepartmentId(String departmentId) {
		LOG.info("Inside VehicleController.loadSimpleVehicleByDepartmentId.");
		ResponseModel<List<VehicleModel>> resp = new ResponseModel<List<VehicleModel>>();
		try {
			List<VehicleModel> out = new ArrayList<VehicleModel>();
			if (!StringUtils.isEmpty(departmentId)) {
				out = VehicleServiceProxy.findSimpleVehicleByDepartmentId(Long.parseLong(departmentId));
			}
			resp.setData(out);
			return formatResponse(resp, null, PortalConstants.SERVICE_SUCCESS);
		} catch (Exception e) {
			LOG.error("Failed in Inside VehicleController.loadSimpleVehicleByDepartmentId.!", e);
			return formatFailureResponse(new StateResponseModel(), PortalConstants.SERVICE_FAILURE,
					PortalConstants.SERVICE_FAILURE_MSG + ": " + e.getMessage());
		}
	}

	@RequestMapping(value = "/loadPosition", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String loadPosition(String flag, String longitude, String latitude) {
		LOG.info("Inside VehicleController.loadPosition.");
		LOG.info("Inside VehicleController.flag:" + flag);
		LOG.info("Inside VehicleController.longitude:" + longitude);
		LOG.info("Inside VehicleController.latitude:" + latitude);
		ResponseModel<Coordinate> resp = new ResponseModel<Coordinate>();
		try {
			Coordinate oldCoordinate = new Coordinate();
			List<Coordinate> oldCoordinates = new ArrayList<Coordinate>();
			if (!StringUtils.isEmpty(longitude) && !"0".equals(longitude)) {
				oldCoordinate.setLon(Double.parseDouble(longitude));
			}
			if (!StringUtils.isEmpty(latitude) && !"0".equals(latitude)) {
				oldCoordinate.setLat(Double.parseDouble(latitude));
			}
			if ("department".equals(flag)) {
				resp.setData(oldCoordinate);
				return formatResponse(resp, null, PortalConstants.SERVICE_SUCCESS);
			}
			if (oldCoordinate.getLon() != 0 && oldCoordinate.getLat() != 0) {
				oldCoordinates.add(oldCoordinate);
			}
			List<Coordinate> newCoordinates = BaiduClient.transGPStoBaidu(oldCoordinates);
			resp.setData(newCoordinates.get(0));
			return formatResponse(resp, null, PortalConstants.SERVICE_SUCCESS);
		} catch (Exception e) {
			LOG.error("Failed in Inside VehicleController.loadPosition.!", e);
			return formatFailureResponse(new StateResponseModel(), PortalConstants.SERVICE_FAILURE,
					PortalConstants.SERVICE_FAILURE_MSG + ": " + e.getMessage());
		}
	}

	@RequestMapping(value = "/loadVehicle", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String loadVehicle() {
		LOG.info("Inside VehicleController.loadVehicle.");
		ResponseModel<List<VehicleModel>> resp = new ResponseModel<List<VehicleModel>>();
		try {
			List<VehicleModel> out = VehicleServiceProxy.findAllVehicles();
			resp.setData(out);
			return formatResponse(resp, null, PortalConstants.SERVICE_SUCCESS);
		} catch (Exception e) {
			LOG.error("Failed in Inside VehicleController.loadVehicle.!", e);
			return formatFailureResponse(new StateResponseModel(), PortalConstants.SERVICE_FAILURE,
					PortalConstants.SERVICE_FAILURE_MSG + ": " + e.getMessage());
		}
	}

	@RequestMapping(value = "/loadMonitor", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String loadMonitor() {
		LOG.info("Inside VehicleController.loadMonitor.");
		ResponseModel<List<MonitorModel>> resp = new ResponseModel<List<MonitorModel>>();
		try {
			List<MonitorModel> out = VehicleServiceProxy.findAllMonitors();
			resp.setData(out);
			return formatResponse(resp, null, PortalConstants.SERVICE_SUCCESS);
		} catch (Exception e) {
			LOG.error("Failed in Inside VehicleController.loadMonitor.!", e);
			return formatFailureResponse(new StateResponseModel(), PortalConstants.SERVICE_FAILURE,
					PortalConstants.SERVICE_FAILURE_MSG + ": " + e.getMessage());
		}
	}
}
