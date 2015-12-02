package cn.concar.service.proxy;

import org.apache.log4j.Logger;

import cn.concar.service.AdminService;
import cn.concar.service.BeanFactory;
import cn.concar.service.exception.ServiceException;
import cn.concar.service.model.AdminModel;

public class AdminServiceProxy {
	private static final Logger LOG = Logger.getLogger(AdminServiceProxy.class);

	private static AdminService service = BeanFactory.getService(AdminService.class);

	public static AdminModel findAdminByUsername(String username) throws ServiceException {
		try {
			return service.findAdminByUsername(username);
		} catch (Exception e) {
			LOG.error("AdminServiceProxy.findAdminByUsername failure!", e);
			throw new ServiceException("AdminServiceProxy.findAdminByUsername failure!", e);
		}
	}

}
