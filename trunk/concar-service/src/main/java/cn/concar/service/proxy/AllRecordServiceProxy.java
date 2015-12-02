package cn.concar.service.proxy;

import java.util.List;

import org.apache.log4j.Logger;

import cn.concar.service.AllRecordService;
import cn.concar.service.BeanFactory;
import cn.concar.service.exception.ServiceException;
import cn.concar.service.model.AllRecordModel;

public class AllRecordServiceProxy {
	private static final Logger LOG = Logger.getLogger(AllRecordServiceProxy.class);

	private static AllRecordService service = BeanFactory.getService(AllRecordService.class);

	public static void addAllRecordBatch(List<AllRecordModel> input) throws ServiceException {
		try {
			service.addAllRecordBatch(input);
		} catch (Exception e) {
			LOG.error("VehTnTServiceProxy.addVehTnTBatch failure!", e);
			throw new ServiceException("VehTnTServiceProxy.addVehTnTBatch failure!", e);
		}
	}

}
