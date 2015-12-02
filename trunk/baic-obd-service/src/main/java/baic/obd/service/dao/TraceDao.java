package baic.obd.service.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import baic.common.dao.CommonDao;
import baic.common.exception.ServiceException;
import baic.obd.model.TraceModel;
import baic.obd.service.dao.bean.Trace;
import baic.obd.service.idao.ITraceDao;

@Service
public class TraceDao implements ITraceDao {
	private static final Logger LOG = Logger.getLogger(TraceDao.class);
	@Autowired
	private CommonDao commonDao;

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<TraceModel> findAllTraces() throws ServiceException {
		LOG.info("Inside TraceDao.findAllTraces.");
		try {
			List<TraceModel> list = new ArrayList<TraceModel>();
			List<Trace> traces = (List<Trace>) commonDao.findAll(Trace.class, null);
			if (null != traces && !traces.isEmpty()) {
				for (Trace trace : traces) {
					list.add(transType(trace));
				}
			}
			return list;
		} catch (Exception e) {
			LOG.error("Failed in TraceDao.findAllTraces", e);
			throw new ServiceException("TraceDao.findAllTraces failure due to unexpeted error!", e);
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<TraceModel> findTracesByImei(String imei) throws ServiceException {
		LOG.info("Inside TraceDao.findTraceDatesByImei.");
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("imei", imei);
		try {
			List<TraceModel> list = new ArrayList<TraceModel>();
			List<Trace> traces = (List<Trace>) commonDao.findByNamedQuery(Trace.FIND_TRACE_BY_IMEI, params);
			if (null != traces && !traces.isEmpty()) {
				for (Trace trace : traces) {
					list.add(transType(trace));
				}
			}
			return list;
		} catch (Exception e) {
			LOG.error("Failed in TraceDao.findAllTraceDates", e);
			throw new ServiceException("TraceDao.findAllTraceDates failure due to unexpeted error!", e);
		}
	}

	private TraceModel transType(Trace trace) {
		TraceModel traceModel = new TraceModel();
		BeanUtils.copyProperties(trace, traceModel);
		return traceModel;
	}
}
