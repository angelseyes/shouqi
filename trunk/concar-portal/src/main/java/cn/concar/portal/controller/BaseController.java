package cn.concar.portal.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.concar.portal.model.PaginationModel;
import cn.concar.portal.model.ResponseModel;
import cn.concar.portal.model.ResponseTreeModel;
import cn.concar.portal.model.SortModel;
import cn.concar.portal.model.StateResponseModel;
import cn.concar.portal.util.PortalConstants;
import cn.concar.service.db.dao.CriteriaPath;
import cn.concar.service.db.dao.OrderUtil;
import cn.concar.service.exception.ServiceException;
import cn.concar.service.model.AdminModel;
import cn.concar.service.proxy.CommonDaoProxy;
import cn.concar.service.util.StringUtils;

public abstract class BaseController {

	private static final Logger LOG = Logger.getLogger(BaseController.class);
	private static final String ENCODING = "UTF-8";

	protected OrderUtil[] getOrderUtil(String sort) {
		List<OrderUtil> orderUtils = new ArrayList<OrderUtil>();
		if (!StringUtils.isEmpty(sort)) {
			ObjectMapper om = new ObjectMapper();
			try {
				List<SortModel> sortModelList = om.readValue(sort, new TypeReference<List<SortModel>>() {
				});
				for (SortModel sortModel : sortModelList) {
					orderUtils.add(new OrderUtil(sortModel.getProperty(), sortModel.getDirection()));
				}
				return orderUtils.toArray(new OrderUtil[orderUtils.size()]);
			} catch (Exception e) {
				LOG.error("Can not transfer sort from JSON format to Object: " + sort, e);
				return null;
			}
		} else {
			return null;
		}
	}

	protected String formatFailureResponse(ResponseModel<?> resp, String status, String failureMsg) {
		ObjectMapper om = new ObjectMapper();
		try {
			if (status != null) {
				resp.setStatus(status);
			}
			resp.setFailureMsg(failureMsg);
			final byte[] data = om.writeValueAsBytes(resp);
			String respStr = new String(data, ENCODING);
			LOG.debug("Response: " + respStr);
			return respStr;
		} catch (Exception e) {
			LOG.error("Failed to convert the marker objects into JSON format!", e);
			return formatFailureResponse(new StateResponseModel(), PortalConstants.SERVICE_FAILURE,
					PortalConstants.JSON_FORMAT_FAILURE + ": " + e.getMessage());
		}
	}

	protected String formatFailureResponse(ResponseTreeModel<?> resp, String status, String failureMsg) {
		ObjectMapper om = new ObjectMapper();
		try {
			if (status != null) {
				resp.setStatus(status);
			}
			resp.setFailureMsg(failureMsg);
			final byte[] data = om.writeValueAsBytes(resp);
			String respStr = new String(data, ENCODING);
			LOG.debug("Response: " + respStr);
			return respStr;
		} catch (Exception e) {
			LOG.error("Failed to convert the marker objects into JSON format!", e);
			return formatFailureResponse(new StateResponseModel(), PortalConstants.SERVICE_FAILURE,
					PortalConstants.JSON_FORMAT_FAILURE + ": " + e.getMessage());
		}
	}

	protected String formatResponse(ResponseModel<?> resp, Integer totalPage, String status) {
		ObjectMapper om = new ObjectMapper();
		try {
			if (totalPage != null) {
				resp.setTotal(totalPage);
			}
			if (status != null) {
				resp.setStatus(status);
			}
			final byte[] data = om.writeValueAsBytes(resp);
			String respStr = new String(data, ENCODING);
			LOG.debug("Response: " + respStr);
			return respStr;
		} catch (Exception e) {
			LOG.error("Failed to convert the marker objects into JSON format!", e);
			return formatFailureResponse(new StateResponseModel(), PortalConstants.SERVICE_FAILURE,
					PortalConstants.JSON_FORMAT_FAILURE + ": " + e.getMessage());
		}
	}

	protected String formatResponse(ResponseTreeModel<?> resp, Integer totalPage, String status) {
		ObjectMapper om = new ObjectMapper();
		try {
			if (totalPage != null) {
				resp.setTotal(totalPage);
			}
			if (status != null) {
				resp.setStatus(status);
			}
			final byte[] data = om.writeValueAsBytes(resp);
			String respStr = new String(data, ENCODING);
			LOG.debug("Response: " + respStr);
			return respStr;
		} catch (Exception e) {
			LOG.error("Failed to convert the marker objects into JSON format!", e);
			return formatFailureResponse(new StateResponseModel(), PortalConstants.SERVICE_FAILURE,
					PortalConstants.JSON_FORMAT_FAILURE + ": " + e.getMessage());
		}
	}

	protected String formatStatResponse(Object obj) {
		ObjectMapper om = new ObjectMapper();
		try {
			final byte[] data = om.writeValueAsBytes(obj);
			String respStr = new String(data, ENCODING);
			LOG.debug("StatResponse: " + respStr);
			return respStr;
		} catch (Exception e) {
			LOG.error("Failed to convert the marker objects into JSON format!", e);
			return formatFailureResponse(new StateResponseModel(), PortalConstants.SERVICE_FAILURE,
					PortalConstants.JSON_FORMAT_FAILURE + ": " + e.getMessage());
		}
	}

	protected Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	protected AdminModel getCurrentUser() {
		Object principal = getSubject().getPrincipal();
		if (principal instanceof AdminModel) {
			return (AdminModel) principal;
		}
		return new AdminModel();
	}

	protected Integer getStartIndex(PaginationModel page) {
		return (page.getPage() - 1) * page.getLimit();

	}

	protected Integer getTotalPage(Class<?> clazz, List<CriteriaPath> params, int pageSize) throws ServiceException {
		try {
			return CommonDaoProxy.getTotalPage(clazz, params, pageSize);
		} catch (Exception e) {
			LOG.error("CommonDaoProxy failure!", e);
			throw new ServiceException("CommonDaoProxy failure!", e);
		}
	}

	protected <T> T transJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, clazz);
	}

	public <T> List<T> getObjectsFromJson(String in, Class<T> clsT) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		JsonParser parser = mapper.getFactory().createParser(in);
		JsonNode nodes = parser.readValueAsTree();
		List<T> list = new ArrayList<T>(nodes.size());
		for (JsonNode node : nodes) {
			list.add(mapper.treeToValue(node, clsT));
		}
		return list;
	}

	protected String exportFileName = "DataExport.csv";
	protected String exportSheetName = "TestData";
	protected String exportHeader = "Header";

	protected void setExportHeader(String header) {
		exportHeader = header;
	}

	protected void setExportSheetName(String name) {
		exportSheetName = name;
	}

	protected void setExportFileName(String name) {
		exportFileName = name;
	}

	protected List<String> getExportData(List<Object> list) {
		List<String> data = new ArrayList<String>();
		data.add("Data");
		return data;
	}
}
