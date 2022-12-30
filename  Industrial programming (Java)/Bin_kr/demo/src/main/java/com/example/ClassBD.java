package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class ClassBD {
    private List<Student> studentsList;
    private Map<String, Student> studMap;

    public void ReadFileInList(String path) throws IOException {
        studentsList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        while(reader.ready()) {
            int i = 0;
            String[] temp = new String[5];
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine().toString(), " ;_!");
            while(tokenizer.hasMoreTokens()) {
                temp[i++] = tokenizer.nextElement().toString();
            }
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
            int i = 0;
            String[] temp = new String[5];
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine().toString(), " ;_!");
            while(tokenizer.hasMoreTokens()) {
                temp[i++] = tokenizer.nextElement().toString();
            }
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

    public void Sort() throws IOException {
        Collections.sort(studentsList, (student1, student2) -> student1.compareTo(student2));
        WriteFile("rezsort.txt");
    }

    public void WriteFile(String path) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        for(Entry<String, Student> item : studMap.entrySet()) {
            writer.write(item.getKey() + " ");
            writer.write(item.getValue().toString() + "\n");
        }
        writer.close();
    }

    private static Node getLanguage(Document doc, String firstName, String lastName, String amount, String ID, String fileName) {
        Element line = doc.createElement("InfoOfStudent");
        line.appendChild(getLanguageElements(doc, line, "firstName", firstName));
        line.appendChild(getLanguageElements(doc, line, "lastName", lastName));
        line.appendChild(getLanguageElements(doc, line, "amount", amount));
        line.appendChild(getLanguageElements(doc, line, "ID", ID));
        line.appendChild(getLanguageElements(doc, line, "fileName", fileName));
        return line;
    }

    private static Node getLanguageElements(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

    public void WriteFileInXML() throws Throwable {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();

        Element element = document.createElement("Students");

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File("rezult.xml"));

        document.appendChild(element);
        for(int i = 0; i < studentsList.size(); ++i) {
            element.appendChild(ClassBD.getLanguage(document, studentsList.get(i).studentFirstName, studentsList.get(i).studentLastName, Double.toString(studentsList.get(i).studentAmount), studentsList.get(i).studentID, studentsList.get(i).fileName));
        }
        transformer.transform(source, result);
    }

    public List<Student> getStudentsList() {
        return studentsList;
    }

    public Map<String, Student> getStudMap() {
        return studMap;
    }
}