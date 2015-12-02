package cn.concar.service.proxy;

import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import cn.concar.service.exception.ServiceException;
import cn.concar.service.model.DepartmentModel;

public class DepartmentServiceProxyTest {
	private static final Logger LOG = Logger.getLogger(DepartmentServiceProxyTest.class);

	@Test
	public void findAllDepartments() {
		try {
			List<DepartmentModel> allDepartments = DepartmentServiceProxy.findAllDepartments();
//			for (DepartmentModel department : allDepartments) {
//				LOG.info("department: " + department.toString());
//				ObjectMapper om = new ObjectMapper();
//				String result = om.writeValueAsString(department);
//				LOG.info("department: " + result);
//			}
			ObjectMapper om = new ObjectMapper();
			String result = om.writeValueAsString(allDepartments);
			LOG.info("department: " + result);
		} catch (Exception e) {
			LOG.error("Failed to find!", e);
			Assert.assertTrue(false);
		}
	}
	
//	@Test
	public void findAllDepartmentStat() {
		try {
			List<DepartmentModel> allDepartments = DepartmentServiceProxy.findAllDepartmentStat();
			for (DepartmentModel department : allDepartments) {
				LOG.info("department code: " + department.getDepartmentCode());
				LOG.info("department vehicle number: " + department.getVehicleNumber());
			}
		} catch (ServiceException e) {
			LOG.error("Failed to find!", e);
			Assert.assertTrue(false);
		}
	}
}
