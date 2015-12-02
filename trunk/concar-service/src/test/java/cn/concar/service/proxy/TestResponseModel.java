package cn.concar.service.proxy;

import cn.concar.service.util.StringUtils;

public class TestResponseModel<T> {
	protected String status;
	protected Integer total;
	protected String failureMsg;
	protected boolean success;

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
	
	public String getFailureMsg() {
		return failureMsg;
	}

	public void setFailureMsg(String failureMsg) {
		this.failureMsg = failureMsg;
	}

	public boolean isSuccess() {
		if (StringUtils.match("SERVICE_SUCCESS", status)){
			return true;
		} else {
			return false;
		}
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	private T data;
	
	public T getData() {
	    return data;
	}
	
	public void setData(T data) {
	    this.data = data;
	}
}
