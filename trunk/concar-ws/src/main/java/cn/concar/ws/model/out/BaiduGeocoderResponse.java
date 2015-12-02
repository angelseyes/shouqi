package cn.concar.ws.model.out;

public class BaiduGeocoderResponse {
	private Integer status;
	private BaiduGeocoder result;
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public BaiduGeocoder getResult() {
		return result;
	}
	public void setResult(BaiduGeocoder result) {
		this.result = result;
	}
	
}
