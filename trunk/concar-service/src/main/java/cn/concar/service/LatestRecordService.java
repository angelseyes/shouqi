package cn.concar.service;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.concar.service.db.dao.CommonDao;
import cn.concar.service.exception.ServiceException;
import cn.concar.service.model.LatestRecordModel;

@Service
public class LatestRecordService {
	private static final Logger LOG = Logger.getLogger(LatestRecordService.class);
	@Autowired
	private CommonDao commonDao;

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public LatestRecordModel findLatestRecordByImei(String imei) throws ServiceException {
		LOG.info("Inside LatestRecordService.findLatestRecordByImei.");
		LatestRecordModel ret = new LatestRecordModel();
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("imei", imei);
		try {
			List<LatestRecordModel> records = (List<LatestRecordModel>) commonDao
					.findByNativeSql(findLatestRecordByImei, params, LatestRecordModel.class);
			if (records != null && records.size() > 0) {
				ret = records.get(0);
			}
			return ret;
		} catch (Exception e) {
			LOG.error("Failed in LatestRecordService.findLatestRecordByImei.", e);
			throw new ServiceException("LatestRecordService.findLatestRecordByImei failure due to unexpeted error!", e);
		}
	}

	private static final String findLatestRecordByImei = "select "
			+ "l.imei imei, "
			+ "l.longitude longitude, "
			+ "l.latitude latitude, "
			+ "l.voltage voltage, "
			+ "l.rpm rpm, "
			+ "l.speed speed, "
			+ "l.mileage mileage, "
			+ "l.temperature temperature, "
			+ "l.trace_time traceTime, "
			+ "!isnull(l.dtc) error "
			+ "from latest_record l "
			+ "where l.imei = :imei";

}
