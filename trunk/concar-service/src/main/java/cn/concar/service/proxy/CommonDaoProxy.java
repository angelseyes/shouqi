package cn.concar.service.proxy;import java.util.List;

import org.apache.log4j.Logger;

import cn.concar.service.BeanFactory;
import cn.concar.service.CommonDaoService;
import cn.concar.service.db.dao.CriteriaPath;
import cn.concar.service.exception.ServiceException;

public class CommonDaoProxy {
	private static final Logger LOG = Logger.getLogger(CommonDaoProxy.class);
	private static CommonDaoService service = BeanFactory.getService(CommonDaoService.class);

	public static Integer getTotalPage(Class<?> clazz, List<CriteriaPath> params, int pageSize) throws ServiceException {
		try {
			return service.getTotalPage(clazz, params, pageSize);
		} catch (Exception e) {
			LOG.error("CommonDaoService failure!", e);
			throw new ServiceException("CommonDaoService failure!", e);
		}
	}
}
