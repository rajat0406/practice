package com.extramarks.journeybuilder.response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Api(value = "StageModel")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class StageResponse implements Response {
    @ApiModelProperty(notes = "Response Message")
    private String stage;

    @ApiModelProperty(notes = "Response Code 1 is successful and 0 is failed")
    private int status;

    @ApiModelProperty(notes = "Response Object if any")
    private Object actions;
    
    

    public String getStage() {
		return stage;
	}



	public StageResponse setStage(String stage) {
		this.stage = stage;
		return this;
	}



	public int getStatus() {
		return status;
	}



	public StageResponse setStatus(int status) {
		this.status = status;
		return this;
	}



	public Object getActions() {
		return actions;
	}



	public StageResponse setActions(Object actions) {
		this.actions = actions;
		return this;
	}



	@Override
    public String toString() {
        return "StageResponse{" +
                "stage='" + stage + '\'' +
                ", status=" + status +
                ", actions=" + actions +
                '}';
    }
}
