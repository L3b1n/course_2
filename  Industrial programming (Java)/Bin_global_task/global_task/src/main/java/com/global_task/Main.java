package com.global_task;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;

import org.json.simple.parser.ParseException;

import com.global_task.FileReaders.JSONFileReader;
import com.global_task.FileReaders.TXTFileReader;
import com.global_task.FileReaders.XMLFileReader;

public class Main {
    // private static final String FirstKey = "test";
    // private static final String SecondKey = "test";

    private static final String TxtInputFileName = "input.txt";
    private static final String TxtOutputFileName = "output.txt";

    private static final String JsonInputFileName = "input.json";
    private static final String JsonOutputFileName = "output.json";

    private static final String XmlInputFileName = "input.xml";
    private static final String XmlOutputFileName = "output.xml";
    
    public static void main(String[] args) throws IOException, XMLStreamException, TransformerException, ParserConfigurationException, ParseException {
        TXTFileReader tempTxt = new TXTFileReader(TxtInputFileName);
        tempTxt.getResult(TxtOutputFileName);
        
        JSONFileReader tempJson = new JSONFileReader(JsonInputFileName);
        tempJson.getResult(JsonOutputFileName);
        
        XMLFileReader tempXml = new XMLFileReader(XmlInputFileName);
        tempXml.getResult(XmlOutputFileName);
    }
}