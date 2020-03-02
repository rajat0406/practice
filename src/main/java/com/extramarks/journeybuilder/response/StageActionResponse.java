package com.extramarks.journeybuilder.response;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Api(value = "StageActionResponse")
@AllArgsConstructor @NoArgsConstructor
public class StageActionResponse implements Response {
    @ApiModelProperty(notes = "Response Code 1 is successful and 0 is failed")
    private int status;
    private String currentStage;
    private String selectedAction;
    private String nextStage;
    private List<String> actions;
    
    

    /**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}



	/**
	 * @param status the status to set
	 */
	public StageActionResponse setStatus(int status) {
		this.status = status;
		return this;
	}



	/**
	 * @return the currentStage
	 */
	public String getCurrentStage() {
		return currentStage;
	}



	/**
	 * @param currentStage the currentStage to set
	 */
	public StageActionResponse setCurrentStage(String currentStage) {
		this.currentStage = currentStage;
		return this;
	}



	/**
	 * @return the selectedAction
	 */
	public String getSelectedAction() {
		return selectedAction;
	}



	/**
	 * @param selectedAction the selectedAction to set
	 */
	public StageActionResponse setSelectedAction(String selectedAction) {
		this.selectedAction = selectedAction;
		return this;
	}



	/**
	 * @return the nextStage
	 */
	public String getNextStage() {
		return nextStage;
	}



	/**
	 * @param nextStage the nextStage to set
	 */
	public StageActionResponse setNextStage(String nextStage) {
		this.nextStage = nextStage;
		return this;
	}



	/**
	 * @return the actions
	 */
	public List<String> getActions() {
		return actions;
	}



	/**
	 * @param actions the actions to set
	 */
	public StageActionResponse setActions(List<String> actions) {
		this.actions = actions;
		return this;
	}



	@Override
    public String toString() {
        return "StageActionResponse{" +
                "status=" + status +
                ", currentStage='" + currentStage + '\'' +
                ", selectedAction='" + selectedAction + '\'' +
                ", nextStage='" + nextStage + '\'' +
                ", actions=" + actions +
                '}';
    }
}