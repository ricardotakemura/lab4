package com.sensedia.poc.call.bean;

import java.io.Serializable;

public class CallBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String url;

	private Integer statusCode;

	public CallBean(Long id, String url, Integer statusCode) {
		this.id = id;
		this.url = url;
		this.statusCode = statusCode;
	}

	public CallBean() {
		this.id = null;
		this.url = "";
		this.statusCode = null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	@Override
	public String toString() {
		return "CallBean [id=" + id + ", url=" + url + ", statusCode=" + statusCode + "]";
	}
}
