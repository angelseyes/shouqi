package baic.base.service.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import baic.base.service.dao.bean.Admin;
import baic.base.service.idao.IAdminDao;
import baic.common.bean.CommonAdmin;
import baic.common.dao.CommonDao;
import baic.common.exception.ServiceException;

@Service
public class AdminDao implements IAdminDao {
	private static final Logger LOG = Logger.getLogger(AdminDao.class);
	@Autowired
	private CommonDao commonDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Long saveAdmin(CommonAdmin commonAdmin) throws ServiceException {
		LOG.info("Inside AdminDao.saveAdmin.");
		try {
			Admin admin = transType(commonAdmin);
			return commonDao.save(admin);
		} catch (Exception e) {
			LOG.error("Failed in AdminDao.save", e);
			throw new ServiceException("AdminDao.save failure due to unexpeted error!", e);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Long findBySql(String query, HashMap<String, Object> parameterMap) throws ServiceException {
		LOG.info("Inside AdminDao.findBySql.");
		try {
			Long count = (Long) commonDao.findCountByNativeSql(query, parameterMap);
			return count;
		} catch (Exception e) {
			LOG.error("Failed in AdminDao.findBySql", e);
			throw new ServiceException("AdminDao.findBySql failure due to unexpeted error!", e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<CommonAdmin> findAllAdmins() throws ServiceException {
		LOG.info("Inside AdminDao.findAllAdmins.");
		List<CommonAdmin> list = new ArrayList<CommonAdmin>();
		try {
			List<Admin> admins = (List<Admin>) commonDao.findAll(Admin.class, null);
			if (null != admins && !admins.isEmpty()) {
				for (Admin admin : admins) {
					list.add(transType(admin));
				}
			}
			return list;
		} catch (Exception e) {
			LOG.error("Failed in AdminDao.findAllAdmins", e);
			throw new ServiceException("AdminDao.findAllAdmins failure due to unexpeted error!", e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public CommonAdmin findAdminByUsername(String username) throws ServiceException {
		LOG.info("Inside AdminDao.findAdminByUsername.");
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("username", username);

		try {
			List<Admin> admins = (List<Admin>) commonDao.findByNamedQuery(Admin.FIND_ADMIN_BY_NAME, params);
			if (null != admins && !admins.isEmpty()) {
				return transType(admins.get(0));
			}
			return null;
		} catch (Exception e) {
			LOG.error("Failed in AdminDao.findAdminByUsername.", e);
			throw new ServiceException("AdminDao.findAdminByUsername failure due to unexpeted error!", e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<CommonAdmin> findAdminsByDepartmentId(Long departmentId) throws ServiceException {
		LOG.info("Inside AdminDao.findAdminsByDepartmentId.");
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("departmentId", departmentId);
		try {
			List<CommonAdmin> list = new ArrayList<CommonAdmin>();
			List<Admin> admins = (List<Admin>) commonDao.findByNamedQuery(Admin.FIND_ADMIN_BY_DEPARTMENT_ID, params);
			if (null != admins && !admins.isEmpty()) {
				for (Admin admin : admins) {
					list.add(transType(admin));
				}
			}
			return list;
		} catch (Exception e) {
			LOG.error("Failed in AdminDao.findAdminsByDepartmentId", e);
			throw new ServiceException("AdminDao.findAdminsByDepartmentId failure due to unexpeted error!", e);
		}
	}

	private CommonAdmin transType(Admin admin) {
		CommonAdmin commonAdmin = new CommonAdmin();
		BeanUtils.copyProperties(admin, commonAdmin);
		return commonAdmin;
	}

	private Admin transType(CommonAdmin commonAdmin) {
		Admin admin = new Admin();
		BeanUtils.copyProperties(commonAdmin, admin);
		return admin;
	}
}
