package cn.concar.service.proxy;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import cn.concar.service.exception.ServiceException;
import cn.concar.service.model.AllRecordModel;

public class AllRecordServiceProxyTest {
	private static final Logger LOG = Logger.getLogger(AllRecordServiceProxyTest.class);

	@Test
	public void addAllRecordBatch() {
		try {
			List<AllRecordModel> allRecords = new ArrayList<AllRecordModel>();
			AllRecordModel model = new AllRecordModel();
			model.setDtc("test");
			allRecords.add(model);
			AllRecordServiceProxy.addAllRecordBatch(allRecords);
		} catch (ServiceException e) {
			LOG.error("Failed to add!", e);
			Assert.assertTrue(false);
		}
	}
}
