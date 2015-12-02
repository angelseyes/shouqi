package baic.base.service.idao;

import java.util.List;

import baic.common.bean.CommonAdmin;
import baic.common.exception.ServiceException;

public interface IAdminDao {
	public Long saveAdmin(CommonAdmin commonAdmin) throws ServiceException;
	
	public CommonAdmin findAdminByUsername(String username) throws ServiceException;

	public List<CommonAdmin> findAllAdmins() throws ServiceException;

	public List<CommonAdmin> findAdminsByDepartmentId(Long departmentId) throws ServiceException;
}
