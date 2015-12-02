package cn.concar.service;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.concar.service.db.dao.CommonDao;
import cn.concar.service.exception.ServiceException;
import cn.concar.service.model.TraceModel;

@Service
public class TraceService {
	private static final Logger LOG = Logger.getLogger(TraceService.class);
	@Autowired
	private CommonDao commonDao;

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<TraceModel> findTraceByImei(String imei) throws ServiceException {
		LOG.info("Inside TraceService.findTraceByImei.");
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("imei", imei);
		try {
			return (List<TraceModel>) commonDao.findByNativeSql(findTraceByImei, params, TraceModel.class);
		} catch (Exception e) {
			LOG.error("Failed in TraceService.findTraceByImei.", e);
			throw new ServiceException("TraceService.findTraceByImei failure due to unexpeted error!", e);
		}
	}

	private static final String findTraceByImei = "select " 
			+ "t.trace_id traceId, " 
			+ "t.imei imei, "
			+ "t.trace_date traceDate " 
			//+ "t.trace_line traceLine " 
			+ "from trace t " 
			+ "where t.imei = :imei "
			+ "order by t.trace_date desc";

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public String findTraceLineByTraceId(Long traceId) throws ServiceException {
		LOG.info("Inside TraceService.findTraceLineByTraceId.");
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("traceId", traceId);
		try {
			String ret = null;
			List<TraceModel> result = (List<TraceModel>) commonDao.findByNativeSql(findTraceLineByTraceId, params,
					TraceModel.class);
			if (result != null && result.size() > 0) {
				ret = result.get(0).getTraceLine();
			}
			return ret;
		} catch (Exception e) {
			LOG.error("Failed in TraceService.findTraceLineByTraceId.", e);
			throw new ServiceException("TraceService.findTraceLineByTraceId failure due to unexpeted error!", e);
		}
	}

	private static final String findTraceLineByTraceId = "select " 
			+ "t.trace_line traceLine " 
			+ "from trace t "
			+ "where t.trace_id = :traceId";
}
