package com.lab_6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

public class XML {
    private static Node getLanguagePoint(Document doc, Integer X, Integer Y) {
        Element point = doc.createElement("Point");
        point.appendChild(getLanguageElementsPoint(doc, point, "X", X));
        point.appendChild(getLanguageElementsPoint(doc, point, "Y", Y));
        return point;
    }

    private static Node getLanguageElementsPoint(Document doc, Element element, String name, Integer value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(Integer.toString(value)));
        return node;
    }
    
    private static Node getLanguageLine(Document doc, Double K, Double B, Integer Counter) {
        Element line = doc.createElement("InfoOfLine");
        line.appendChild(getLanguageElementsLine(doc, line, "K", K));
        line.appendChild(getLanguageElementsLine(doc, line, "B", B));
        line.appendChild(getLanguageElementsPoint(doc, line, "Counter", Counter));
        return line;
    }
    
    private static Node getLanguageElementsLine(Document doc, Element element, String name, Double value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(Double.toString(value)));
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

    public static void WriteInFileXML(Set<Line> temp, Map<Line, Set<Point>> points) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();

        Element element = document.createElement("Lines");

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File("output.xml"));

        document.appendChild(element);
        for(int i = 0, k = 0; i < points.size(); ++i) {
            points.get(new ArrayList<>(temp).get(k)).forEach((tempPoint) -> {
                element.appendChild(XML.getLanguagePoint(document, tempPoint.getX(), tempPoint.getY()));
            });
            element.appendChild(XML.getLanguageLine(document, new ArrayList<>(temp).get(k).getK(), new ArrayList<>(temp).get(k).getB(), new ArrayList<>(temp).get(k++).getCounter()));
        }
        transformer.transform(source, result);
    }
}