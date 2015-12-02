package baic.base.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;

import baic.base.service.idao.IAdminDao;
import baic.common.bean.CommonAdmin;
import baic.common.bean.CommonLogin;
import baic.common.model.Response;
import baic.common.service.BaseService;
import baic.common.utils.EncodeUtils;
import baic.common.utils.ResponseConstants;
import baic.common.utils.ServiceConstants;

public class AdminService extends BaseService {

	private static final Logger LOG = Logger.getLogger(DepartmentService.class);

	@Autowired
	private IAdminDao adminDao;

	@GET
	@Path(ServiceConstants.Base.Admin.FIND_ALL_ADMINS)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON })
	public String findAllAdmins() {
		LOG.info("Inside AdminService.findAllAdmins.");
		Response<List<CommonAdmin>> resp = new Response<List<CommonAdmin>>();
		try {
			List<CommonAdmin> admins = adminDao.findAllAdmins();
			resp.setData(admins);
			return formatResponse(resp);
		} catch (Exception e) {
			LOG.error("Failed in AdminService.findAllAdmins!", e);
			resp.setFailureMsg(ResponseConstants.FAILURE + ": " + e.getMessage());
			return formatResponse(resp);
		}
	}

	@POST
	@Path(ServiceConstants.Base.Admin.FIND_ADMINS_BY_DEPARTMENT_ID)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON })
	public String findAdminsByDepartmentId(Long departmentId) {
		LOG.info("Inside AdminService.findAdminsByDepartmentId.");
		Response<List<CommonAdmin>> resp = new Response<List<CommonAdmin>>();
		try {
			List<CommonAdmin> admins = adminDao.findAdminsByDepartmentId(departmentId);
			resp.setData(admins);
			return formatResponse(resp);
		} catch (Exception e) {
			LOG.error("Failed in AdminService.findAdminsByDepartmentId!", e);
			resp.setFailureMsg(ResponseConstants.FAILURE + ": " + e.getMessage());
			return formatResponse(resp);
		}
	}

	@GET
	@Path(ServiceConstants.Base.Admin.ACCESS)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON })
	public String access(@QueryParam("callback") String callback) {
		LOG.info("Inside AdminService.access.");
		Response<CommonAdmin> resp = new Response<CommonAdmin>();
		try {
			if (getSubject().isAuthenticated() || getSubject().isRemembered()) {
				CommonAdmin admin = getAdmin();
				if (!getSubject().isAuthenticated() && getSubject().isRemembered()) {
					getSubject().login(
							new UsernamePasswordToken(admin.getUsername(), EncodeUtils.md5(admin.getPassword()), true));
				}
				resp.setData(admin);
				return formatCallBackResponse(resp, callback);
			} else {
				resp.setFailureMsg(ResponseConstants.FAILURE);
				return formatCallBackResponse(resp, callback);
			}
		} catch (AuthenticationException e) {
			LOG.error("Failed in AdminService.access!", e);
			resp.setFailureMsg(ResponseConstants.FAILURE + ": " + getErrorMsg(e));
			return formatCallBackResponse(resp, callback);
		} catch (Exception e) {
			LOG.error("Failed in AdminService.access!", e);
			resp.setFailureMsg(ResponseConstants.FAILURE + ": " + e.getMessage());
			return formatCallBackResponse(resp, callback);
		}
	}

	@GET
	@Path(ServiceConstants.Base.Admin.LOGIN)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON })
	public @ResponseBody String login(@QueryParam("data") String data, @QueryParam("callback") String callback) {
		LOG.info("Inside AdminService.login.");
		LOG.info("data: " + data);
		LOG.info("callback: " + callback);
		Response<CommonAdmin> resp = new Response<CommonAdmin>();
		try {
			CommonLogin commonLogin = readObject(data, CommonLogin.class);
			boolean remember = false;
			if (StringUtils.isEmpty(commonLogin.getRememberMe())) {
				remember = true;
			}
			getSubject().login(new UsernamePasswordToken(commonLogin.getUsername(),
					EncodeUtils.md5(commonLogin.getPassword()), remember));
			CommonAdmin admin = getAdmin();
			admin.setPassword(null);
			resp.setData(admin);
			return formatCallBackResponse(resp, callback);
		} catch (AuthenticationException e) {
			LOG.error("Failed in AdminService.login!", e);
			resp.setFailureMsg(ResponseConstants.FAILURE + ": " + getErrorMsg(e));
			return formatCallBackResponse(resp, callback);
		} catch (Exception e) {
			LOG.error("Failed in AdminService.login!", e);
			resp.setFailureMsg(ResponseConstants.FAILURE + ": " + e.getMessage());
			return formatCallBackResponse(resp, callback);
		}
	}

	@GET
	@Path(ServiceConstants.Base.Admin.LOGOUT)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON })
	public String logout(@QueryParam("callback") String callback) {
		LOG.info("Inside AdminService.logout.");
		Response<CommonAdmin> resp = new Response<CommonAdmin>();
		try {
			getSubject().logout();
			return formatCallBackResponse(resp, callback);
		} catch (AuthenticationException e) {
			LOG.error("Failed in AdminService.logout!", e);
			resp.setFailureMsg(ResponseConstants.FAILURE + ": " + e.getMessage());
			return formatCallBackResponse(resp, callback);
		}
	}

	@POST
	@Path(ServiceConstants.Base.Admin.SAVE)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON })
	public String save(CommonAdmin commonAdmin, @QueryParam("callback") String callback) {
		LOG.info("Inside AdminService.save.");
		Response<Long> resp = new Response<Long>();
		try {
			if (adminDao.findAdminByUsername(commonAdmin.getUsername()) != null) {
				resp.setFailureMsg(ResponseConstants.SAVE_USERNAME_EXISTS);
				return formatCallBackResponse(resp, callback);
			}
			commonAdmin.setPassword(EncodeUtils.md5(commonAdmin.getPassword()));
			Long id = adminDao.saveAdmin(commonAdmin);
			resp.setData(id);
			return formatCallBackResponse(resp, callback);
		} catch (AuthenticationException e) {
			LOG.error("Failed in AdminService.save!", e);
			resp.setFailureMsg(ResponseConstants.FAILURE + ": " + getErrorMsg(e));
			return formatCallBackResponse(resp, callback);
		} catch (Exception e) {
			LOG.error("Failed in AdminService.save!", e);
			resp.setFailureMsg(ResponseConstants.FAILURE + ": " + e.getMessage());
			return formatCallBackResponse(resp, callback);
		}
	}

	private String getErrorMsg(AuthenticationException e) {
		if (e instanceof IncorrectCredentialsException) {
			return ResponseConstants.LOGIN_INCORRECT_CREDENTIAL;
		} else if (e instanceof UnknownAccountException) {
			return ResponseConstants.LOGIN_UNKNOWN_ACCOUNT;
		} else if (e instanceof LockedAccountException) {
			return ResponseConstants.LOGIN_LOCKED_ACCOUNT;
		} else {
			return ResponseConstants.LOGIN_UNKNOWN;
		}
	}

	private Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	protected CommonAdmin getAdmin() {
		Object principal = getSubject().getPrincipal();
		if (principal instanceof CommonAdmin) {
			return (CommonAdmin) principal;
		}
		return new CommonAdmin();
	}
}
