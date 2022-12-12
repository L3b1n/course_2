package com.lab_6;

import java.util.Set;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.jar.JarEntry;
import java.util.zip.ZipEntry;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipOutputStream;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;

import java.util.HashMap;
import java.security.Key;
import java.util.Iterator;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.io.OutputStream;
import java.util.Comparator;
import java.io.FileInputStream;
import java.io.FileOutputStream;

class Line {
    Integer counter = 0;
    double k = Double.valueOf(1);
    double b = Double.valueOf(0);

    Line(Integer _x1, Integer _y1, Integer _x2, Integer _y2) {
        this.k = (double)(_y2 - _y1) / (_x2 - _x1);
        this.b = _y1 - _x1 * k;
        this.counter = 2;
    }
    
    Line(Double _k, Double _b, Integer _counter) {
        this.k = _k;
        this.b = _b;
        this.counter = _counter;
    }
    
    public boolean Check(Line temp) {
        if(temp == this) {
            return true;
        }
        return this.getB().equals(temp.getB()) && this.getK().equals(temp.getK());
    }
    
    Double getK() {
        return k;
    }
    
    Double getB() {
        return b;
    }
    
    Integer getCounter() {
        return counter;
    }
    
    void setCounter(Integer _counter) {
        this.counter = _counter;
    }
}

class CompareKoefLines implements Comparator<Line> {
    public int compare(Line temp1, Line temp2) {
        return temp1.getK().compareTo(temp2.getK());
    }
}

class CompareConstLines implements Comparator<Line> {
    public int compare(Line temp1, Line temp2) {
        return temp1.getB().compareTo(temp2.getB());
    }
}

class ComparePoint implements Comparator<Point> {
    public int compare(Point temp1, Point temp2) {
        return temp1.getX().compareTo(temp2.getX());
    }
}


public class Main {
    public static void Solution(HashMap<Integer, Point> txt, Map<Line, Set<Point>> answerTxt, Set<Line> lineTemp) {
        for(int i = 0; i < txt.size(); i++) {
            for(int j = i + 1; j < txt.size(); j++) {
                Line temp = new Line(txt.get(i).getX(), txt.get(i).getY(), txt.get(j).getX(), txt.get(j).getY());
                lineTemp.add(temp);
                Set<Point> test = new TreeSet<>(new ComparePoint());
                test.add(txt.get(i));
                test.add(txt.get(j));
                Boolean tempBool = false;
                Set<Line> Set = answerTxt.keySet();
                for(Line tempSet : Set) {
                    if(tempSet.Check(temp)) { 
                        tempBool = true;
                        break;
                    }
                }
                if(tempBool) {
                    test.addAll(answerTxt.get(temp));
                    for(Line x : lineTemp){ 
                        if(x.Check(temp)) {
                            temp.setCounter(x.getCounter() + 1);
                            x.setCounter(temp.getCounter());
                            break;
                        }
                    }
                    answerTxt.remove(temp);
                }
                answerTxt.put(temp, test);
            }
        }
    }

