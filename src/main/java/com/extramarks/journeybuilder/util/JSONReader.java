package com.extramarks.journeybuilder.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JSONReader {
    private static final Logger logger = Logger.getLogger("myLogger");
    private static final String FILE_PATH = "src/main/java/com/extramarks/journeybuilder/jsonFile/test.json";
    private static Map<String, List<String>> map;

    static {
        try {
            readJSON();
        } catch (Exception h) {
            logger.log(Level.FINE, h.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static void readJSON() throws IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader(FILE_PATH));
        map = new ObjectMapper().readValue(((JSONObject) obj).toJSONString(), HashMap.class);
    }

    public List<String> getStages() {
        return new ArrayList<>(map.keySet());
    }

    @SuppressWarnings("unchecked")
    public List<String> getActions(String stageName) {
        if (stageName.equals("Done")) return Collections.emptyList();
        Map<String, List<String>> response = (Map<String, List<String>>) map.get(stageName);
        return new ArrayList<>(response.keySet());
    }

    @SuppressWarnings("unchecked")
    public List<String> getActionStages(String stageName, String actionName) {
        Map<String, List<String>> response = (Map<String, List<String>>) map.get(stageName);
        return response.get(actionName);
    }

    @SuppressWarnings("unchecked")
    public String getNextStage(String stageName, String actionName) {
        if (stageName.equals("Done")) return "It is a final stage!";
        Map<String, String> response = (Map<String, String>) map.get(stageName);
        return response.get(actionName);
    }

    public Map<String, List<String>> getResponse(String stage) {
        Map<String, List<String>> stagesMap = new HashMap<>();
        stagesMap.put(stage, getActions(stage));
        return stagesMap;
    }
}
