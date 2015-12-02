package baic.base.service.idao;

import java.util.List;

import baic.common.bean.CommonDepartment;
import baic.common.exception.ServiceException;

public interface IDepartmentDao {
	public CommonDepartment findDepartmentById(Long id) throws ServiceException;

	public List<CommonDepartment> findDepartmentsById(Long id) throws ServiceException;
	
	public void countVehiclesByDepartmentIds() throws ServiceException;
}
