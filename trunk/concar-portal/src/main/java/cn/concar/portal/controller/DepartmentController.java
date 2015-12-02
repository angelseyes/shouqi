package cn.concar.portal.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.concar.portal.model.ResponseModel;
import cn.concar.portal.model.StateResponseModel;
import cn.concar.portal.util.PortalConstants;
import cn.concar.service.model.DepartmentModel;
import cn.concar.service.proxy.DepartmentServiceProxy;

@Controller
@RequestMapping("/department")
public class DepartmentController extends BaseController {
	private static final Logger LOG = Logger.getLogger(DepartmentController.class);

	@RequestMapping(value = "/loadDepartment", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String loadDepartment() {
		LOG.info("Inside DepartmentController.loadDepartment.");
		ResponseModel<List<DepartmentModel>> resp = new ResponseModel<List<DepartmentModel>>();
		try {
			List<DepartmentModel> out = DepartmentServiceProxy.findAllDepartments();
			resp.setData(out);
			return formatResponse(resp, null, PortalConstants.SERVICE_SUCCESS);
		} catch (Exception e) {
			LOG.error("Failed in DepartmentController.loadDepartment!", e);
			return formatFailureResponse(new StateResponseModel(), PortalConstants.SERVICE_FAILURE,
					PortalConstants.SERVICE_FAILURE_MSG + ": " + e.getMessage());
		}
	}
	
	@RequestMapping(value = "/loadSimpleDepartment", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String loadSimpleDepartment() {
		LOG.info("Inside DepartmentController.loadSimpleDepartment.");
		ResponseModel<List<DepartmentModel>> resp = new ResponseModel<List<DepartmentModel>>();
		try {
			List<DepartmentModel> out = DepartmentServiceProxy.findAllSimpleDepartments();
			resp.setData(out);
			return formatResponse(resp, null, PortalConstants.SERVICE_SUCCESS);
		} catch (Exception e) {
			LOG.error("Failed in DepartmentController.loadSimpleDepartment!", e);
			return formatFailureResponse(new StateResponseModel(), PortalConstants.SERVICE_FAILURE,
					PortalConstants.SERVICE_FAILURE_MSG + ": " + e.getMessage());
		}
	}

	@RequestMapping(value = "/loadDepartmentStat", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String loadDepartmentStat() {
		LOG.info("Inside DepartmentController.loadDepartmentStat.");
		ResponseModel<List<DepartmentModel>> resp = new ResponseModel<List<DepartmentModel>>();
		try {
			List<DepartmentModel> out = DepartmentServiceProxy.findAllDepartmentStat();
			resp.setData(out);
			return formatResponse(resp, null, PortalConstants.SERVICE_SUCCESS);
		} catch (Exception e) {
			LOG.error("Failed in DepartmentController.loadDepartmentStat!", e);
			return formatFailureResponse(new StateResponseModel(), PortalConstants.SERVICE_FAILURE,
					PortalConstants.SERVICE_FAILURE_MSG + ": " + e.getMessage());
		}
	}
}
