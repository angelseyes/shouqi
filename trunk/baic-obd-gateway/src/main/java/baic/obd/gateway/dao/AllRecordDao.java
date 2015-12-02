package baic.obd.gateway.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import baic.common.dao.CommonDao;
import baic.obd.gateway.dao.bean.AllRecord;
import baic.obd.gateway.model.AllRecordModel;

@Service
public class AllRecordDao {
	private static final Logger LOG = Logger.getLogger(AllRecordDao.class);
	@Autowired
	private CommonDao commonDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public void addAllRecordBatch(List<AllRecordModel> input) throws ServiceException {
		LOG.info("Inside AllRecordDao.addAllRecordBatch.");
		try {
			for (AllRecordModel oldRow : input) {
				AllRecord newRow = new AllRecord();
				BeanUtils.copyProperties(oldRow, newRow);
				if (!StringUtils.isEmpty(newRow.getImei())) {
					commonDao.save(newRow);
				}
			}
		} catch (Exception e) {
			LOG.error("Failed in AllRecordDao.addAllRecordBatch", e);
			throw new ServiceException("AllRecordDao.addAllRecordBatch failure due to unexpeted error!", e);
		}
	}
}
