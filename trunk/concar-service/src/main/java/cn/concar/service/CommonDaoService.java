package cn.concar.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.concar.service.db.dao.CommonDao;
import cn.concar.service.db.dao.CriteriaPath;


@Service
public class CommonDaoService {

	private static final Logger LOG = Logger.getLogger(CommonDaoService.class);
	
	@Autowired
	private CommonDao commonDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Integer getTotalPage(Class<?> clazz, List<CriteriaPath> params, Integer pageSize) {
		LOG.info("Inside getTotalPage.");
		try {
			return commonDao.getTotalPage(clazz, params);
		} catch (Exception e) {
			LOG.error("Failed to get total page count!", e);
			return 0;
		}
	}
	
}
