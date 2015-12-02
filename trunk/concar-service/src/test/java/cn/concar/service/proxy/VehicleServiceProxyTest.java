package cn.concar.service.proxy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import cn.concar.service.exception.ServiceException;
import cn.concar.service.model.MonitorModel;
import cn.concar.service.model.VehicleModel;
import cn.concar.service.model.stat.KeyBigIntegerValModel;
import cn.concar.service.model.stat.KeyBigDecimalValModel;

public class VehicleServiceProxyTest extends BaseTestUnit{
	private static final Logger LOG = Logger.getLogger(VehicleServiceProxyTest.class);

//	@Test
	public void findVehicleByDepartmentId() {
		try {
			List<VehicleModel> vehicles = VehicleServiceProxy.findVehicleByDepartmentId(1L);
			for (VehicleModel vehicle : vehicles) {
				LOG.info("vehicle plate number: " + vehicle.getPlateNumber());
			}
		} catch (ServiceException e) {
			LOG.error("Failed to find!", e);
			Assert.assertTrue(false);
		}
	}
	
//	@Test
	public void findAllMonitors() {
		try {
			List<MonitorModel> monitors = VehicleServiceProxy.findAllMonitors();
			for (MonitorModel monitor : monitors) {
				LOG.info("vehicle plate number: " + monitor.getPlateNumber());
			}
		} catch (ServiceException e) {
			LOG.error("Failed to find!", e);
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void findAllVehicles() {
		try {
			List<VehicleModel> vehicles = VehicleServiceProxy.findAllVehicles();
			for (VehicleModel vehicle : vehicles) {
				LOG.info("vehicle plate number: " + vehicle.getPlateNumber());
			}
		} catch (ServiceException e) {
			LOG.error("Failed to find!", e);
			Assert.assertTrue(false);
		}
	}
	
	
//	@Test
	public void queryVehMileageStat() {
		try {
			List<KeyBigIntegerValModel> pieModels = VehicleServiceProxy.queryVehMileageStat();
			TestResponseModel<List<KeyBigIntegerValModel>> model = new TestResponseModel<List<KeyBigIntegerValModel>>();
			model.setData(pieModels);
			model.setStatus("SERVICE_SUCCESS");
			LOG.info(toJson(model));
		} catch (ServiceException e) {
			LOG.error("Failed to find!", e);
			Assert.assertTrue(false);
		}
	}
	
	
//	@Test
	public void queryVehDepStat() {
		try {
			List<KeyBigIntegerValModel> pieModels = VehicleServiceProxy.queryVehDepStat();
			TestResponseModel<List<KeyBigIntegerValModel>> model = new TestResponseModel<List<KeyBigIntegerValModel>>();
			model.setData(pieModels);
			model.setStatus("SERVICE_SUCCESS");
			LOG.info(toJson(model));
		} catch (ServiceException e) {
			LOG.error("Failed to find!", e);
			Assert.assertTrue(false);
		}
	}
	
	
	
//	@Test
	public void queryVehTypeStat() {
		try {
			List<KeyBigIntegerValModel> pieModels = VehicleServiceProxy.queryVehTypeStat();
			TestResponseModel<List<KeyBigIntegerValModel>> model = new TestResponseModel<List<KeyBigIntegerValModel>>();
			model.setData(pieModels);
			model.setStatus("SERVICE_SUCCESS");
			LOG.info(toJson(model));
		} catch (ServiceException e) {
			LOG.error("Failed to find!", e);
			Assert.assertTrue(false);
		}
	}
	
	
	
//	@Test
	public void queryVehStatusStat() {
		try {
			List<KeyBigDecimalValModel> barModels = VehicleServiceProxy.queryVehStatusStat();
			TestResponseModel<List<KeyBigDecimalValModel>> model = new TestResponseModel<List<KeyBigDecimalValModel>>();
			model.setData(barModels);
			model.setStatus("SERVICE_SUCCESS");
			LOG.info(toJson(model));
		} catch (ServiceException e) {
			LOG.error("Failed to find!", e);
			Assert.assertTrue(false);
		}
	}
	
	
	
//	@Test
	public void queryHistoryStat() {
		try {
			List<String> dateTypes = new ArrayList<String>();
			dateTypes.add("DTC");
			dateTypes.add("ACT");
 			List<KeyBigIntegerValModel> barModels = VehicleServiceProxy.queryHistoryStat(new Date(115,9,1), new Date(115,9,20), dateTypes);
 			TestResponseModel<List<KeyBigIntegerValModel>> model = new TestResponseModel<List<KeyBigIntegerValModel>>();
			model.setData(barModels);
			model.setStatus("SERVICE_SUCCESS");
			LOG.info(toJson(model));
		} catch (ServiceException e) {
			LOG.error("Failed to find!", e);
			Assert.assertTrue(false);
		}
	}
}
