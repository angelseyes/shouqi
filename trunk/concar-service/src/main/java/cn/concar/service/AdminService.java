package cn.concar.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.concar.service.db.dao.CommonDao;
import cn.concar.service.exception.ServiceException;
import cn.concar.service.model.AdminModel;
import cn.concar.service.model.DeviceModel;

@Service
public class AdminService {
	private static final Logger LOG = Logger.getLogger(AdminService.class);
	@Autowired
	private CommonDao commonDao;

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<AdminModel> findAllAdmins() throws ServiceException {
		LOG.info("Inside AdminService.findAllAdmins.");
		try {
			return (List<AdminModel>) commonDao.findByNativeSql(findAllAdmins, null, DeviceModel.class);
		} catch (Exception e) {
			LOG.error("Failed in AdminService.findAllAdmins.", e);
			throw new ServiceException("AdminService.findAllAdmins failure due to unexpeted error!", e);
		}
	}
	
	private static final String findAllAdmins = "select "
			+ "a.admin_id adminId, "
			+ "a.username username, "
			+ "a.password password, "
			+ "a.realname realname, "
			+ "a.email email, "
			+ "a.phone phone, "
			+ "d.department_id belongDepartment, "
			+ "d.department_code departmentCode "
			+ "from admin a "
			+ "left join department d "
			+ "on a.belong_department = d.department_id "
			+ "order by a.admin_id desc";
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public AdminModel findAdminByUsername(String username) throws ServiceException {
		LOG.info("Inside AdminService.findAdminByUsername.");
		HashMap<String, Object> params = new HashMap<String, Object>();
		AdminModel admin = new AdminModel();
		params.put("username", username);
		try {
			List<AdminModel> admins = new ArrayList<AdminModel>();
			admins = (List<AdminModel>)commonDao.findByNativeSql(findAdminByUsername, params, AdminModel.class);
			if (admins != null && admins.size() > 0) {
				admin = admins.get(0);
			}
			return admin;
		} catch (Exception e) {
			LOG.error("Failed in AdminService.findAllAdmins.", e);
			throw new ServiceException("AdminService.findAllAdmins failure due to unexpeted error!", e);
		}
	}
	
	private static final String findAdminByUsername = "select "
			+ "a.admin_id adminId, "
			+ "a.username username, "
			+ "a.password password, "
			+ "a.realname realname, "
			+ "a.email email, "
			+ "a.phone phone, "
			+ "a.belong_department belongDepartment "
			+ "from admin a"
			+ "where a.username = :username";
}
