package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ClassBD {
    private List<Student> studentsList;
    private Map<String, Student> studMap;

    public void ReadFileInList(String path) throws IOException {
        studentsList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        while(reader.ready()) {
            String[] temp = reader.readLine().split(" ");
            String studentFirstName = temp[0];
            String studentLastName = temp[1];
            Double studentAmount = Double.valueOf(temp[2]);
            String studentID = temp[3];
            String fileName = temp[4];
            
            studentsList.add(new Student(studentFirstName, studentLastName, studentAmount, studentID, fileName));
        }
        reader.close();
    }

    public void ReadFileInMap(String path) throws IOException {
        ReadFileInList(path);
        studMap = new HashMap<String, Student>();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        while(reader.ready()) {
            String[] temp = reader.readLine().split(" ");
            String studentFirstName = temp[0];
            String studentLastName = temp[1];
            Double studentAmount = Double.valueOf(temp[2]);
            String studentID = temp[3];
            String fileName = temp[4];
            studMap.put(studentID, new Student(studentFirstName, studentLastName, studentAmount, "temp", fileName));
            studMap.size();
        }
        reader.close();
        WriteFile("rezult1.txt");
    }

    public void deleteName() throws IOException {
        for(int i = 0; i < studentsList.size(); i++) {
            boolean hasDigits = false;
            String word = new String(studentsList.get(i).studentFirstName);
            String wordId = new String(studentsList.get(i).studentID);
            for(int j = 0; j < word.length() && !hasDigits; i++) {
                if(Character.isDigit(word.charAt(i))) {
                    hasDigits = true;
                }
            }
            if(hasDigits) {
                studMap.remove(wordId);
            }
        }
        WriteFile("rezdel.txt");
    }

    public void WriteFile(String path) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        for(Entry<String, Student> item : studMap.entrySet()) {
            writer.write(item.getKey() + " ");
            writer.write(item.getValue().toString() + "\n");
        }
        writer.close();
    }

    public void ReadFileInListWithXML(String path) throws IOException {
        WriteFile("src/res/rezult1.txt");
    }

    public List<Student> getStudentsList() {
        return studentsList;
    }

    public Map<String, Student> getStudMap() {
        return studMap;
    }
}