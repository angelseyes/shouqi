package cn.concar.portal.model;

public abstract class ResponseStatModel<T> extends ResponseModel<T> {
	
	protected Integer start;

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}
}
