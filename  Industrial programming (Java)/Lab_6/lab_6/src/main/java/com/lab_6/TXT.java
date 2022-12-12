package com.lab_6;

import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TXT {
    private static Scanner scanner;

    public static HashMap<Integer, Point> ReadFromFileTXT(String filename) {
        HashMap<Integer, Point> points = new HashMap<>();
        try(FileReader reader = new FileReader(filename)) {
            Integer i = 0;
            scanner = new Scanner(reader);
            while(scanner.hasNextLine()) {
                points.put(i++, new Point(scanner.nextInt(), scanner.nextInt()));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return points;
    }

    public static void WriteInFileTXT(Set<Line> temp, Map<Line, Set<Point>> points) {
        try(FileWriter writer = new FileWriter("output.txt")) {
            for(int i = 0, k = 0; i < points.size(); i++) {
                writer.write("[" + new ArrayList<>(points.get(new ArrayList<>(temp).get(k))).get(0).getX() + "; " + new ArrayList<>(points.get(new ArrayList<>(temp).get(k))).get(0).getY() + "]");
                points.get(new ArrayList<>(temp).get(k)).remove(new ArrayList<>(points.get(new ArrayList<>(temp).get(k))).get(0));
                points.get(new ArrayList<>(temp).get(k)).forEach((tempPoint) -> {
                    try {
                        writer.write(" --> [" + tempPoint.getX() + "; " + tempPoint.getY() + "]");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                writer.write(" || ");
                writer.write("K = " + new ArrayList<>(temp).get(k).getK() + "; B = " + new ArrayList<>(temp).get(k).getB() + "; counter = " + new ArrayList<>(temp).get(k++).getCounter() + "\n\n");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}