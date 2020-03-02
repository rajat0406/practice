package com.extramarks.journeybuilder.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.extramarks.journeybuilder.model.StageModel;
import com.extramarks.journeybuilder.response.Response;
import com.extramarks.journeybuilder.response.ResponseModel;
import com.extramarks.journeybuilder.response.StageActionResponse;
import com.extramarks.journeybuilder.response.StageResponse;
import com.extramarks.journeybuilder.service.FlowService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(value = "*")
@Api(value = "Flow management", tags = "flow management")
public class FlowController {
	
    @Autowired
    private FlowService flowService;

    @ApiOperation(value = "Saves a stage")
    @PostMapping(value = "/saveStage")
    public ResponseEntity<ResponseModel> saveStage(@RequestParam String stage, @RequestParam String action) {
    	
        if (stage.isEmpty() || action.isEmpty()) {
        	return new ResponseEntity<>(
        			new ResponseModel()
        			.setStatus(0)
        			.setMessage("one of the fields are empty")
        			.setObject(null)
        			, HttpStatus.OK);
        }
        return new ResponseEntity<>(
    			new ResponseModel()
    			.setStatus(1)
    			.setMessage("Successfully Saved")
    			.setObject(flowService.addStage(new StageModel(stage, action)))
    			, HttpStatus.OK);
    }

    @ApiOperation(value = "Stages from the json file")
    @GetMapping(value = "/stages")
    public ResponseEntity<ResponseModel> getStages() {
    	
        List<String> stages = flowService.getJSONStages();
        return new ResponseEntity<>(
    			new ResponseModel()
    			.setStatus(1)
    			.setMessage("Stages are")
    			.setObject(stages)
    			, HttpStatus.OK);
    }
    
    @ApiOperation(value = "Show actions at a stage")
    @GetMapping(value = "/stageActions")
    public ResponseEntity<Response> getStageActions(@RequestParam String stage) {
        List<String> actions = flowService.getStageActions(stage);
        if (actions.isEmpty()) {
            return new ResponseEntity<>(new ResponseModel()
            		.setMessage("It is a final state")
            		.setObject(null)
            		.setStatus(0)
            		, HttpStatus.OK);
    }
        return new ResponseEntity<>(
        		new StageResponse()
        		.setActions(actions)
        		.setStatus(1)
        		.setStage(stage)
        		, HttpStatus.OK);
    }

    @ApiOperation(value = "Get next stage at selected action (test.json)")
    @GetMapping(value = "/nextStage")
    public ResponseEntity<Response> getNextStage(@RequestParam String stage, @RequestParam String action) {
        String nextStage = flowService.getNextStage(stage, action);
        if (nextStage == null || nextStage.isEmpty()) {
            return new ResponseEntity<>(new ResponseModel()
            		.setMessage("It is a final state")
            		.setStatus(0)
            		.setObject(null)
            		, HttpStatus.OK);
        }
        Map<String, List<String>> response = flowService.getResponse(nextStage);
        
        if (response.get(nextStage).isEmpty()) {
            return new ResponseEntity<>(new StageActionResponse()
            		.setStatus(0)
            		.setCurrentStage(stage)
            		.setNextStage(nextStage)
            		.setSelectedAction(action)
            		.setActions(null)          		
            		, HttpStatus.OK);  
        }
        return new ResponseEntity<>(new StageActionResponse()
        		.setStatus(1)
        		.setCurrentStage(stage)
        		.setSelectedAction(action)
        		.setNextStage(nextStage)
        		.setActions(response.get(nextStage))
        		, HttpStatus.OK);
    }

    @ApiOperation(value = "Deletes a stage")
    @DeleteMapping(value = "/deleteStage")
    public ResponseEntity<ResponseModel> deleteStage(@RequestParam String stage) {
        return flowService.deleteStage(stage) ? new ResponseEntity<>(new ResponseModel()
        		.setMessage("Successfully Deleted")
        		.setStatus(1)
        		.setObject(true), HttpStatus.OK) : new ResponseEntity<>(new ResponseModel()
        				.setMessage("No such Stage!")
        				.setObject(false)
        				.setStatus(0)
        				, HttpStatus.OK);
    }

    @ApiOperation(value = "Displays the created flow")
    @GetMapping(value = "/flow")
    public ResponseEntity<ResponseModel> getFlow() {
    	System.out.println("controller+++++++++flowCreated"+ flowService.getFlow() );
        List<StageModel> flow = flowService.getFlow();
        if (flow.isEmpty()) {
        	return new ResponseEntity<>(new ResponseModel()
        			.setMessage("No stages Present!")
        			.setObject(Collections.emptyList())
        			.setStatus(0)
        			, HttpStatus.OK);
        }
        	return new ResponseEntity<>(new ResponseModel()
        			.setMessage("Flow created is")
        			.setStatus(1)
        			.setObject(flow)
        			, HttpStatus.OK);
    }

}
