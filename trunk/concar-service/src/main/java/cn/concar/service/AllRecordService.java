package cn.concar.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.concar.service.db.bean.AllRecord;
import cn.concar.service.db.dao.CommonDao;
import cn.concar.service.exception.ServiceException;
import cn.concar.service.model.AllRecordModel;

@Service
public class AllRecordService {
	private static final Logger LOG = Logger.getLogger(AllRecordService.class);
	@Autowired
	private CommonDao commonDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public void addAllRecordBatch(List<AllRecordModel> input) throws ServiceException {
		LOG.info("Inside VehTnTService.addVehTnTBatch.");
		try {
			for (AllRecordModel oldRow : input) {
				AllRecord newRow = new AllRecord();
				BeanUtils.copyProperties(oldRow, newRow);
				commonDao.save(newRow);
			}
		} catch (Exception e) {
			LOG.error("Failed in AllRecordService.addAllRecordBatch", e);
			throw new ServiceException("AllRecordService.addAllRecordBatch failure due to unexpeted error!", e);
		}
	}
}
