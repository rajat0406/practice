package com.extramarks.journeybuilder.response;

import io.swagger.annotations.ApiModelProperty;

public class ResponseModel implements Response {
	
	@ApiModelProperty(notes="status of the operation. 0 - Failed | 1 - Successful")
	private int status;
	
	@ApiModelProperty(notes="Response Message.")
    private String message;
    
	@ApiModelProperty(notes="Response Object if any")
    private Object object;

	public int getStatus() {
		return status;
	}

	public ResponseModel setStatus(int status) {
		this.status = status;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public ResponseModel setMessage(String message) {
		this.message = message;
		return this;
	}

	public Object getObject() {
		return object;
	}

	public ResponseModel setObject(Object object) {
		this.object = object;
		return this;
	}
	
	@Override
	public String toString() {
		return "ResponseModel [status=" + status + ", message=" + message + ", object=" + object + "]";
	}
    

}
