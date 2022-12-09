package com.lab_6;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSON {
    
    public static HashMap<Integer, Point> ReadFromJSON(String filename) {
        HashMap<Integer, Point> points = new HashMap<>();
        JSONParser jsonParser = new JSONParser();
        try {
            JSONArray list = (JSONArray) jsonParser.parse(new FileReader(filename));
            for(int i = 0; i < list.size(); ++i) {
                JSONObject object = (JSONObject) list.get(i);
                Integer X = Integer.parseInt(object.get("X: ").toString());
                Integer Y = Integer.parseInt(object.get("Y: ").toString());
                // Integer Y = (Integer) object.get("Y: ");
                Point temp_point = new Point(X, Y);
                points.put(i, temp_point);
            }
        } catch (IOException | org.json.simple.parser.ParseException ex) {
            throw new RuntimeException(ex);
        }
        return points;
    }

    public static void WriteInFileJSON(HashMap<Integer, Point> temp) throws IOException {
        FileWriter writer = new FileWriter("output.json");
        writer.write("[ ");
        for(int i = 0; i < temp.size(); ++i) {
            if(i + 1 == temp.size()) {
                JSONObject object = new JSONObject();
                object.put("X: ", temp.get(i).getX());
                object.put("Y: ", temp.get(i).getY());
                writer.write(object.toJSONString());
                break;
            }
            JSONObject object = new JSONObject();
            object.put("X: ", temp.get(i).getX());
            object.put("Y: ", temp.get(i).getY());
            writer.write(object.toJSONString() + ", ");
        }
        writer.write(" ]");
        writer.close();
    }
}
