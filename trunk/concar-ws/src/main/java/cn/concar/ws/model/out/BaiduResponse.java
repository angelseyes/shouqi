package cn.concar.ws.model.out;

public class BaiduResponse {
	private Integer status;
	private BaiduCoordinate[] result;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BaiduCoordinate[] getResult() {
		return result;
	}

	public void setResult(BaiduCoordinate[] result) {
		this.result = result;
	}

}
