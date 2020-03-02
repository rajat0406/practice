package com.extramarks.journeybuilder.service;

import com.extramarks.journeybuilder.model.StageModel;
import com.extramarks.journeybuilder.repository.StageRepository;
import com.extramarks.journeybuilder.util.JSONReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Service
public class FlowService{
    private final JSONReader reader = new JSONReader();
    
    @Autowired
    private StageRepository stageRepository;

    public StageModel getStage(String stage) {
        return stageRepository.findByContentName(stage);
    }

    public List<StageModel> getStages() {
        return stageRepository.findAll();
    }

    public void setStages(List<StageModel> stages) {
        stageRepository.saveAll(stages);
    }

    public List<String> predictNextStages(String stage, String action) {
        return reader.getActionStages(stage, action);
    }

    public String getNextStage(String stage, String action) {
        return reader.getNextStage(stage, action);
    }

    public StageModel addStage(StageModel stage) {
        return stageRepository.save(stage);
    }

    public List<String> getStageActions(@RequestParam(name = "stage") String stage) {
        return reader.getActions(stage);
    }

    public boolean deleteStage(String stageName) {
        StageModel stage = getStage(stageName);
        if (stage == null) 
        	return false;
        stageRepository.deleteById(stage.getId());
        return true;
    }

    public StageModel updateStage(StageModel newStage) {
        return stageRepository.save(newStage);
    } 	

    public List<String> getJSONStages() {
        return reader.getStages();
    }

    public List<StageModel> getFlow() {
    	System.out.println("++++++++++"+stageRepository.findAll());
        return stageRepository.findAll();
        
    }

    public Map<String, List<String>> getResponse(String stage) {
        return reader.getResponse(stage);
    }
}
