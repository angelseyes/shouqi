package cn.concar.service.proxy;

import java.util.List;

import org.apache.log4j.Logger;

import cn.concar.service.BeanFactory;
import cn.concar.service.DepartmentService;
import cn.concar.service.exception.ServiceException;
import cn.concar.service.model.DepartmentModel;

public class DepartmentServiceProxy {
	private static final Logger LOG = Logger.getLogger(DepartmentServiceProxy.class);

	private static DepartmentService service = BeanFactory.getService(DepartmentService.class);

	public static List<DepartmentModel> findAllDepartments() throws ServiceException {
		try {
			return service.findAllDepartments();
		} catch (Exception e) {
			LOG.error("DepartmentServiceProxy.findAllDepartments failure!", e);
			throw new ServiceException("DepartmentServiceProxy.findAllDepartments failure!", e);
		}
	}

	public static List<DepartmentModel> findAllSimpleDepartments() throws ServiceException {
		try {
			return service.findAllSimpleDepartments();
		} catch (Exception e) {
			LOG.error("DepartmentServiceProxy.findAllSimpleDepartments failure!", e);
			throw new ServiceException("DepartmentServiceProxy.findAllSimpleDepartments failure!", e);
		}
	}

	public static List<DepartmentModel> findAllDepartmentStat() throws ServiceException {
		try {
			return service.findAllDepartmentStat();
		} catch (Exception e) {
			LOG.error("DepartmentServiceProxy.findAllDepartmentStat failure!", e);
			throw new ServiceException("DepartmentServiceProxy.findAllDepartmentStat failure!", e);
		}
	}
}
