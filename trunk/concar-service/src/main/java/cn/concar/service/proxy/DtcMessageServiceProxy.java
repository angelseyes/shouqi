package cn.concar.service.proxy;

import org.apache.log4j.Logger;

import cn.concar.service.BeanFactory;
import cn.concar.service.DtcMessageService;
import cn.concar.service.exception.ServiceException;

public class DtcMessageServiceProxy {
	private static final Logger LOG = Logger.getLogger(DtcMessageServiceProxy.class);

	private static DtcMessageService service = BeanFactory.getService(DtcMessageService.class);

	public static String findDtcMessageByDtc(String dtc) throws ServiceException {
		try {
			return service.findDtcMessageByDtc(dtc);
		} catch (Exception e) {
			LOG.error("DtcMessageServiceProxy.findDtcMessageByDtc failure!", e);
			throw new ServiceException("DtcMessageServiceProxy.findDtcMessageByDtc failure!", e);
		}
	}
}
