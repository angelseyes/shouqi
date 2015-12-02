package cn.concar.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.concar.service.db.dao.CommonDao;
import cn.concar.service.exception.ServiceException;
import cn.concar.service.model.ConfigModel;
import cn.concar.service.model.DeviceModel;

@Service
public class ConfigService {
	private static final Logger LOG = Logger.getLogger(ConfigService.class);
	@Autowired
	private CommonDao commonDao;

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public String findConfigValueByName(String configName) throws ServiceException {
		LOG.info("Inside ConfigService.findConfigValueByName.");
		try {
			String configValue = null;
			List<ConfigModel> configs = (List<ConfigModel>) commonDao.findByNativeSql(findConfigValueByName, null,
					DeviceModel.class);
			if (configs != null && configs.size() > 0) {
				configValue = configs.get(0).getConfigValue();
			}
			return configValue;
		} catch (Exception e) {
			LOG.error("Failed in ConfigService.findConfigValueByName.", e);
			throw new ServiceException("ConfigService.findConfigValueByName failure due to unexpeted error!", e);
		}
	}

	private static final String findConfigValueByName = "select " 
			+ "config_value configValue " 
			+ "from config c "
			+ "where c.config_name = :configName";
}
