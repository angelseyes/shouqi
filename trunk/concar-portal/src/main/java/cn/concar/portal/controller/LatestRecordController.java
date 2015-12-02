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
import cn.concar.service.model.LatestRecordModel;
import cn.concar.service.proxy.LatestRecordServiceProxy;
import cn.concar.ws.client.BaiduClient;
import cn.concar.ws.model.in.Coordinate;

@Controller
@RequestMapping("/record")
public class LatestRecordController extends BaseController {
	private static final Logger LOG = Logger.getLogger(LatestRecordController.class);

	@RequestMapping(value = "/loadLatestRecordByImei", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String loadLatestRecordByImei(String imei) {
		LOG.info("Inside LatestRecordController.loadLatestRecordByImei.");
		ResponseModel<LatestRecordModel> resp = new ResponseModel<LatestRecordModel>();
		try {
			LatestRecordModel out = new LatestRecordModel();
			if (!StringUtils.isEmpty(imei)) {
				out = LatestRecordServiceProxy.findLatestRecordByImei(imei);

				Coordinate oldCoordinate = new Coordinate();
				List<Coordinate> oldCoordinates = new ArrayList<Coordinate>();
				oldCoordinate.setLon(out.getLongitude());
				oldCoordinate.setLat(out.getLatitude());

				oldCoordinates.add(oldCoordinate);
				List<Coordinate> newCoordinates = BaiduClient.transGPStoBaidu(oldCoordinates);
				Coordinate newCoordinate = newCoordinates.get(0);
				out.setLongitude(newCoordinate.getLon());
				out.setLatitude(newCoordinate.getLat());
			}
			resp.setData(out);
			return formatResponse(resp, null, PortalConstants.SERVICE_SUCCESS);
		} catch (Exception e) {
			LOG.error("Failed in Inside LatestRecordController.loadLatestRecordByImei!", e);
			return formatFailureResponse(new StateResponseModel(), PortalConstants.SERVICE_FAILURE,
					PortalConstants.SERVICE_FAILURE_MSG + ": " + e.getMessage());
		}
	}
}
