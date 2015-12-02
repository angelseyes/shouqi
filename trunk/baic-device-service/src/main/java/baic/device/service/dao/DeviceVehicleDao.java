package baic.device.service.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import baic.common.dao.CommonDao;
import baic.common.exception.ServiceException;
import baic.device.service.dao.bean.DeviceVehicle;
import baic.device.service.idao.IDeviceVehicleDao;

@Service
public class DeviceVehicleDao implements IDeviceVehicleDao {
	private static final Logger LOG = Logger.getLogger(DeviceVehicleDao.class);
	@Autowired
	private CommonDao commonDao;

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public String findImeiByVehicleId(Long vehicleId) throws ServiceException {
		LOG.info("Inside DeviceVehicleDao.findImeiByVehicleId.");
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("vehicleId", vehicleId);
		try {
			List<String> imeis = (List<String>) commonDao.findByNamedQuery(DeviceVehicle.FIND_IMEI_BY_VEHICLE_ID,
					params);
			if (null != imeis && !imeis.isEmpty()) {
				return imeis.get(0);
			}
			return null;
		} catch (Exception e) {
			LOG.error("Failed in DeviceVehicleDao.findImeiByVehicleId", e);
			throw new ServiceException("DeviceVehicleDao.findImeiByVehicleId failure due to unexpeted error!", e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Long findVehicleIdByImei(String imei) throws ServiceException {
		LOG.info("Inside DeviceVehicleDao.findImeiByVehicleId.");
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("imei", imei);
		try {
			List<Long> vehicleIds = (List<Long>) commonDao.findByNamedQuery(DeviceVehicle.FIND_VEHICLE_ID_BY_IMEI,
					params);
			if (null != vehicleIds && !vehicleIds.isEmpty()) {
				return vehicleIds.get(0);
			}
			return null;
		} catch (Exception e) {
			LOG.error("Failed in DeviceVehicleDao.findImeiByVehicleId", e);
			throw new ServiceException("DeviceVehicleDao.findImeiByVehicleId failure due to unexpeted error!", e);
		}
	}
}
