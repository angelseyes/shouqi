package baic.obd.service;

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
import baic.obd.model.LatestRecordModel;
import baic.obd.service.idao.ILatestRecordDao;

public class LatestRecordService extends BaseService {

	private static final Logger LOG = Logger.getLogger(LatestRecordService.class);

	@Autowired
	private ILatestRecordDao latestRecordDao;

	@POST
	@Path("/findLatestRecordByImei")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON })
	public String findLatestRecordByImei(String imei) {
		LOG.info("Inside LatestRecordService.findLatestRecordByImei.");
		Response<LatestRecordModel> resp = new Response<LatestRecordModel>();
		try {
			LatestRecordModel latestRecord = latestRecordDao.findLatestRecordByImei(imei);
			resp.setData(latestRecord);
			return formatResponse(resp);
		} catch (Exception e) {
			LOG.error("Failed in LatestRecordService.findLatestRecordByImei!", e);
			resp.setFailureMsg(ResponseConstants.FAILURE + ": " + e.getMessage());
			return formatResponse(resp);
		}
	}
}
