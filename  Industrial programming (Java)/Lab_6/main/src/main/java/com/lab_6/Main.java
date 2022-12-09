package com.lab_6;

import java.util.*;

class Line {
    Integer x1 = 0;
    Integer y1 = 0;
    Integer x2 = 0;
    Integer y2= 0;
    double k = Double.valueOf(1);
    double b = Double.valueOf(0);
    Line(Integer _x1, Integer _y1, Integer _x2, Integer _y2) {
        x1 = _x1;
        y1 = _y1;
        x2 = _x2;
        y2 = _y2;
        k = (double)(y2 - y1) / (x2 - x1);
        b = y1 - x1 * k;
    }
}


public class Main {
    public static void main(String[] args) throws Exception {
        HashMap<Integer, Point> txt = new HashMap<>(TXT.ReadFromFileTXT("input.txt"));
        TXT.WriteInFileTXT(txt);

        HashMap<Integer, Point> json = new HashMap<>(JSON.ReadFromJSON("input.json"));
        JSON.WriteInFileJSON(json);
        
        HashMap<Integer, Point> xml = new HashMap<>(XML.ReadFromXML("input.xml"));
        XML.WriteInFileXML(xml);

        // System.out.println("--- Method 1 ---");
        // for(Point p : txt) { System.out.println(p.getFirstName() + " " + p.getLastName() + " " + p.getAge()); }
        // System.out.println("\n");

        // System.out.println("--- Method 2 ---");
        // txt.forEach(n -> System.out.println(n.getFirstName() + " " + n.getLastName() + " " + n.getAge()));
        // System.out.println("\n");

        // System.out.println("--- Method 3 ---");
        // Iterator<Point> iter = txt.iterator();
        // while(iter.hasNext()) {
        //     Point t = iter.next();
        //     System.out.println(t.getFirstName() + " " + t.getLastName() + " " + t.getAge());
        // }
    }
}
