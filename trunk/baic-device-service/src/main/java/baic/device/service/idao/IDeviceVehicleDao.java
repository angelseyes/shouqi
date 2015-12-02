package baic.device.service.idao;

import baic.common.exception.ServiceException;

public interface IDeviceVehicleDao {

	public String findImeiByVehicleId(Long vehicleId) throws ServiceException;
	
	public Long findVehicleIdByImei(String imei) throws ServiceException;
}
