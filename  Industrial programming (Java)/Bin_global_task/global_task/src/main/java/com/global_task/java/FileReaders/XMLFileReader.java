package com.global_task.java.FileReaders;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.global_task.java.StringParsers.Calculation;

public class XMLFileReader extends FileReaderInfo {
    public XMLFileReader(String fileName) {
        super(fileName);
    }

    private static Node getLanguageElements(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
    
    private static Node getLanguage(Document doc, ArrayList<String> result) {
        Element line = doc.createElement("expressions");
        for(int i = 0, k = 1; i < result.size(); i++) {
            line.appendChild(getLanguageElements(doc, line, "Result" + Integer.toString(k++), result.get(i)));
        }
        return line;
    }

    // @Override
    public ArrayList<ArrayList<String>> Read() throws IOException, XMLStreamException {
        ArrayList<ArrayList<String>> readFile = new ArrayList<>();
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(new FileReader(inputName));
        int i = 0, counter = 0;
        readFile.add(0, new ArrayList<>());
        while(reader.hasNext()) {
            if(reader.next() == XMLStreamConstants.CHARACTERS) {
                String tmp = reader.getText().replaceAll("  ", "").replaceAll("\n", "");
                if(tmp != "") {
                    readFile.get(i).add(tmp);
                    counter = 0;
                } else {
                    counter++;
                }
            } 
            if(counter == 3) {
                readFile.add(++i, new ArrayList<>());
                counter = 0;
            }
        }
        return readFile;
    }
    
    // @Override
    public void Write(ArrayList<ArrayList<String>> result, String outputFileName) throws IOException, TransformerException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        
        builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();
        
        Element element = document.createElement("ExpressionList");
        
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File(outputFileName));

        document.appendChild(element);
        for(int i = 0; i < result.size() - 1; i++) {
            element.appendChild(XMLFileReader.getLanguage(document, result.get(i)));
        }
        transformer.transform(source, streamResult);
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
    public void getResult(String outputFileName) throws IOException, XMLStreamException, TransformerException, ParserConfigurationException {
        ArrayList<ArrayList<String>> readFile = Read();
        ArrayList<ArrayList<String>> result = Calculate(readFile);
        Write(result, outputFileName);
    }
}