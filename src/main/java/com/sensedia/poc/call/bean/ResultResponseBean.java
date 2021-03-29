package com.sensedia.poc.call.bean;

public class ResultResponseBean {

	private Object result;
	private Status status;

	public ResultResponseBean(Object result, Status status) {
		this.result = result;
		this.status = status;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
