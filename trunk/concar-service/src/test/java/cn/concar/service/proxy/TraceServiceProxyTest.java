package cn.concar.service.proxy;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import cn.concar.service.exception.ServiceException;
import cn.concar.service.model.TraceModel;

public class TraceServiceProxyTest {
	private static final Logger LOG = Logger.getLogger(TraceServiceProxyTest.class);

//	@Test
	public void findTraceByImei() {
		try {
			List<TraceModel> traces = TraceServiceProxy.findTraceByImei("111");
			for (TraceModel trace : traces) {
				LOG.info("trace id: " + trace.getTraceId());
			}
		} catch (ServiceException e) {
			LOG.error("Failed to find!", e);
			Assert.assertTrue(false);
		}
	}

	@Test
	public void findTraceLineByTraceId() {
		try {
			String traceLine = TraceServiceProxy.findTraceLineByTraceId(1L);
			LOG.info("trace line: " + traceLine);
		} catch (ServiceException e) {
			LOG.error("Failed to find!", e);
			Assert.assertTrue(false);
		}
	}
}
