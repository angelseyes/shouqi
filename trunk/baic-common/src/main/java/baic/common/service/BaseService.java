package baic.common.service;

import org.apache.cxf.common.util.StringUtils;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import baic.common.model.Response;
import baic.common.model.TreeResponse;
import baic.common.utils.LZString;

public abstract class BaseService {

	private static final Logger LOG = Logger.getLogger(BaseService.class);

	protected String formatResponse(Response<?> resp) {
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			if (!StringUtils.isEmpty(resp.getFailureMsg())) {
				resp.setSuccess(false);
			} else {
				resp.setSuccess(true);
			}
			String respStr = om.writeValueAsString(resp);
			LOG.debug("respStr: " + respStr);
			return respStr;
		} catch (Exception e) {
			LOG.error("Failed to convert the marker objects into JSON format!", e);
			return null;
		}
	}

	protected String formatResponse(TreeResponse<?> resp) {
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			if (!StringUtils.isEmpty(resp.getFailureMsg())) {
				resp.setSuccess(false);
			} else {
				resp.setSuccess(true);
				resp.setExpanded(Boolean.TRUE);
			}
			String respStr = om.writeValueAsString(resp);
			LOG.debug("respStr: " + respStr);
			return respStr;
		} catch (Exception e) {
			LOG.error("Failed to convert the marker objects into JSON format!", e);
			return null;
		}
	}

	protected String formatCallBackResponse(Response<?> resp, String callBackName) {
		return callBackName + "(" + formatResponse(resp) + ")";
	}

	protected String formatCallBackResponse(TreeResponse<?> resp, String callBackName) {
		return callBackName + "(" + formatResponse(resp) + ")";
	}

	protected Response<?> readResponse(String respStr, TypeReference<?> type) {
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			Response<?> resp = om.readValue(respStr, type);
			return resp;
		} catch (Exception e) {
			LOG.error("Failed to convert the JSON format into marker objects!", e);
			return null;
		}
	}

	protected <T> T readObject(String data, Class<T> valueType) {
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			// Decompress.
			data = LZString.decompressFromEncodedURIComponent(data.replaceAll("\"", ""));
			T t = om.readValue(data, valueType);
			return t;
		} catch (Exception e) {
			LOG.error("Failed to convert the JSON format into marker objects!", e);
			return null;
		}
	}
}
