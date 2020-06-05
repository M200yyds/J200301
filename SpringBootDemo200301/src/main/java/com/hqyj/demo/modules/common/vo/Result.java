package com.hqyj.demo.modules.common.vo;

public class Result<T> {
	
	private Integer status;
	private String message;
	private T object;
	
	public Result() {
	}
	
	public Result(Integer status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public Result(Integer status, String message, T object) {
		super();
		this.status = status;
		this.message = message;
		this.object = object;
	}


	@Override
	public String toString() {
		return "Result [status=" + status + ", message=" + message + ", object=" + object + "]";
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getObject() {
		return object;
	}
	public void setObject(T object) {
		this.object = object;
	}
	
	public enum ResultStatus{
		SUCCESS(200),FAIED(500);
		public Integer status;
		private ResultStatus(Integer status) {
			this.status = status;
		}
	}
	
}
