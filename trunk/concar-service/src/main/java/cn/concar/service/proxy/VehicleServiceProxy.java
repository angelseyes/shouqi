package cn.concar.service.proxy;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import cn.concar.service.BeanFactory;
import cn.concar.service.VehicleService;
import cn.concar.service.exception.ServiceException;
import cn.concar.service.model.MonitorModel;
import cn.concar.service.model.VehicleModel;
import cn.concar.service.model.stat.KeyBigDecimalValModel;
import cn.concar.service.model.stat.KeyBigIntegerValModel;

public class VehicleServiceProxy {
	private static final Logger LOG = Logger.getLogger(VehicleServiceProxy.class);

	private static VehicleService service = BeanFactory.getService(VehicleService.class);

	public static List<VehicleModel> findVehicleByDepartmentId(Long departmentId) throws ServiceException {
		try {
			return service.findVehicleByDepartmentId(departmentId);
		} catch (Exception e) {
			LOG.error("VehicleServiceProxy.findVehicleByDepartmentId failure!", e);
			throw new ServiceException("VehicleServiceProxy.findVehicleByDepartmentId failure!", e);
		}
	}

	public static List<VehicleModel> findSimpleVehicleByDepartmentId(Long departmentId) throws ServiceException {
		try {
			return service.findSimpleVehicleByDepartmentId(departmentId);
		} catch (Exception e) {
			LOG.error("VehicleServiceProxy.findSimpleVehicleByDepartmentId failure!", e);
			throw new ServiceException("VehicleServiceProxy.findSimpleVehicleByDepartmentId failure!", e);
		}
	}

	public static List<MonitorModel> findAllMonitors() throws ServiceException {
		try {
			return service.findAllMonitors();
		} catch (Exception e) {
			LOG.error("VehicleServiceProxy.findAllMonitors failure!", e);
			throw new ServiceException("VehicleServiceProxy.findAllMonitors failure!", e);
		}
	}

	public static List<VehicleModel> findAllVehicles() throws ServiceException {
		try {
			return service.findAllVehicles();
		} catch (Exception e) {
			LOG.error("VehicleServiceProxy.findAllVehicles failure!", e);
			throw new ServiceException("VehicleServiceProxy.findAllVehicles failure!", e);
		}
	}

	public static List<KeyBigIntegerValModel> queryVehMileageStat()
			throws ServiceException {
		try {
			return service.queryVehMileageStat();
		} catch (Exception e) {
			LOG.error("VehicleServiceProxy.queryVehMileageStat failure!", e);
			throw new ServiceException("VehicleServiceProxy.queryVehMileageStat failure!", e);
		}
	}
	
	
	public static List<KeyBigIntegerValModel> queryVehDepStat()
			throws ServiceException {
		try {
			return service.queryVehDepStat();
		} catch (Exception e) {
			LOG.error("VehicleServiceProxy.queryVehDepStat failure!", e);
			throw new ServiceException("VehicleServiceProxy.queryVehDepStat failure!", e);
		}
	}
	
	
	public static List<KeyBigIntegerValModel> queryVehTypeStat()
			throws ServiceException {
		try {
			return service.queryVehTypeStat();
		} catch (Exception e) {
			LOG.error("VehicleServiceProxy.queryVehTypeStat failure!", e);
			throw new ServiceException("VehicleServiceProxy.queryVehTypeStat failure!", e);
		}
	}
	
	
	public static List<KeyBigDecimalValModel> queryVehStatusStat()
			throws ServiceException {
		try {
			return service.queryVehStatusStat();
		} catch (Exception e) {
			LOG.error("VehicleServiceProxy.queryVehStatusStat failure!", e);
			throw new ServiceException("VehicleServiceProxy.queryVehStatusStat failure!", e);
		}
	}
	
	
	public static List<KeyBigIntegerValModel> queryHistoryStat(Date startDate, Date endDate, List<String> dateTypes)
			throws ServiceException {
		try {
			return service.queryHistoryStat(startDate, endDate, dateTypes);
		} catch (Exception e) {
			LOG.error("VehicleServiceProxy.queryVehStatusStat failure!", e);
			throw new ServiceException("VehicleServiceProxy.queryVehStatusStat failure!", e);
		}
	}
}
