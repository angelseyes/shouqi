package cn.concar.service.proxy;

import java.util.List;

import org.apache.log4j.Logger;

import cn.concar.service.BeanFactory;
import cn.concar.service.DeviceService;
import cn.concar.service.exception.ServiceException;
import cn.concar.service.model.DeviceModel;

public class DeviceServiceProxy {
	private static final Logger LOG = Logger.getLogger(DeviceServiceProxy.class);

	private static DeviceService service = BeanFactory.getService(DeviceService.class);

	public static List<DeviceModel> findAllDevices() throws ServiceException {
		try {
			return service.findAllDevices();
		} catch (Exception e) {
			LOG.error("DeviceServiceProxy.findAllDevices failure!", e);
			throw new ServiceException("DeviceServiceProxy.findAllDevices failure!", e);
		}
	}
}
