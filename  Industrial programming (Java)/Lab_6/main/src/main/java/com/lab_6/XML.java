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

public class XML {
    private static Node getLanguage(Document doc, String firstName, String lastName, String age) {
        Element person = doc.createElement("Person");
        person.appendChild(getLanguageElements(doc, person, "firstName", firstName));
        person.appendChild(getLanguageElements(doc, person, "lastName", lastName));
        person.appendChild(getLanguageElements(doc, person, "age", age));
        return person;
    }

    private static Node getLanguageElements(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

    public static ArrayList<Person> ReadFromFileXML(String filename) throws FileNotFoundException, XMLStreamException {
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

        ArrayList<Person> result = new ArrayList<>();
        for(int i = 0; i < r.size(); i += 3) {
            result.add(new Person(r.get(i), r.get(i + 1), r.get(i + 2)));
        }
        return result;
    }

    public static void WriteInFileXML(ArrayList<Person> people) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();

        Element element = document.createElement("Persons");

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File("out_file.xml"));

        document.appendChild(element);
        element.appendChild(XML.getLanguage(document, people.get(0).getFirstName(), people.get(0).getLastName(), people.get(0).getAge()));
        for(int i = 1; i < people.size(); ++i) {
            element.appendChild(XML.getLanguage(document, people.get(i).getFirstName(), people.get(i).getLastName(), people.get(i).getAge()));
        }
        transformer.transform(source, result);
    }
}