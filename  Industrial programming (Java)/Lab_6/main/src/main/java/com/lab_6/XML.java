package com.lab_6;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class XML {
    private static Node getLanguage(Document doc, Integer X, Integer Y) {
        Element point = doc.createElement("Point");
        point.appendChild(getLanguageElements(doc, point, "X", X));
        point.appendChild(getLanguageElements(doc, point, "Y", Y));
        return point;
    }

    private static Node getLanguageElements(Document doc, Element element, String name, Integer value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(Integer.toString(value)));
        return node;
    }

    public static HashMap<Integer, Point> ReadFromXML(String filename) throws FileNotFoundException, XMLStreamException {
        ArrayList<String> r = new ArrayList<>();
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(new FileReader(filename));
        while(reader.hasNext()) {
            if(reader.next() == XMLStreamConstants.CHARACTERS) {
                String tmp = reader.getText().replaceAll("  ", "").replaceAll("\n", "");
                if(tmp != "") {
                    r.add(tmp);
                }
            }
        }

        HashMap<Integer, Point> result = new HashMap<>();
        for(int i = 0, j = 0; i < r.size(); i += 2) {
            result.put(j++, new Point(Integer.parseInt(r.get(i)), Integer.parseInt(r.get(i + 1))));
        }
        return result;
    }

    public static void WriteInFileXML(HashMap<Integer, Point> points) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();

        Element element = document.createElement("points");

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File("output.xml"));

        document.appendChild(element);
        element.appendChild(XML.getLanguage(document, points.get(0).getX(), points.get(0).getY()));
        for(int i = 1; i < points.size(); ++i) {
            element.appendChild(XML.getLanguage(document, points.get(i).getX(), points.get(i).getY()));
        }
        transformer.transform(source, result);
    }
}