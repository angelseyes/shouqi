package baic.sealed.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.core.type.TypeReference;

import baic.common.client.ClientFactory;
import baic.common.model.Response;
import baic.common.service.BaseService;
import baic.common.utils.ResponseConstants;
import baic.common.utils.ServiceConstants;
import baic.obd.model.TraceModel;
import baic.sealed.service.model.MonitorTrace;

public class MonitorService extends BaseService {

	private static final Logger LOG = Logger.getLogger(MonitorService.class);

	// TODO findDepartments Zhao Li

	// TODO findVehicles Zhao Li

	@SuppressWarnings("unchecked")
	@POST
	@Path("/findTraces")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON })
	public String findTraces(String imei) {
		LOG.info("Inside MonitorService.findTraces.");
		Response<List<MonitorTrace>> resp = new Response<List<MonitorTrace>>();
		try {
			// Get trace from obd service.
			WebClient obdClient = ClientFactory.getClient(ServiceConstants.Obd.Trace.Url.FIND_TRACES_BY_IMEI_URL);
			String respStr = obdClient.post(imei, String.class);
			Response<?> response = readResponse(respStr, new TypeReference<Response<List<TraceModel>>>() {
			});

			if (!response.isSuccess() || null == response.getData()) {
				return formatResponse(resp);
			}

			List<TraceModel> traceModels = (List<TraceModel>) response.getData();
			if (null != traceModels && !traceModels.isEmpty()) {
				List<MonitorTrace> monitorTrace = new ArrayList<MonitorTrace>();
				for (TraceModel traceModel : traceModels) {
					monitorTrace.add(transType(traceModel));
				}
				resp.setData(monitorTrace);
			}
			return formatResponse(resp);
		} catch (Exception e) {
			LOG.error("Failed in MonitorService.findTraces!", e);
			resp.setFailureMsg(ResponseConstants.FAILURE + ": " + e.getMessage());
			return formatResponse(resp);
		}
	}

	private MonitorTrace transType(TraceModel traceModel) {
		MonitorTrace monitorTrace = new MonitorTrace();
		BeanUtils.copyProperties(traceModel, monitorTrace);
		return monitorTrace;
	}

}
