package com.fileobj.user;

import com.fileobj.annotation.Column;


public class UserSummary {

	@Column(id=0)
	public String Summary;
	@Column(id=1)
	public Integer total;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getSummary() {
		return Summary;
	}

	public void setSummary(String summary) {
		Summary = summary;
	}




}