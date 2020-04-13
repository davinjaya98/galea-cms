package com.galea.cms.webservices.bean;

public class GeneralWsResponseBean {
    
	private String returnCode;
	private Object responseObject;

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public Object getResponseObject() {
		return responseObject;
	}

	public void setResponseObject(Object responseObject) {
		this.responseObject = responseObject;
	}
}