package baic.base.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import baic.base.service.idao.IDepartmentDao;
import baic.common.bean.CommonDepartment;
import baic.common.model.Response;
import baic.common.service.BaseService;
import baic.common.utils.ResponseConstants;
import baic.common.utils.ServiceConstants;

public class DepartmentService extends BaseService {

	private static final Logger LOG = Logger.getLogger(DepartmentService.class);

	@Autowired
	private IDepartmentDao departmentDao;
	
	@POST
	@Path(ServiceConstants.Base.Department.FIND_DEPARTMENT_BY_ID)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON })
	public String findDepartmentById(Long departmentId) {
		LOG.info("Inside DepartmentService.findDepartmentById.");
		Response<CommonDepartment> resp = new Response<CommonDepartment>();
		try {
			CommonDepartment department = departmentDao.findDepartmentById(departmentId);
			resp.setData(department);
			return formatResponse(resp);
		} catch (Exception e) {
			LOG.error("Failed in DepartmentService.findDepartmentById!", e);
			resp.setFailureMsg(ResponseConstants.FAILURE + ": " + e.getMessage());
			return formatResponse(resp);
		}
	}

	@POST
	@Path(ServiceConstants.Base.Department.FIND_DEPARTMENTS_BY_ID)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON })
	public String findDepartmentsById(Long departmentId) {
		LOG.info("Inside DepartmentService.findDepartmentsById.");
		Response<List<CommonDepartment>> resp = new Response<List<CommonDepartment>>();
		try {
			List<CommonDepartment> departments = departmentDao.findDepartmentsById(departmentId);
			resp.setData(departments);
			return formatResponse(resp);
		} catch (Exception e) {
			LOG.error("Failed in DepartmentService.findDepartmentById!", e);
			resp.setFailureMsg(ResponseConstants.FAILURE + ": " + e.getMessage());
			return formatResponse(resp);
		}
	}
}
