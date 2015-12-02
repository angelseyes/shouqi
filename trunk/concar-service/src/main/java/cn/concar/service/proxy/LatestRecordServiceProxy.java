package cn.concar.service.proxy;

import org.apache.log4j.Logger;

import cn.concar.service.BeanFactory;
import cn.concar.service.LatestRecordService;
import cn.concar.service.exception.ServiceException;
import cn.concar.service.model.LatestRecordModel;

public class LatestRecordServiceProxy {
	private static final Logger LOG = Logger.getLogger(LatestRecordServiceProxy.class);

	private static LatestRecordService service = BeanFactory.getService(LatestRecordService.class);

	public static LatestRecordModel findLatestRecordByImei(String imei) throws ServiceException {
		try {
			return service.findLatestRecordByImei(imei);
		} catch (Exception e) {
			LOG.error("LatestRecordServiceProxy.findLatestRecordByImei failure!", e);
			throw new ServiceException("LatestRecordServiceProxy.findLatestRecordByImei failure!", e);
		}
	}

}
