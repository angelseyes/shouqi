package cn.concar.portal.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude (value =Include.NON_DEFAULT)
public class SortModel {
	private String property;
	private String direction;
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	@Override
	public String toString() {
		return "SortModel [property=" + property + ", direction=" + direction
				+ "]";
	}
	

}
