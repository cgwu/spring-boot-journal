package com.apress.spring.service;

import java.io.Serializable;

public class OperationResult implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private boolean succeed;
	private String msg;

	public OperationResult() {
	}

	public OperationResult(boolean succeed, String msg) {
		super();
		this.succeed = succeed;
		this.msg = msg;
	}

	public boolean isSucceed() {
		return succeed;
	}

	public void setSucceed(boolean succeed) {
		this.succeed = succeed;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OperationResult [succeed=");
		builder.append(succeed);
		builder.append(", msg=");
		builder.append(msg);
		builder.append("]");
		return builder.toString();
	}

}
