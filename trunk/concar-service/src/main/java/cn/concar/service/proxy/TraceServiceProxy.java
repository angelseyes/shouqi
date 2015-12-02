package cn.concar.service.proxy;

import java.util.List;

import org.apache.log4j.Logger;

import cn.concar.service.BeanFactory;
import cn.concar.service.TraceService;
import cn.concar.service.exception.ServiceException;
import cn.concar.service.model.TraceModel;

public class TraceServiceProxy {
	private static final Logger LOG = Logger.getLogger(TraceServiceProxy.class);

	private static TraceService service = BeanFactory.getService(TraceService.class);

	public static List<TraceModel> findTraceByImei(String imei) throws ServiceException {
		try {
			return service.findTraceByImei(imei);
		} catch (Exception e) {
			LOG.error("TraceServiceProxy.findModelByImei failure!", e);
			throw new ServiceException("TraceServiceProxy.findModelByImei failure!", e);
		}
	}

	public static String findTraceLineByTraceId(Long traceId) throws ServiceException {
		try {
			return service.findTraceLineByTraceId(traceId);
		} catch (Exception e) {
			LOG.error("TraceServiceProxy.findTraceLineByTraceId failure!", e);
			throw new ServiceException("TraceServiceProxy.findTraceLineByTraceId failure!", e);

		}
	}

}
