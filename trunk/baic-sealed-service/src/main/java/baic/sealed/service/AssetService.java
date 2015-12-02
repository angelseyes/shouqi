package baic.sealed.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;

import baic.common.bean.CommonDepartment;
import baic.common.bean.CommonVehicle;
import baic.common.client.ClientFactory;
import baic.common.model.Response;
import baic.common.model.TreeResponse;
import baic.common.service.BaseService;
import baic.common.utils.ResponseConstants;
import baic.common.utils.ServiceConstants;
import baic.sealed.service.model.AssetDepartment;
import baic.sealed.service.model.AssetVehicle;
import baic.sealed.service.utils.SealedConstants;

public class AssetService extends BaseService {

	private static final Logger LOG = Logger.getLogger(AssetService.class);

	@SuppressWarnings("unchecked")
	@GET
	@Path("/findVehicles")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON })
	public String findVehicles(@QueryParam("deptId") String departmentId, @QueryParam("callback") String callback) {
		LOG.info("Inside AssetService.findVehicles.");
		Response<List<AssetVehicle>> resp = new Response<List<AssetVehicle>>();
		try {
			WebClient baseClient = ClientFactory
					.getClient(ServiceConstants.Base.Vehicle.Url.FIND_SUB_VEHICLES_BY_DEPARTMENT_ID_URL);
			String respStr = baseClient.post(departmentId, String.class);
			Response<?> response = readResponse(respStr, new TypeReference<Response<List<CommonVehicle>>>() {
			});
			if (!response.isSuccess() || null == response.getData()) {
				return formatCallBackResponse(resp, callback);
			}
			List<CommonVehicle> commonVehicles = (List<CommonVehicle>) response.getData();
			if (null != commonVehicles && !commonVehicles.isEmpty()) {
				List<AssetVehicle> assetVehicles = new ArrayList<AssetVehicle>();
				for (CommonVehicle commonVehicle : commonVehicles) {
					assetVehicles.add(transType(commonVehicle));
				}
				resp.setData(assetVehicles);
			}
			return formatCallBackResponse(resp, callback);
		} catch (Exception e) {
			LOG.error("Failed in AssetService.findVehicles!", e);
			resp.setFailureMsg(ResponseConstants.FAILURE + ": " + e.getMessage());
			return formatCallBackResponse(resp, callback);
		}
	}

	@GET
	@Path("/findDepartments")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON })
	public @ResponseBody String findDepartments(@QueryParam("deptId") String departmentId,
			@QueryParam("callback") String callback) {
		LOG.info("Inside AssetService.findDepartments.");
		LOG.info("departmentId: " + departmentId);
		LOG.info("callback: " + callback);
		TreeResponse<AssetDepartment> resp = new TreeResponse<AssetDepartment>();
		try {
			WebClient baseClient = ClientFactory
					.getClient(ServiceConstants.Base.Department.Url.FIND_DEPARTMENT_BY_ID_URL);
			String respStr = baseClient.post(Long.parseLong(departmentId), String.class);
			Response<?> response = readResponse(respStr, new TypeReference<Response<CommonDepartment>>() {
			});
			if (!response.isSuccess() || null == response.getData()) {
				return formatCallBackResponse(resp, callback);
			}

			CommonDepartment commonDepartment = (CommonDepartment) response.getData();
			if (commonDepartment != null) {
				resp.setChildren(transType(commonDepartment));
			}
			return formatCallBackResponse(resp, callback);
		} catch (Exception e) {
			LOG.error("Failed in AllMapService.findDepartments!", e);
			resp.setFailureMsg(ResponseConstants.FAILURE + ": " + e.getMessage());
			return formatCallBackResponse(resp, callback);
		}
	}

	private AssetDepartment transType(CommonDepartment commonDepartment) {
		AssetDepartment assetDepartment = new AssetDepartment();
		BeanUtils.copyProperties(commonDepartment, assetDepartment);
		if (null != commonDepartment.getDepartments() && !commonDepartment.getDepartments().isEmpty()) {
			List<AssetDepartment> departments = new ArrayList<AssetDepartment>();
			for (CommonDepartment subDepartment : commonDepartment.getDepartments()) {
				AssetDepartment subDepartmentModel = transType(subDepartment);
				departments.add(subDepartmentModel);
			}
			Collections.sort(departments, new SortByDepartmentId());
			assetDepartment.setChildren(departments);
			assetDepartment.setLeaf(null);
			assetDepartment.setIconCls(SealedConstants.FOLDER_ICON);
		} else {
			assetDepartment.setExpanded(null);
			assetDepartment.setLeaf(Boolean.TRUE);
			assetDepartment.setIconCls(SealedConstants.LEAF_ICON);
		}
		return assetDepartment;
	}

	class SortByDepartmentId implements Comparator<AssetDepartment> {
		@Override
		public int compare(AssetDepartment newAssetDepartment, AssetDepartment oldAssetDepartment) {
			return newAssetDepartment.getDepartmentId().compareTo(oldAssetDepartment.getDepartmentId());
		}
	}

	private AssetVehicle transType(CommonVehicle commonVehicle) {
		AssetVehicle assetVehicle = new AssetVehicle();
		BeanUtils.copyProperties(commonVehicle, assetVehicle);
		return assetVehicle;
	}
}
