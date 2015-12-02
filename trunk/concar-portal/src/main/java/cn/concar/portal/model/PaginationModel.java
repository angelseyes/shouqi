package cn.concar.portal.model;

public class PaginationModel {
	private Integer page;
	private Integer start;
	private Integer limit;
	private String sort;
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	@Override
	public String toString() {
		return "PaginationModel [page=" + page + ", start=" + start
				+ ", limit=" + limit + ", sort=" + sort + "]";
	}
	

}
