package com.lab_6;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.Test;

public class MainTest 
{
    @Test
    public void TestTxt()
    {
        HashMap<Integer, Point> txt = new HashMap<>();
        txt.put(0, new Point(1, 1));
        txt.put(1, new Point(2, 2));
        txt.put(2, new Point(3, 3));
        Map<Line, Set<Point>> answer = new TreeMap<>(new CompareKoefLines());
        Map<Line, Set<Point>> Txt = new TreeMap<>(new CompareKoefLines());
        Set<Line> lineTxt = new TreeSet<>(new CompareConstLines());
        Set<Point> test = new TreeSet<>(new ComparePoint());
        test.addAll(Arrays.asList(new Point(1, 1), new Point(2, 2), new Point(3,3)));
        Txt.put(new Line(1.0, 0.0, 4), test);
        Main.Solution(txt, answer, lineTxt);
        assertEquals(Txt.get(new Line(1.0, 0.0, 4)), answer.get(new Line(1.0, 0.0, 4)));
    }
    
    @Test
    public void TestJson()
    {
        HashMap<Integer, Point> json = new HashMap<>();
        json.put(0, new Point(1, 1));
        json.put(1, new Point(2, 2));
        json.put(2, new Point(3, 3));
        Map<Line, Set<Point>> answer = new TreeMap<>(new CompareKoefLines());
        Map<Line, Set<Point>> Json = new TreeMap<>(new CompareKoefLines());
        Set<Line> lineJson = new TreeSet<>(new CompareConstLines());
        Set<Point> test = new TreeSet<>(new ComparePoint());
        test.addAll(Arrays.asList(new Point(1, 1), new Point(2, 2), new Point(3,3)));
        Json.put(new Line(1.0, 0.0, 4), test);
        Main.Solution(json, answer, lineJson);
        assertEquals(Json.get(new Line(1.0, 0.0, 4)), answer.get(new Line(1.0, 0.0, 4)));
    }
    
    @Test
    public void TestXml()
    {
        HashMap<Integer, Point> xml = new HashMap<>();
        xml.put(0, new Point(1, 1));
        xml.put(1, new Point(2, 2));
        xml.put(2, new Point(3, 3));
        Map<Line, Set<Point>> answer = new TreeMap<>(new CompareKoefLines());
        Map<Line, Set<Point>> Xml = new TreeMap<>(new CompareKoefLines());
        Set<Line> lineXml = new TreeSet<>(new CompareConstLines());
        Set<Point> test = new TreeSet<>(new ComparePoint());
        test.addAll(Arrays.asList(new Point(1, 1), new Point(2, 2), new Point(3,3)));
        Xml.put(new Line(1.0, 0.0, 4), test);
        Main.Solution(xml, answer, lineXml);
        assertEquals(Xml.get(new Line(1.0, 0.0, 4)), answer.get(new Line(1.0, 0.0, 4)));
    }
}
