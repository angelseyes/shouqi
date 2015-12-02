package cn.concar.portal.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.concar.portal.model.ResponseModel;
import cn.concar.portal.model.StateResponseModel;
import cn.concar.portal.util.PortalConstants;
import cn.concar.service.model.DepartmentModel;
import cn.concar.service.util.StringUtils;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {
	private static final Logger LOG = Logger.getLogger(AdminController.class);

	@RequestMapping(value = "/access", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String access() {
		LOG.info("Inside AdminController.access.");
		ResponseModel<List<DepartmentModel>> resp = new ResponseModel<List<DepartmentModel>>();
		try {
			if (getSubject().isAuthenticated() || getSubject().isRemembered()) {
				if (!getSubject().isAuthenticated() && getSubject().isRemembered()) {
					getSubject().login(new UsernamePasswordToken(getCurrentUser().getUsername(),
							getCurrentUser().getPassword(), true));
				}
				return formatResponse(resp, null, PortalConstants.SERVICE_SUCCESS);
			} else {
				return formatFailureResponse(new StateResponseModel(), PortalConstants.SERVICE_FAILURE,
						PortalConstants.SERVICE_FAILURE_MSG);
			}
		} catch (AuthenticationException e) {
			LOG.error("Failed in AdminController.access!", e);
			return formatFailureResponse(new StateResponseModel(), PortalConstants.SERVICE_FAILURE, getErrorMsg(e));
		} catch (Exception e) {
			LOG.error("Failed in AdminController.access!", e);
			return formatFailureResponse(new StateResponseModel(), PortalConstants.SERVICE_FAILURE,
					PortalConstants.SERVICE_FAILURE_MSG + ": " + e.getMessage());
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String login(String username, String password, String rememberMe) {
		LOG.info("Inside AdminController.login.");
		ResponseModel<List<DepartmentModel>> resp = new ResponseModel<List<DepartmentModel>>();
		try {
			boolean remember = false;
			if (!StringUtils.isEmpty(rememberMe)) {
				remember = true;
			}
			getSubject().login(new UsernamePasswordToken(username, password, remember));
			return formatResponse(resp, null, PortalConstants.SERVICE_SUCCESS);
		} catch (AuthenticationException e) {
			LOG.error("Failed in AdminController.login!", e);
			return formatFailureResponse(new StateResponseModel(), PortalConstants.SERVICE_FAILURE, getErrorMsg(e));
		} catch (Exception e) {
			LOG.error("Failed in AdminController.login!", e);
			return formatFailureResponse(new StateResponseModel(), PortalConstants.SERVICE_FAILURE,
					PortalConstants.SERVICE_FAILURE_MSG + ": " + e.getMessage());
		}
	}

	private String getErrorMsg(AuthenticationException e) {
		if (e instanceof IncorrectCredentialsException) {
			return "密码错误";
		} else if (e instanceof UnknownAccountException) {
			return "帐号不存在";
		} else if (e instanceof LockedAccountException) {
			return "账号被锁定";
		} else {
			return "未知错误";
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String logout() {
		LOG.info("Inside AdminController.logout.");
		ResponseModel<List<DepartmentModel>> resp = new ResponseModel<List<DepartmentModel>>();
		try {
			getSubject().logout();
			return formatResponse(resp, null, PortalConstants.SERVICE_SUCCESS);
		} catch (AuthenticationException e) {
			LOG.error("Failed in AdminController.logout!", e);
			return formatFailureResponse(new StateResponseModel(), PortalConstants.SERVICE_FAILURE,
					PortalConstants.SERVICE_FAILURE_MSG + ": " + e.getMessage());
		}
	}
}
