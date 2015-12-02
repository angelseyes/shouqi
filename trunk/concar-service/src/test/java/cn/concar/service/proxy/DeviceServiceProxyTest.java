package cn.concar.service.proxy;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import cn.concar.service.exception.ServiceException;
import cn.concar.service.model.DeviceModel;

public class DeviceServiceProxyTest {
	private static final Logger LOG = Logger.getLogger(DeviceServiceProxyTest.class);

	@Test
	public void findVehicleByDepartmentId() {
		try {
			List<DeviceModel> devices = DeviceServiceProxy.findAllDevices();
			for (DeviceModel device : devices) {
				LOG.info("device imei: " + device.getImei());
			}
		} catch (ServiceException e) {
			LOG.error("Failed to find!", e);
			Assert.assertTrue(false);
		}
	}
}