    public static void main(String[] args) throws Throwable {
        HashMap<Integer, Point> txt = new HashMap<>(TXT.ReadFromFileTXT("input.txt"));
        Map<Line, Set<Point>> answerTxt = new TreeMap<>(new CompareKoefLines());
        Set<Line> lineTxtTemp = new TreeSet<>(new CompareConstLines());
        Solution(txt, answerTxt, lineTxtTemp);
        TXT.WriteInFileTXT(lineTxtTemp, answerTxt);
        System.out.println("--- Jast for ---");
        for(int i = 0, k = 0; i < answerTxt.size(); i++) {
            System.out.print("[" + new ArrayList<>(answerTxt.get(new ArrayList<>(lineTxtTemp).get(k))).get(0).getX() + "; " + new ArrayList<>(answerTxt.get(new ArrayList<>(lineTxtTemp).get(k))).get(0).getY() + "]");
            answerTxt.get(new ArrayList<>(lineTxtTemp).get(k)).remove(new ArrayList<>(answerTxt.get(new ArrayList<>(lineTxtTemp).get(k))).get(0));
            answerTxt.get(new ArrayList<>(lineTxtTemp).get(k)).forEach((tempPoint) -> {
                System.out.print(" --> [" + tempPoint.getX() + "; " + tempPoint.getY() + "]");
            });
            System.out.println(" || K = " + new ArrayList<>(lineTxtTemp).get(k).getK() + "; B = " + new ArrayList<>(lineTxtTemp).get(k).getB() + "; counter = " + new ArrayList<>(lineTxtTemp).get(k++).getCounter());
        }
        System.out.println("\n");

        HashMap<Integer, Point> json = new HashMap<>(JSON.ReadFromJSON("input.json"));
        Map<Line, Set<Point>> answerJson = new TreeMap<>(new CompareKoefLines());
        Set<Line> lineJsonTemp = new TreeSet<>(new CompareConstLines());
        Solution(json, answerJson, lineJsonTemp);
        JSON.WriteInFileJSON(lineJsonTemp, answerJson);
        System.out.println("--- Jast forEach ---");
        for(int i = 0, k = 0; i < answerJson.size(); i++) {
            System.out.print("[" + new ArrayList<>(answerJson.get(new ArrayList<>(lineJsonTemp).get(k))).get(0).getX() + "; " + new ArrayList<>(answerJson.get(new ArrayList<>(lineJsonTemp).get(k))).get(0).getY() + "]");
            answerJson.get(new ArrayList<>(lineJsonTemp).get(k)).remove(new ArrayList<>(answerJson.get(new ArrayList<>(lineJsonTemp).get(k))).get(0));
            answerJson.get(new ArrayList<>(lineJsonTemp).get(k)).forEach((tempPoint) -> {
                System.out.print(" --> [" + tempPoint.getX() + "; " + tempPoint.getY() + "]");
            });
            System.out.println(" || K = " + new ArrayList<>(lineJsonTemp).get(k).getK() + "; B = " + new ArrayList<>(lineJsonTemp).get(k).getB() + "; counter = " + new ArrayList<>(lineJsonTemp).get(k++).getCounter());
        }
        System.out.println("\n");
        
        HashMap<Integer, Point> xml = new HashMap<>(XML.ReadFromXML("input.xml"));
        Map<Line, Set<Point>> answerXml = new TreeMap<>(new CompareKoefLines());
        Set<Line> lineXmlTemp = new TreeSet<>(new CompareConstLines());
        Solution(xml, answerXml, lineXmlTemp);
        XML.WriteInFileXML(lineXmlTemp, answerXml);
        System.out.println("--- Jast Iterator ---");
        for(int i = 0; i < answerJson.size(); i++) {
            System.out.print("[" + new ArrayList<>(answerXml.get(new ArrayList<>(lineXmlTemp).get(i))).get(0).getX() + "; " + new ArrayList<>(answerXml.get(new ArrayList<>(lineXmlTemp).get(i))).get(0).getY() + "]");
            answerXml.get(new ArrayList<>(lineXmlTemp).get(i)).remove(new ArrayList<>(answerXml.get(new ArrayList<>(lineXmlTemp).get(i))).get(0));
            Iterator<Point> iter = answerXml.get(new ArrayList<>(lineXmlTemp).get(i)).iterator();
            while(iter.hasNext()) {
                Point t = iter.next();
                System.out.print(" --> [" + t.getX() + "; " + t.getY() + "]");
            }
            System.out.println(" || K = " + new ArrayList<>(lineXmlTemp).get(i).getK() + "; B = " + new ArrayList<>(lineXmlTemp).get(i).getB() + "; counter = " + new ArrayList<>(lineXmlTemp).get(i).getCounter());
        }
        System.out.println("\n");

        String key = "squirrel123";

        FileInputStream fis = new FileInputStream("output.txt");
        FileOutputStream fos = new FileOutputStream("encrypted.txt");
        encrypt(key, fis, fos);
        
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream("output.zip"));
        FileInputStream fileInputStreamTxt = new FileInputStream("output.txt");
        ZipEntry zipEntryTxt = new ZipEntry("output.txt");
        zipOutputStream.putNextEntry(zipEntryTxt);
        zipOutputStream.write(fileInputStreamTxt.readAllBytes());
        zipOutputStream.closeEntry();

        FileInputStream fileInputStreamXml = new FileInputStream("output.xml");
        ZipEntry zipEntryXml = new ZipEntry("output.xml");
        zipOutputStream.putNextEntry(zipEntryXml);
        zipOutputStream.write(fileInputStreamXml.readAllBytes());
        zipOutputStream.closeEntry();
        zipOutputStream.close();
        
        JarOutputStream jarOutputStream = new JarOutputStream(new FileOutputStream("output.jar"));
        FileInputStream fileInputStreamJson = new FileInputStream("output.json");
        JarEntry jarEntryJson = new JarEntry("output.json");
        jarOutputStream.putNextEntry(jarEntryJson);
        jarOutputStream.write(fileInputStreamJson.readAllBytes());
        jarOutputStream.closeEntry();
        jarOutputStream.close();
        
        fileInputStreamTxt.close();
        fileInputStreamJson.close();
        fileInputStreamXml.close();
    }

    public static void encrypt(String key, InputStream is, OutputStream os) throws Throwable {
        encryptOrDecrypt(key, Cipher.ENCRYPT_MODE, is, os);
    }

    public static void encryptOrDecrypt(String key, int mode, InputStream is, OutputStream os) throws Throwable {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("Blowfish");
        keyGenerator.init(128);
        Key secretKey = keyGenerator.generateKey();
        Cipher cipher = Cipher.getInstance("Blowfish/CFB/NoPadding");

        if(mode == Cipher.ENCRYPT_MODE) {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            CipherInputStream cis = new CipherInputStream(is, cipher);
            doCopy(cis, os);
        } else if (mode == Cipher.DECRYPT_MODE) {
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            CipherOutputStream cos = new CipherOutputStream(os, cipher);
            doCopy(is, cos);
        }
    }

    public static void doCopy(InputStream is, OutputStream os) throws IOException {
        byte[] bytes = new byte[64];
        int numBytes;
        while((numBytes = is.read(bytes)) != -1) {
            os.write(bytes, 0, numBytes);
        }
        os.flush();
        os.close();
        is.close();
    }
}