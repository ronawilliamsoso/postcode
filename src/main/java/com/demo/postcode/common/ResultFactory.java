package com.demo.postcode.common;


public class ResultFactory{

	public static <T> Result<T> getInstance(int code, T data, String msg) {

		return new Result<T>(code, data, msg);
	}

	public static <T> Result<T> getInstance(T data) {

		return new Result<T>(200, data, null);
	}

	public static <T> Result<T> getInstance() {

		return new Result<T>(200, null, "success");
	}

	public static <T> Result<T> getFailInstance(String message) {

		return new Result<T>(201, null, message);
	}

	public static <T> Result<T> getInstance(int code, String message) {

		return new Result<T>(code, null, message);
	}
}
