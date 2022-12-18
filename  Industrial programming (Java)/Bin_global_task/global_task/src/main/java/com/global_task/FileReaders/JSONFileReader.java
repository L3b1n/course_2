package com.global_task.FileReaders;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.global_task.StringParsers.Calculation;

public class JSONFileReader extends FileReaderInfo {
    public JSONFileReader(String fileName) {
        super(fileName);
    }
    
    // @Override
    public ArrayList<ArrayList<String>> Read() throws IOException, ParseException {
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
    }

    // @Override
    public void Write(ArrayList<ArrayList<String>> result, String outputFileName) throws IOException {
        FileWriter writer = new FileWriter(outputFileName);
        writer.write("[\n\t");
        int i = 0;
        for(ArrayList<String> lines : result) {
            int j = 1;
            HashMap<String, String> BlockOfResults = new HashMap<>(); 
            for(String line : lines) {
                BlockOfResults.put("Result" + Integer.toString(j++), line);
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
    }

    // @Override
    public ArrayList<ArrayList<String>> Calculate(ArrayList<ArrayList<String>> readFile) throws IOException {
        ArrayList<ArrayList<String>> calculated = new ArrayList<>();
        int i = 0;
        calculated.add(0, new ArrayList<>());
        for(ArrayList<String> lines : readFile) {
            for(String line : lines) {
                String calculatedLine = Calculation.CalculationOfLine(line);
                calculated.get(i).add(calculatedLine);
            }
            if(i + 1 != readFile.size()) {
                calculated.add(++i, new ArrayList<>());
            }
        }
        return calculated;
    }
    
    // @Override
    public void getResult(String outputFileName) throws IOException, ParseException {
        ArrayList<ArrayList<String>> readFile = Read();
        ArrayList<ArrayList<String>> result = Calculate(readFile);
        Write(result, outputFileName);
    }
}
