package cn.concar.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.concar.service.db.dao.CommonDao;
import cn.concar.service.exception.ServiceException;
import cn.concar.service.model.DeviceModel;

@Service
public class DeviceService {
	private static final Logger LOG = Logger.getLogger(DeviceService.class);
	@Autowired
	private CommonDao commonDao;

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<DeviceModel> findAllDevices() throws ServiceException {
		LOG.info("Inside DeviceService.findAllDevices.");
		try {
			return (List<DeviceModel>) commonDao.findByNativeSql(findAllDevices, null, DeviceModel.class);
		} catch (Exception e) {
			LOG.error("Failed in DeviceService.findAllDevices.", e);
			throw new ServiceException("DeviceService.findAllDevices failure due to unexpeted error!", e);
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<DeviceModel> addDevices(List<DeviceModel> devices) throws ServiceException {
		LOG.info("Inside DeviceService.addDevices");
		try {
			for (DeviceModel device : devices) {
				this.commonDao.save(device);
			}
			return devices;
		} catch (Exception e) {
			LOG.error("Failed in DeviceService.addDevices", e);
            throw new ServiceException("DeviceService.addDevices failure due to unexpeted error!", e);
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<DeviceModel> updateDevices(List<DeviceModel> devices) throws ServiceException {
		LOG.info("Inside DeviceService.updateDevices");
		try {
			for (DeviceModel device : devices) {
				this.commonDao.update(device);
			}
			return devices;
		} catch (Exception e) {
			LOG.error("Failed in DeviceService.updateDevices", e);
            throw new ServiceException("DeviceService.updateDevices failure due to unexpeted error!", e);
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public void deleteDevices(List<DeviceModel> devices) throws ServiceException {
		LOG.info("Inside DeviceService.deleteDevices");
		try {
			for (DeviceModel device : devices) {
				this.commonDao.delete(device);
			}
		} catch (Exception e) {
			LOG.error("Failed in DeviceService.updateDevices", e);
            throw new ServiceException("DeviceService.deleteDevices failure due to unexpeted error!", e);
		}
	}
	
	private static final String findAllDevices = "select "
			+ "dvc.imei imei, "
			+ "v.plate_number plateNumber, "
			+ "dpt.department_code departmentCode, "
			+ "dvc.sim_number simNumber, "
			+ "r.trace_time traceTime, "
			+ "ifnull((DATE_FORMAT(r.trace_time, '%Y-%m-%d') = curdate()), 0) active "
			+ "from vehicle v, department dpt, device dvc, latest_record r "
			+ "where v.imei = dvc.imei and v.belong_department = dpt.department_id and v.imei = r.imei "
			+ "order by imei asc";
}
