package baic.obd.service.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import baic.common.dao.CommonDao;
import baic.common.exception.ServiceException;
import baic.obd.model.LatestRecordModel;
import baic.obd.service.dao.bean.LatestRecord;
import baic.obd.service.idao.ILatestRecordDao;

@Service
public class LatestRecordDao implements ILatestRecordDao {
	private static final Logger LOG = Logger.getLogger(LatestRecordDao.class);
	@Autowired
	private CommonDao commonDao;

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public LatestRecordModel findLatestRecordByImei(String imei) throws ServiceException {
		LOG.info("Inside AdminDao.findAdminsByDepartmentId.");
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("imei", imei);
		try {
			List<LatestRecord> latestRecords = (List<LatestRecord>) commonDao
					.findByNamedQuery(LatestRecord.FIND_LATEST_RECORD_BY_IMEI, params);
			if (null != latestRecords && !latestRecords.isEmpty()) {
				return transType(latestRecords.get(0));
			}
			return null;
		} catch (Exception e) {
			LOG.error("Failed in AdminDao.findAdminsByDepartmentId", e);
			throw new ServiceException("AdminDao.findAdminsByDepartmentId failure due to unexpeted error!", e);
		}
	}

	private LatestRecordModel transType(LatestRecord latestRecord) {
		LatestRecordModel latestRecordModel = new LatestRecordModel();
		BeanUtils.copyProperties(latestRecord, latestRecordModel);
		return latestRecordModel;
	}
}
