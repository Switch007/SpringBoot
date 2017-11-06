package com.switch007.model;

public class ResultModel {

	public int code;
	public String info;
	public Object data;

	public ResultModel(int code, String info, Object o) {
		this.code = code;
		this.info = info;
		this.data = o;
	}

	public static ResultModel success(String info, Object o) {
		return new ResultModel(0, info, o);

	}
	public static ResultModel fail(String info, Object o) {
		return new ResultModel(-1, info, o);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
