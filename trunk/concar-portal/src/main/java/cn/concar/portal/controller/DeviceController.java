package cn.concar.portal.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.concar.portal.model.ResponseModel;
import cn.concar.portal.model.StateResponseModel;
import cn.concar.portal.util.PortalConstants;
import cn.concar.service.model.DeviceModel;
import cn.concar.service.proxy.DeviceServiceProxy;

@Controller
@RequestMapping("/device")
public class DeviceController extends BaseController {
	private static final Logger LOG = Logger.getLogger(DeviceController.class);

	@RequestMapping(value = "/loadDevice", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String loadDevice() {
		LOG.info("Inside DeviceController.loadDevice.");
		ResponseModel<List<DeviceModel>> resp = new ResponseModel<List<DeviceModel>>();
		try {
			List<DeviceModel> out = DeviceServiceProxy.findAllDevices();
			resp.setData(out);
			return formatResponse(resp, null, PortalConstants.SERVICE_SUCCESS);
		} catch (Exception e) {
			LOG.error("Failed in DeviceController.loadDevice!", e);
			return formatFailureResponse(new StateResponseModel(), PortalConstants.SERVICE_FAILURE,
					PortalConstants.SERVICE_FAILURE_MSG + ": " + e.getMessage());
		}
	}
}
