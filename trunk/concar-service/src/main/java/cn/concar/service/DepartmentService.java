package cn.concar.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.concar.service.db.dao.CommonDao;
import cn.concar.service.exception.ServiceException;
import cn.concar.service.model.DepartmentModel;

@Service
public class DepartmentService {
	private static final Logger LOG = Logger.getLogger(DepartmentService.class);
	@Autowired
	private CommonDao commonDao;

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<DepartmentModel> findAllDepartments() throws ServiceException {
		LOG.info("Inside DepartmentService.findAllDepartments.");
		try {
			return (List<DepartmentModel>) commonDao.findByNativeSql(findAllDepartments, null, DepartmentModel.class);
		} catch (Exception e) {
			LOG.error("Failed in DepartmentService.findAllDepartments", e);
			throw new ServiceException("DepartmentService.findAllDepartments failure due to unexpeted error!", e);
		}
	}
	
	private static final String findAllDepartments = "select "
			+ "d.department_id departmentId, "
			+ "d.department_name departmentName, "
			+ "d.department_code departmentCode, "
			+ "d.address address, "
			+ "d.longitude longitude, "
			+ "d.latitude latitude, "
			+ "count(v.vehicle_id) vehicleNumber "
			+ "from department d "
			+ "left join vehicle v "
			+ "on d.department_id = v.belong_department "
			+ "group by d.department_id "
			+ "order by d.department_id";
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<DepartmentModel> findAllSimpleDepartments() throws ServiceException {
		LOG.info("Inside DepartmentService.findAllSimpleDepartments.");
		try {
			return (List<DepartmentModel>) commonDao.findByNativeSql(findAllSimpleDepartments, null, DepartmentModel.class);
		} catch (Exception e) {
			LOG.error("Failed in DepartmentService.findAllSimpleDepartments", e);
			throw new ServiceException("DepartmentService.findAllSimpleDepartments failure due to unexpeted error!", e);
		}
	}
	
	private static final String findAllSimpleDepartments = "select "
			+ "d.department_name departmentName, "
			+ "d.department_code departmentCode "
			+ "from department d "
			+ "order by d.department_id";
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<DepartmentModel> findAllDepartmentStat() throws ServiceException {
		LOG.info("Inside DepartmentService.findAllDepartmentStat.");
		try {
			return (List<DepartmentModel>) commonDao.findByNativeSql(findAllDepartmentStat, null, DepartmentModel.class);
		} catch (Exception e) {
			LOG.error("Failed in DepartmentService.findAllDepartmentStat", e);
			throw new ServiceException("DepartmentService.findAllDepartmentStat failure due to unexpeted error!", e);
		}
	}
	
	private static final String findAllDepartmentStat = "select "
			+ "d.department_code departmentCode, "
			+ "d.department_name departmentName, "
			+ "d.address,(sum(a.abnorm) + sum(a.norm)) countNumber, "
			+ "sum(a.abnorm) abnormalNumber, "
			+ "sum(a.norm) normalNumber, "
			+ "(case when sum(a.abnorm) = 0 then 0 else sum(a.norm) / (sum(a.abnorm) + sum(a.norm)) end) normalRate, "
			+ "sum(a.inact) inactiveNumber, "
			+ "sum(a.act) activeNumber, "
			+ "(case when sum(a.inact) = 0 then 0 else sum(a.act) / (sum(a.abnorm) + sum(a.norm)) end) activeRate "
			+ "from( "
			+ "select department_code, "
			+ "case when is_normal = 0 then c else 0 end as abnorm, "
			+ "case when is_normal = 1 then c else 0 end as norm, "
			+ "case when is_active = 0 then c else 0 end as inact, "
			+ "case when is_active = 1 then c else 0 end as act "
			+ "from is_norm_active) a, "
			+ "department d "
			+ "where a.department_code = d.department_code "
			+ "group by d.department_code, d.department_name, d.address";
}
