package baic.base.service.idao;

import java.util.List;

import baic.common.bean.CommonVehicle;
import baic.common.exception.ServiceException;

public interface IVehicleDao {
	public List<CommonVehicle> findAllVehicles() throws ServiceException;

	/**
	 * findVehiclesByDepartmentId
	 * 
	 * @param departmentId
	 * 
	 * @return List<CommonVehicle>
	 * @throws ServiceException
	 */
	public List<CommonVehicle> findVehiclesByDepartmentId(Long departmentId) throws ServiceException;

	/**
	 * findVehiclesByAdminId
	 * 
	 * @param adminId
	 * 
	 * @return List<CommonVehicle>
	 * @throws ServiceException
	 */
	public List<CommonVehicle> findVehiclesByAdminId(Long adminId) throws ServiceException;

	/**
	 * findSubVehiclesByDepartmentId
	 * 
	 * @param departmentId
	 * @return List<CommonVehicle>
	 * @throws ServiceException
	 */
	public List<CommonVehicle> findSubVehiclesByDepartmentId(Long departmentId) throws ServiceException;

}
