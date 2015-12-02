package cn.concar.service.proxy;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import cn.concar.service.exception.ServiceException;
import cn.concar.service.model.LatestRecordModel;

public class LatestRecordServiceProxyTest {
	private static final Logger LOG = Logger.getLogger(LatestRecordServiceProxyTest.class);

	@Test
	public void addAllRecordBatch() {
		try {
			LatestRecordModel record = LatestRecordServiceProxy.findLatestRecordByImei("111");
			LOG.info("trace time: " + record.getTraceTime());
		} catch (ServiceException e) {
			LOG.error("Failed to find!", e);
			Assert.assertTrue(false);
		}
	}
}
