package baic.obd.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
import baic.obd.model.TraceModel;
import baic.obd.service.idao.ITraceDao;

public class TraceService extends BaseService {

	private static final Logger LOG = Logger.getLogger(TraceService.class);

	@Autowired
	private ITraceDao traceDao;

	@GET
	@Path(ServiceConstants.Obd.Trace.Url.FIND_TRACES_URL)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON })
	public String findTraces() {
		LOG.info("Inside TraceService.findTraces.");
		Response<List<TraceModel>> resp = new Response<List<TraceModel>>();
		try {
			List<TraceModel> traces = traceDao.findAllTraces();
			resp.setData(traces);
			return formatResponse(resp);
		} catch (Exception e) {
			LOG.error("Failed in TraceService.findTraces!", e);
			resp.setFailureMsg(ResponseConstants.FAILURE + ": " + e.getMessage());
			return formatResponse(resp);
		}
	}
	
	@POST
	@Path("/findTracesByImei")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON })
	public String findTracesByImei(String imei) {
		LOG.info("Inside TraceService.findTracesByImei.");
		Response<List<TraceModel>> resp = new Response<List<TraceModel>>();
		try {
			List<TraceModel> traces = traceDao.findTracesByImei(imei);
			resp.setData(traces);
			return formatResponse(resp);
		} catch (Exception e) {
			LOG.error("Failed in TraceService.findTracesByImei!", e);
			resp.setFailureMsg(ResponseConstants.FAILURE + ": " + e.getMessage());
			return formatResponse(resp);
		}
	}
	
}
