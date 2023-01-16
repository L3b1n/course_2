package com.global_task.mathLogic.FileReaders;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.global_task.StringParsers.Calculation;

public class JSONFileReader extends FileReaderInfo {
    public JSONFileReader(String fileName) {
        super(fileName);
    }
    
    @Override
    public ArrayList<ArrayList<String>> Read() throws Exception {
        try {
            ArrayList<ArrayList<String>> readFile = new ArrayList<>();
            readFile.add(0, new ArrayList<>());
            JSONParser jsonParser = new JSONParser();
            JSONArray list = (JSONArray) jsonParser.parse(new FileReader(inputName));
            for(int i = 0; i < list.size(); i++) {
                JSONObject object = (JSONObject) list.get(i);
                for(int j = 0; j < object.size(); j++) {
                    readFile.get(i).add(object.get("expression" + Integer.toString(j + 1)).toString());
                }
                if(i + 1 != list.size()) {
                    readFile.add(i + 1, new ArrayList<>());
                }
            }
            return readFile;
        } catch(Exception e) {
            throw new Exception("Error in JSON file calculating. Check selected file, actions and try again.", e);
        }
    }

    @Override
    public ArrayList<ArrayList<String>> Transform(byte[] tempByte) throws Exception {
        try {
            ArrayList<ArrayList<String>> readFile = new ArrayList<>();
            readFile.add(0, new ArrayList<>());
            JSONParser jsonParser = new JSONParser();
            String tempString = new String(tempByte, StandardCharsets.UTF_8);
            JSONArray list = (JSONArray) jsonParser.parse(new StringReader(tempString));
            for(int i = 0; i < list.size(); i++) {
                JSONObject object = (JSONObject) list.get(i);
                for(int j = 0; j < object.size(); j++) {
                    readFile.get(i).add(object.get("expression" + Integer.toString(j + 1)).toString() + "\n");
                }
                if(i + 1 != list.size()) {
                    readFile.add(i + 1, new ArrayList<>());
                }
            }
            return readFile;
        } catch(Exception e) {
            throw new Exception("Error in JSON file calculating. Check selected file, actions and try again.", e);
        }
    }

    @Override
    public void Write(ArrayList<ArrayList<String>> result, String outputFileName) throws Exception {
        try {
            FileWriter writer = new FileWriter(outputFileName);
            writer.write("[\n\t");
            int i = 0;
            for(ArrayList<String> lines : result) {
                int j = 1;
                HashMap<String, String> BlockOfResults = new HashMap<>(); 
                for(String line : lines) {
                    BlockOfResults.put("Result_" + Integer.toString(j++), line);
                }
                JSONObject objectBlockOfResults = new JSONObject(BlockOfResults);
                if(i + 1 == result.size()) {
                    writer.write(objectBlockOfResults.toJSONString() + "\n");
                    break;
                }
                else {
                    writer.write(objectBlockOfResults.toJSONString() + ",\n\t");
                }
                i++;
            }
            writer.write("]");
            writer.close();
        } catch(Exception e) {
            throw new Exception("Error in JSON file calculating. Check selected file, actions and try again.", e);
        }
    }

    @Override
    public ArrayList<ArrayList<String>> Calculate(ArrayList<ArrayList<String>> readFile) throws Exception {
        ArrayList<ArrayList<String>> calculated = new ArrayList<>();
        int i = 0;
        calculated.add(0, new ArrayList<>());
        for(ArrayList<String> lines : readFile) {
            for(String line : lines) {
                String calculatedLine = Calculation.CalculationOfLine(line);
                if(!calculatedLine.equals(line)) {
                    calculated.get(i).add(calculatedLine);
                }
            }
            if(i + 1 != readFile.size() && calculated.get(i).size() != 0) {
                calculated.add(++i, new ArrayList<>());
            }
        }
        if(calculated.size() == 1) {
            throw new Exception("Error in JSON file calculating. There aren't any math expressions. Check selected file, actions and try again.");
        }
        return calculated;
    }
    
    @Override
    public void getResult(String outputFileName) throws Exception {
        ArrayList<ArrayList<String>> readFile = Read();
        ArrayList<ArrayList<String>> result = Calculate(readFile);
        Write(result, outputFileName);
    }
}