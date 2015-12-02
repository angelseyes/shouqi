package cn.concar.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.concar.service.db.dao.CommonDao;
import cn.concar.service.exception.ServiceException;
import cn.concar.service.model.MonitorModel;
import cn.concar.service.model.VehicleModel;
import cn.concar.service.model.stat.KeyBigDecimalValModel;
import cn.concar.service.model.stat.KeyBigIntegerValModel;
import cn.concar.service.model.stat.VehStatusStatModel;

@Service
public class VehicleService {
	private static final Logger LOG = Logger.getLogger(VehicleService.class);
	@Autowired
	private CommonDao commonDao;
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<VehicleModel> findVehicleByDepartmentId(Long departmentId) throws ServiceException {
		LOG.info("Inside VehicleService.findVehicleByDepartmentId.");
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("departmentId", departmentId);
		try {
			return (List<VehicleModel>) commonDao.findByNativeSql(findVehicleByDepartmentId, params,
					VehicleModel.class);
		} catch (Exception e) {
			LOG.error("Failed in VehicleService.findVehicleByDepartmentId.", e);
			throw new ServiceException("VehicleService.findVehicleByDepartmentId failure due to unexpeted error!", e);
		}
	}

	private static final String findVehicleByDepartmentId = "select "
			+ "v.vehicle_id vehicleId, "
			+ "v.plate_number plateNumber, "
			+ "v.model model, "
			+ "v.color color, "
			+ "v.imei imei, "
			+ "v.registered_date registeredDate, "
			+ "l.longitude longitude, "
			+ "l.latitude latitude, "
			+ "l.location location, "
			+ "ifnull((DATE_FORMAT(l.trace_time, '%Y-%m-%d') = curdate()), 0) active "
			+ "from vehicle v "
			+ "left join latest_record l "
			+ "on v.imei = l.imei "
			+ "where v.belong_department = :departmentId "
			+ "order by v.vehicle_id desc";
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<VehicleModel> findSimpleVehicleByDepartmentId(Long departmentId) throws ServiceException {
		LOG.info("Inside VehicleService.findSimpleVehicleByDepartmentId.");
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("departmentId", departmentId);
		try {
			return (List<VehicleModel>) commonDao.findByNativeSql(findSimpleVehicleByDepartmentId, params,
					VehicleModel.class);
		} catch (Exception e) {
			LOG.error("Failed in VehicleService.findSimpleVehicleByDepartmentId.", e);
			throw new ServiceException("VehicleService.findSimpleVehicleByDepartmentId failure due to unexpeted error!", e);
		}
	}

	private static final String findSimpleVehicleByDepartmentId = "select "
			+ "v.vehicle_id vehicleId, "
			+ "v.plate_number plateNumber, "
			+ "v.model model, "
			+ "v.color color, "
			+ "v.imei imei "
			+ "from vehicle v "
			+ "where v.belong_department = :departmentId "
			+ "order by v.vehicle_id desc";

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<VehicleModel> findAllVehicles() throws ServiceException {
		LOG.info("Inside VehicleService.findAllVehicles.");
		try {
			return (List<VehicleModel>) commonDao.findByNativeSql(findAllVehicles, null, VehicleModel.class);
		} catch (Exception e) {
			LOG.error("Failed in VehicleService.findAllVehicles.", e);
			throw new ServiceException("VehicleService.findAllVehicles failure due to unexpeted error!", e);
		}
	}

