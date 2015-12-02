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
import baic.base.service.dao.bean.Vehicle;
import baic.base.service.idao.IDepartmentDao;
import baic.base.service.idao.IVehicleDao;
import baic.common.bean.CommonDepartment;
import baic.common.bean.CommonVehicle;
import baic.common.dao.CommonDao;
import baic.common.exception.ServiceException;

@Service
public class VehicleDao implements IVehicleDao {
	private static final Logger LOG = Logger.getLogger(VehicleDao.class);
	@Autowired
	private CommonDao commonDao;
	@Autowired
	private IDepartmentDao departmentDao;

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<CommonVehicle> findAllVehicles() throws ServiceException {
		LOG.info("Inside VehicleDao.findAllVehicles.");
		List<CommonVehicle> list = new ArrayList<CommonVehicle>();
		try {
			List<Vehicle> vehicles = (List<Vehicle>) commonDao.findAll(Vehicle.class, null);
			if (null != vehicles && !vehicles.isEmpty()) {
				for (Vehicle vehicle : vehicles) {
					list.add(transType(vehicle));
				}
			}
			return list;
		} catch (Exception e) {
			LOG.error("Failed in VehicleDao.findAllVehicles", e);
			throw new ServiceException("VehicleDao.findAllVehicles failure due to unexpeted error!", e);
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<CommonVehicle> findVehiclesByDepartmentId(Long departmentId) throws ServiceException {
		LOG.info("Inside VehicleDao.findVehiclesByDepartmentId.");
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("departmentId", departmentId);
		try {
			List<CommonVehicle> list = new ArrayList<CommonVehicle>();
			List<Vehicle> vehicles = (List<Vehicle>) commonDao.findByNamedQuery(Vehicle.FIND_VEHICLE_BY_DEPARTMENT_ID,
					params);
			if (null != vehicles && !vehicles.isEmpty()) {
				for (Vehicle vehicle : vehicles) {
					list.add(transType(vehicle));
				}
			}
			return list;
		} catch (Exception e) {
			LOG.error("Failed in VehicleDao.findVehiclesByDepartmentId", e);
			throw new ServiceException("VehicleDao.findVehiclesByDepartmentId failure due to unexpeted error!", e);
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<CommonVehicle> findVehiclesByAdminId(Long adminId) throws ServiceException {
		LOG.info("Inside VehicleDao.findVehiclesByAdminId.");
		List<CommonVehicle> list = new ArrayList<CommonVehicle>();
		try {
			Admin admin = (Admin) commonDao.findById(Admin.class, adminId);
			if (admin != null) {
				HashMap<String, Object> params = new HashMap<String, Object>();
				params.put("departmentId", admin.getBelongDepartment());
				List<Vehicle> vehicles = (List<Vehicle>) commonDao
						.findByNamedQuery(Vehicle.FIND_VEHICLE_BY_DEPARTMENT_ID, params);
				if (null != vehicles && !vehicles.isEmpty()) {
					for (Vehicle vehicle : vehicles)
						list.add(transType(vehicle));
				}
			}
			return list;
		} catch (Exception e) {
			LOG.error("Failed in VehicleDao.findVehiclesByDepartmentId", e);
			throw new ServiceException("VehicleDao.findVehiclesByDepartmentId failure due to unexpeted error!", e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<CommonVehicle> findSubVehiclesByDepartmentId(Long departmentId) throws ServiceException {
		LOG.info("Inside VehicleDao.findSubVehiclesByDepartmentId.");
		List<CommonVehicle> list = new ArrayList<CommonVehicle>();
		try {
			List<CommonDepartment> commonDepartments = departmentDao.findDepartmentsById(departmentId);
			for (CommonDepartment commonDepartment : commonDepartments) {
				if (DepartmentDao.departmentNameMap.get(commonDepartment.getDepartmentId()) == null) {
					DepartmentDao.departmentNameMap.put(commonDepartment.getDepartmentId(),
							commonDepartment.getDepartmentName());
				}
			}
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("idList", DepartmentDao.departmentNameMap.keySet());
			List<Vehicle> vehicles = (List<Vehicle>) commonDao.findByNamedQuery(Vehicle.FIND_VEHICLE_BY_DEPARTMENT_IDS,
					params);
			if (null != vehicles && !vehicles.isEmpty()) {
				for (Vehicle vehicle : vehicles)
					list.add(transType(vehicle));
			}
			return list;
		} catch (Exception e) {
			LOG.error("Failed in VehicleDao.findSubVehiclesByDepartmentId", e);
			throw new ServiceException("VehicleDao.findSubVehiclesByDepartmentId failure due to unexpeted error!", e);
		}
	}

	private CommonVehicle transType(Vehicle vehicle) {
		CommonVehicle vehicleModel = new CommonVehicle();
		BeanUtils.copyProperties(vehicle, vehicleModel);
		if (vehicle.getBelongDepartment() != null) {
			vehicleModel.setDepartmentName(DepartmentDao.departmentNameMap.get(vehicle.getBelongDepartment()));
		}
		return vehicleModel;
	}

}
