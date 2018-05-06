package com.cmf.base;


import com.cmf.exception.enums.HandlerExceptionEnums;

import java.io.Serializable;


public class ResponseModel implements Serializable {
    private static final long serialVersionUID = 3644322851500370651L;

    private Integer result = 200;

    private String message = "ok";

    private Object data;

    public ResponseModel() {
    }
    
    public ResponseModel(Integer result, String message) {
        this.result = result;
        this.message = message;
    }
    
    public ResponseModel(String message) {
        this.result = 0;
        this.message = message;
    }
    
    public ResponseModel(HandlerExceptionEnums exceptionEnums) {
        this.result = exceptionEnums.getCode();
        this.message = exceptionEnums.getMessage();
    }
    
    public ResponseModel(Object data) {
        this.data = data;
    }



	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