	private static final String findAllVehicles = "select "
			+ "v.vehicle_id vehicleId, "
			+ "v.plate_number plateNumber, "
			+ "v.vin vin, "
			+ "v.model model, "
			+ "v.displacement displacement, "
			+ "v.color color, "
			+ "v.mileage mileage, "
			+ "v.registered_date registeredDate, "
			+ "v.valid valid, "
			+ "v.same_name sameName, "
			+ "v.key_number keyNumber, "
			+ "v.license license, "
			+ "v.certificate certificate, "
			+ "v.purchase purchase, "
			+ "v.tax tax, "
			+ "v.clivta clivta, "
			+ "v.clivta_date clivtaDate, "
			+ "v.insurance insurance, "
			+ "v.insurance_date insuranceDate, "
			+ "v.inspection_date inspectionDate, "
			+ "v.violation violation "
			+ "from vehicle v "
			+ "left join department d "
			+ "on v.belong_department = d.department_id "
			+ "order by v.vehicle_id desc";
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<MonitorModel> findAllMonitors() throws ServiceException {
		LOG.info("Inside VehicleService.findAllMonitors.");
		try {
			return (List<MonitorModel>) commonDao.findByNativeSql(findAllMonitors, null, MonitorModel.class);
		} catch (Exception e) {
			LOG.error("Failed in VehicleService.findAllMonitors.", e);
			throw new ServiceException("VehicleService.findAllMonitors failure due to unexpeted error!", e);
		}
	}

	private static final String findAllMonitors = "select "
			+ "v.vehicle_id vehicleId, "
			+ "v.plate_number plateNumber, "
			+ "dpt.department_code departmentCode, "
			+ "r.voltage voltage, "
			+ "r.voltage < 10 lackVoltage, "
			+ "r.dtc dtc, "
			+ "r.dtc is not null error, "
			+ "v.insurance_date insuranceDate, "
			+ "(to_days(curdate()) - to_days(DATE_FORMAT(v.insurance_date, '%Y-%m-%d'))) < 5 needInsurance, "
			+ "v.inspection_date inspectionDate, "
			+ "(to_days(curdate()) - to_days(DATE_FORMAT(v.inspection_date, '%Y-%m-%d'))) < 5 needInspection, "
			+ "r.trace_time traceTime, "
			+ "(to_days(curdate()) - to_days(DATE_FORMAT(r.trace_time, '%Y-%m-%d'))) sleepDayNumber, "
			+ "ifnull((DATE_FORMAT(r.trace_time, '%Y-%m-%d') < curdate()), 1) needCheck, "
			+ "!(r.voltage < 10)  "
			+ "&& isnull(dtc)  "
			+ "&& ((to_days(curdate()) - to_days(DATE_FORMAT(v.insurance_date, '%Y-%m-%d'))) < 5)  "
			+ "&& ((to_days(curdate()) - to_days(DATE_FORMAT(v.inspection_date, '%Y-%m-%d'))) < 5) "
			+ "&& (!ifnull((DATE_FORMAT(r.trace_time, '%Y-%m-%d') < curdate()), 1)) status "
			+ "from vehicle v, department dpt, latest_record r "
			+ "where v.belong_department = dpt.department_id "
			+ "and v.imei = r.imei "
			+ "order by v.vehicle_id desc";
	
	
	
