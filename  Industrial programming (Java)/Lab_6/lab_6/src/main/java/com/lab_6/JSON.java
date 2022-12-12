package com.lab_6;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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
                Point temp_point = new Point(X, Y);
                points.put(i, temp_point);
            }
        } catch (IOException | org.json.simple.parser.ParseException ex) {
            throw new RuntimeException(ex);
        }
        return points;
    }

    public static void WriteInFileJSON(Set<Line> temp, Map<Line, Set<Point>> points) throws IOException {
        FileWriter writer = new FileWriter("output.json");
        writer.write("[\n\t");
        for(int i = 0, k = 0; i < points.size(); i++) {
            HashMap<String, Integer> testInteger1 = new HashMap<>();
            testInteger1.put("X", new ArrayList<>(points.get(new ArrayList<>(temp).get(k))).get(0).getX());
            testInteger1.put("Y", new ArrayList<>(points.get(new ArrayList<>(temp).get(k))).get(0).getY());
            JSONObject objectInt1 = new JSONObject(testInteger1);
            writer.write(objectInt1.toJSONString() + ", ");
            points.get(new ArrayList<>(temp).get(k)).remove(new ArrayList<>(points.get(new ArrayList<>(temp).get(k))).get(0));
            points.get(new ArrayList<>(temp).get(k)).forEach(tempPoint -> {
                HashMap<String, Integer> testInteger2 = new HashMap<>();
                testInteger2.put("X", tempPoint.getX());
                testInteger2.put("Y", tempPoint.getY());    
                JSONObject objectInt2 = new JSONObject(testInteger2);
                try {
                    writer.write(objectInt2.toJSONString() + ", ");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            HashMap<String, Double> testDouble = new HashMap<>();
            testDouble.put("K = ", new ArrayList<>(temp).get(k).getK());
            testDouble.put("B = ", new ArrayList<>(temp).get(k).getB());
            testDouble.put("Counter = ", (double) new ArrayList<>(temp).get(k++).getCounter());
            JSONObject objectDouble = new JSONObject(testDouble);
            if(i + 1 == points.size()) {
                writer.write(objectDouble.toJSONString() + "\n");
                break;
            }
            else {
                writer.write(objectDouble.toJSONString() + ",\n\t");
            }
        }
        writer.write("]");
        writer.close();
    }
}