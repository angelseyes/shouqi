package cn.concar.service.db.dao;

public class OrderUtil {

	public static final int ASC = 1;

	public static final int DESC = 0;

	private String fieldName;
	private int order;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public OrderUtil(String fieldName, int order) {
		super();
		this.fieldName = fieldName;
		this.order = order;
	}

	public OrderUtil(String fieldName, String order) {
		super();
		this.fieldName = fieldName;
		if (order.equals("ASC")) {
			this.order = ASC;
		} else if (order.equals("DESC")) {
			this.order = DESC;
		}

	}

	public boolean isAsc() {
		return order == ASC;
	}

	public boolean isDesc() {
		return order == DESC;
	}
}