	/**
	 * Query vehicle count by Department
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<KeyBigIntegerValModel> queryVehDepStat() throws ServiceException {
		LOG.info("Inside VehicleService.queryVehDepStat");
		String queryString = "select "
				+ " department_name category, count(*) value from ( "
				+ " select * from vehicle v right join department d "
				+ " on v.belong_department = d.department_id) t "
				+ " group by department_id ";
        try {
        	List<KeyBigIntegerValModel> retList = (List<KeyBigIntegerValModel>)commonDao.findByNativeSql(queryString, null, KeyBigIntegerValModel.class);
            if (null != retList && !retList.isEmpty()) {
                return retList;
            }
            return null;
        } catch (Exception e) {
            LOG.error("Failed in VehicleService.queryVehDepStat", e);
            throw new ServiceException("VehicleService.queryVehDepStat failure due to unexpeted error!", e);
        }
    }
	
	
	/**
	 * Query vehicle count by model
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<KeyBigIntegerValModel> queryVehTypeStat() throws ServiceException {
		LOG.info("Inside VehicleService.queryVehTypeStat");
		String queryString = "select "
				+ " model category, count(*) value " 
				+ " from vehicle v "
				+ " group by model ";
        try {
        	List<KeyBigIntegerValModel> retList = (List<KeyBigIntegerValModel>)commonDao.findByNativeSql(queryString, null, KeyBigIntegerValModel.class);
            if (null != retList && !retList.isEmpty()) {
                return retList;
            }
            return null;
        } catch (Exception e) {
            LOG.error("Failed in VehicleService.queryVehTypeStat", e);
            throw new ServiceException("VehicleService.queryVehTypeStat failure due to unexpeted error!", e);
        }
    }
	
	
	/**
	 * Query vehicle count by mileage
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<KeyBigIntegerValModel> queryVehMileageStat() throws ServiceException {
		LOG.info("Inside VehicleService.queryVehMileageStat");
		String queryString = "select mileageflag category, count(*) value from ( "
				+ " select v.imei,  "
				+ " CASE WHEN (v.mileage + d.mileage) < 10000 || (v.mileage + d.mileage) is null THEN '<10000' "
				+ " WHEN (v.mileage + d.mileage) >= 10000 and (v.mileage + d.mileage) < 20000 THEN '10000-20000' "
				+ " WHEN (v.mileage + d.mileage) >= 20000 and (v.mileage + d.mileage) < 30000 THEN '20000-30000' "
				+ " WHEN (v.mileage + d.mileage) >= 30000 and (v.mileage + d.mileage) < 40000 THEN '30000-40000' "
				+ " WHEN (v.mileage + d.mileage) >= 40000 and (v.mileage + d.mileage) < 50000 THEN '40000-50000' "
				+ " WHEN (v.mileage + d.mileage) >= 50000 and (v.mileage + d.mileage) < 60000 THEN '50000-60000' "
				+ " WHEN (v.mileage + d.mileage) >= 60000 and (v.mileage + d.mileage) < 70000 THEN '60000-70000' "
				+ " WHEN (v.mileage + d.mileage) >= 70000 and (v.mileage + d.mileage) < 80000 THEN '70000-80000' "
				+ " WHEN (v.mileage + d.mileage) >= 80000 and (v.mileage + d.mileage) < 90000 THEN '80000-90000' "
				+ " WHEN (v.mileage + d.mileage) >= 90000 and (v.mileage + d.mileage) < 100000 THEN '90000-100000' "
				+ " WHEN (v.mileage + d.mileage) >= 100000 THEN '>=100000' "
				+ " END AS mileageflag from vehicle v left join latest_record d "
				+ " on v.imei = d.imei) t group by mileageflag ";
        try {
        	List<KeyBigIntegerValModel> retList = (List<KeyBigIntegerValModel>)commonDao.findByNativeSql(queryString, null, KeyBigIntegerValModel.class);
            if (null != retList && !retList.isEmpty()) {
                return retList;
            }
            return null;
        } catch (Exception e) {
            LOG.error("Failed in VehicleService.queryVehMileageStat", e);
            throw new ServiceException("VehicleService.queryVehMileageStat failure due to unexpeted error!", e);
        }
    }
	
	public static final String dtcCountTag = "DTC";
	public static final String insuranceCountTag = "insurance";
	public static final String inspectionCountTag = "inspection";
	public static final String sleepCountTag = "sleep";
	public static final String normalCountTag = "normal";
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<KeyBigDecimalValModel> queryVehStatusStat() throws ServiceException {
		LOG.info("Inside VehicleService.queryVehStatusStat");
		String queryString = "select "
				+" sum(isDtc) dtcCount, "
				+" sum(isInsurance) insuranceCount, "
				+" sum(isInspection) inspectionCount, "
				+" sum(isSleep) sleepCount, "
				+" sum(!(isDtc||isInsurance||isInspection||isSleep)) normalCount from ( "
				+" select  "
				+" !isnull(d.dtc) isDtc, "
				+" ifnull(((to_days(curdate())-to_days(DATE_FORMAT(v.insurance_date,'%Y-%m-%d')))<5),0) isInsurance, "
				+" ifnull(((to_days(curdate())-to_days(DATE_FORMAT(v.inspection_date,'%Y-%m-%d')))<5),0) isInspection, "
				+" (DATE_FORMAT(d.trace_time,'%Y-%m-%d') <> curdate()) isSleep "
				+" from vehicle v left outer join latest_record d "
				+" on v.imei = d.imei) t ";                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
        try {
        	List<VehStatusStatModel> retList = (List<VehStatusStatModel>)commonDao.findByNativeSql(queryString, null, VehStatusStatModel.class);
            if (null != retList && !retList.isEmpty()) {
            	List<KeyBigDecimalValModel> flist = new ArrayList<KeyBigDecimalValModel>();
            	VehStatusStatModel model = retList.get(0);
        		
            	//dtc
            	KeyBigDecimalValModel dtcM = new KeyBigDecimalValModel();
            	if(model.getDtcCount()!=null){
            		dtcM.setValue(model.getDtcCount());
        		}else{
        			dtcM.setValue(BigDecimal.valueOf(0));
        		}
            	dtcM.setCategory(dtcCountTag);
            	flist.add(dtcM);
            	
            	//insuranceCountTag
            	KeyBigDecimalValModel insuM = new KeyBigDecimalValModel();
            	if(model.getInsuranceCount()!=null){
            		insuM.setValue(model.getInsuranceCount());
        		}else{
        			insuM.setValue(BigDecimal.valueOf(0));
        		}
            	insuM.setCategory(insuranceCountTag);
            	flist.add(insuM);
            	
            	//inspectionCountTag
            	KeyBigDecimalValModel inspM = new KeyBigDecimalValModel();
            	if(model.getInspectionCount()!=null){
            		inspM.setValue(model.getInspectionCount());
        		}else{
        			inspM.setValue(BigDecimal.valueOf(0));
        		}
            	inspM.setCategory(inspectionCountTag);
            	flist.add(inspM);
            	
            	//sleepCountTag
            	KeyBigDecimalValModel slepM = new KeyBigDecimalValModel();
            	if(model.getSleepCount()!=null){
            		slepM.setValue(model.getSleepCount());
        		}else{
        			slepM.setValue(BigDecimal.valueOf(0));
        		}
            	slepM.setCategory(sleepCountTag);
            	flist.add(slepM);
            	
            	//normalCountTag
            	KeyBigDecimalValModel normM = new KeyBigDecimalValModel();
            	if(model.getNormalCount()!=null){
            		normM.setValue(model.getNormalCount());
        		}else{
        			normM.setValue(BigDecimal.valueOf(0));
        		}
            	normM.setCategory(normalCountTag);
            	flist.add(normM);
            	
                return flist;
            }
            return null;
        } catch (Exception e) {
            LOG.error("Failed in VehicleService.queryVehStatusStat", e);
            throw new ServiceException("VehicleService.queryVehStatusStat failure due to unexpeted error!", e);
        }
    }
	
	
	
	/**
	 * Query history date
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<KeyBigIntegerValModel> queryHistoryStat(Date startDate, Date endDate, List<String> dateTypes) throws ServiceException {
		LOG.info("Inside VehicleService.queryVehMileageStat");
		String queryString = "SELECT date_format(stat_date,'%Y/%m/%d') category,stat_count value,stat_type type FROM historystat "
				+" where stat_date between :startDate and :endDate "
				+" and stat_type in (:dateTypes) order by stat_date ";
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		params.put("dateTypes", dateTypes);
        try {
        	List<KeyBigIntegerValModel> retList = (List<KeyBigIntegerValModel>)commonDao.findByNativeSql(queryString, params, KeyBigIntegerValModel.class);
            if (null != retList && !retList.isEmpty()) {
                return retList;
            }
            return null;
        } catch (Exception e) {
            LOG.error("Failed in VehicleService.queryVehMileageStat", e);
            throw new ServiceException("VehicleService.queryVehMileageStat failure due to unexpeted error!", e);
        }
    }
}