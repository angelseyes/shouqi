package cn.concar.portal.controller;

import java.util.ArrayList;
import java.util.Arrays;
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
import cn.concar.portal.util.RamerDouglasPeuckerFilter;
import cn.concar.service.model.TraceModel;
import cn.concar.service.proxy.TraceServiceProxy;
import cn.concar.ws.client.BaiduClient;
import cn.concar.ws.model.in.Coordinate;

@Controller
@RequestMapping("/record")
public class TraceController extends BaseController {
	private static final Logger LOG = Logger.getLogger(TraceController.class);

	@RequestMapping(value = "/loadTraceByImei", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String loadTraceByImei(String imei) {
		LOG.info("Inside TraceController.loadTraceByImei.");
		ResponseModel<List<TraceModel>> resp = new ResponseModel<List<TraceModel>>();
		try {
			List<TraceModel> out = new ArrayList<TraceModel>();
			if (!StringUtils.isEmpty(imei)) {
				out = TraceServiceProxy.findTraceByImei(imei);
			}
			resp.setData(out);
			return formatResponse(resp, null, PortalConstants.SERVICE_SUCCESS);
		} catch (Exception e) {
			LOG.error("Failed in Inside TraceController.loadTraceByImei!", e);
			return formatFailureResponse(new StateResponseModel(), PortalConstants.SERVICE_FAILURE,
					PortalConstants.SERVICE_FAILURE_MSG + ": " + e.getMessage());
		}
	}

	@RequestMapping(value = "/loadTraceLineByTraceId", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String loadTraceLineByTraceId(String traceId) {
		LOG.info("Inside TraceController.loadTraceLineByTraceId.");
		ResponseModel<List<Coordinate>> resp = new ResponseModel<List<Coordinate>>();
		try {
			List<Coordinate> out = new ArrayList<Coordinate>();
			if (!StringUtils.isEmpty(traceId)) {
				String traceLine = TraceServiceProxy.findTraceLineByTraceId(Long.parseLong(traceId));
				if (traceLine != null) {
					String[] coordinateStrs = traceLine.split(";");
					if (coordinateStrs.length > 0) {
						List<Coordinate> oldCoords = new ArrayList<Coordinate>();
						for (String coordinateStr : coordinateStrs) {
							String[] points = coordinateStr.split(",");
							Coordinate coordinate = new Coordinate();
							if (!StringUtils.isEmpty(points[0]) && !"0".equals(points[0])) {
								coordinate.setLon(Double.parseDouble(points[0]));
							}
							if (!StringUtils.isEmpty(points[1]) && !"0".equals(points[1])) {
								coordinate.setLat(Double.parseDouble(points[1]));
							}
							if (coordinate.getLon() != 0 && coordinate.getLat() != 0) {
								oldCoords.add(coordinate);
							}
						}
						Coordinate[] finalCoords = RamerDouglasPeuckerFilter
								.simplify(oldCoords.toArray(new Coordinate[] {}), 80);
						out = BaiduClient.transGPStoBaidu(Arrays.asList(finalCoords));
					}
				}
			}

			resp.setData(out);
			return formatResponse(resp, null, PortalConstants.SERVICE_SUCCESS);
		} catch (Exception e) {
			LOG.error("Failed in Inside TraceController.loadTraceLineByTraceId!", e);
			return formatFailureResponse(new StateResponseModel(), PortalConstants.SERVICE_FAILURE,
					PortalConstants.SERVICE_FAILURE_MSG + ": " + e.getMessage());
		}
	}
}
