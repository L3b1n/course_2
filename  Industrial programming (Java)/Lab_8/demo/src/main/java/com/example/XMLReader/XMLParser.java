package com.example.XMLReader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.example.ObjectClasses.Battery;
import com.example.ObjectClasses.Camera;
import com.example.ObjectClasses.Device;
import com.example.ObjectClasses.Size;
import com.example.Singleton.DataBase;
import com.example.Type.Brand;
import com.example.Type.Color;

public class XMLParser {
    private static List<Device> devices;

    public void Parse(String filePath) throws ParserConfigurationException, SAXException, IOException{
        devices = new ArrayList<Device>();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        HEXParse par = new HEXParse();
        parser.parse(new File(filePath), par);
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        XMLParser Main = new XMLParser();
        Main.Parse("src\\res\\device.xml");

        for(Device id : devices) {
            System.out.println(id.toString());
        }
    }

    private static class HEXParse extends DefaultHandler {
        private Device temp = new Device();
        private String name = new String();

        private Integer counter = 0;

        @Override
        public void startElement(String uri, String me, String qName, Attributes attributes) throws SAXException {
            if(qName.equals("brand")) {

                name = attributes.getValue("name");

                if(name.equals(Brand.ASUS.toString())){
                    temp.setBrand(Brand.ASUS);
                }
                else if(name.equals(Brand.APPLE.toString())){
                    temp.setBrand(Brand.APPLE);
                }
                else if(name.equals(Brand.BLACKBERRY.toString())){
                    temp.setBrand(Brand.BLACKBERRY);
                }
                else if(name.equals(Brand.HUAWEI.toString())){
                    temp.setBrand(Brand.HUAWEI);
                }
                else if(name.equals(Brand.SAMSUNG.toString())){
                    temp.setBrand(Brand.SAMSUNG);
                }
                else if(name.equals(Brand.XIAOMI.toString())){
                    temp.setBrand(Brand.XIAOMI);
                }
                counter++;
            }

            if(qName.equals("color")) {
                String type = attributes.getValue("type");

                if(type.equals(Color.BLUE.toString())) {
                    temp.setColor(Color.BLUE);
                } else if(type.equals(Color.RED.toString())) {
                    temp.setColor(Color.RED);
                }
                counter++;
            }

            if(qName.equals("size")) {
                String height = attributes.getValue("height");
                String lenght = attributes.getValue("lenght");
                String thickness = attributes.getValue("thickness");

                temp.setSize(new Size(Double.valueOf(height),
                                      Double.valueOf(lenght),
                                      Double.valueOf(thickness)));
                counter++;
            }

            if(qName.equals("camera")) {
                String amountOfCameras = attributes.getValue("amountOfCameras");
            
                temp.setCamera(new Camera(Integer.valueOf(amountOfCameras)));

                counter++;
            }

            if(qName.equals("battery")) {
                String capacity = attributes.getValue("capacity");
                String ID = attributes.getValue("ID"); 

                temp.setBattery(new Battery(Integer.valueOf(capacity), ID));

                counter++;
            }

            if(counter == 5) {
                devices.add(new Device(temp));
                counter = 0;  
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            DataBase.getInstance(devices);
        }
    }

    public static List<Device> getDevices() {
        return devices;
    }

    public static void setDevices(List<Device> devices) {
        XMLParser.devices = devices;
    }
}