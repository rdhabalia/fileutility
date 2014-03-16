package com.fileobj.user;

import com.fileobj.annotation.Column;


public class UserMeta {

	@Column(id=0)
	public String header;
	@Column(id=1)
	public String category;

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}



}
