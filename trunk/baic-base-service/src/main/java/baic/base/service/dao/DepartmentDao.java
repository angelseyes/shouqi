package baic.base.service.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import baic.base.service.dao.bean.Department;
import baic.base.service.dao.bean.Vehicle;
import baic.base.service.idao.IDepartmentDao;
import baic.common.bean.CommonDepartment;
import baic.common.dao.CommonDao;
import baic.common.exception.ServiceException;

@Service
public class DepartmentDao implements IDepartmentDao {
	private static final Logger LOG = Logger.getLogger(DepartmentDao.class);

	@Autowired
	private CommonDao commonDao;

	public static HashMap<Long, String> departmentNameMap = new HashMap<Long, String>();
	public static HashMap<Long, Long> departmentVehicleMap = new HashMap<Long, Long>();

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public CommonDepartment findDepartmentById(Long id) throws ServiceException {
		LOG.info("Inside DepartmentDao.findDepartmentById.");
		try {
			Department department = (Department) commonDao.findById(Department.class, id);
			if (departmentVehicleMap.size() == 0) {
				countVehiclesByDepartmentIds();
			}
			if (department != null) {
				return transType(department);
			}
			return null;
		} catch (Exception e) {
			LOG.error("Failed in DepartmentDao.findDepartmentById", e);
			throw new ServiceException("DepartmentDao.findDepartmentById failure due to unexpeted error!", e);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<CommonDepartment> findDepartmentsById(Long id) throws ServiceException {
		LOG.info("Inside DepartmentDao.findDepartmentsById.");
		try {
			CommonDepartment commonDepartment = findDepartmentById(id);
			if (commonDepartment != null) {
				return getDepartments(commonDepartment);
			}
			return null;
		} catch (Exception e) {
			LOG.error("Failed in DepartmentDao.findDepartmentsById", e);
			throw new ServiceException("DepartmentDao.findDepartmentsById failure due to unexpeted error!", e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public void countVehiclesByDepartmentIds() throws ServiceException {
		LOG.info("Inside DepartmentDao.countVehiclesByDepartmentIds.");
		try {
			List<Object[]> countList = (List<Object[]>) commonDao
					.findByNamedQuery(Vehicle.COUNT_VEHICLE_BY_DEPARTMENT_IDS, null);
			for (Object[] countPair : countList) {
				departmentVehicleMap.put((Long) countPair[1], (Long) countPair[0]);
			}
			LOG.info(countList);
		} catch (Exception e) {
			LOG.error("Failed in DepartmentDao.countVehiclesByDepartmentIds", e);
			throw new ServiceException("DepartmentDao.countVehiclesByDepartmentIds failure due to unexpeted error!", e);
		}
	}

	private CommonDepartment transType(Department department) {
		CommonDepartment commonDepartment = new CommonDepartment();
		BeanUtils.copyProperties(department, commonDepartment);
		
		commonDepartment.setVehicleNumber(departmentVehicleMap.get(commonDepartment.getDepartmentId()));
		if (null != department.getDepartments() && !department.getDepartments().isEmpty()) {
			List<CommonDepartment> departments = new ArrayList<CommonDepartment>();
			for (Department subDepartment : department.getDepartments()) {
				CommonDepartment subDepartmentModel = transType(subDepartment);
				departments.add(subDepartmentModel);
			}
			Collections.sort(departments, new SortByDepartmentId());
			commonDepartment.setDepartments(departments);
		}
		return commonDepartment;
	}

	private List<CommonDepartment> getDepartments(CommonDepartment department) {
		List<CommonDepartment> departments = new ArrayList<CommonDepartment>();
		CommonDepartment commonDepartment = new CommonDepartment();
		BeanUtils.copyProperties(department, commonDepartment);
		commonDepartment.setDepartments(null);
		departments.add(commonDepartment);
		if (null != department.getDepartments() && !department.getDepartments().isEmpty()) {
			for (CommonDepartment tempDepartment : department.getDepartments()) {
				departments.addAll(getDepartments(tempDepartment));
			}
		}
		Collections.sort(departments, new SortByDepartmentId());
		return departments;
	}

	class SortByDepartmentId implements Comparator<CommonDepartment> {
		@Override
		public int compare(CommonDepartment newCommonDepartment, CommonDepartment oldCommonDepartment) {
			return newCommonDepartment.getDepartmentId().compareTo(oldCommonDepartment.getDepartmentId());
		}
	}

}
