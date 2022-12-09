package com.lab_6;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

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

    public static void WriteInFileTXT(HashMap<Integer, Point> points) {
        try(FileWriter writer = new FileWriter("output.txt")) {
            for(int i = 0; i < points.size(); ++i) {
                writer.write(points.get(i).getX() + " " + points.get(i).getY() + "\n");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}