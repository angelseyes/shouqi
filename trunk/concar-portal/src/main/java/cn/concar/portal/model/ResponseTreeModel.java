package cn.concar.portal.model;

import cn.concar.portal.util.PortalConstants;
import cn.concar.service.util.StringUtils;

public abstract class ResponseTreeModel<T> {
	protected String status;
	protected Integer total;
	protected String failureMsg;
	protected boolean success;
	protected boolean expanded;

	public String getFailureMsg() {
		return failureMsg;
	}

	public void setFailureMsg(String failureMsg) {
		this.failureMsg = failureMsg;
	}

	public boolean isSuccess() {
		if (StringUtils.match(PortalConstants.SERVICE_SUCCESS, status)) {
			return true;
		} else {
			return false;
		}
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public abstract T getChildren();

	public abstract void setChildren(T children);

}
