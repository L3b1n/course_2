package com.example.Fabric.Builder;

import java.io.IOException;
import org.xml.sax.SAXException;
import com.example.Singleton.DataBase;
import com.example.XMLReader.XMLParser;
import com.example.ObjectClasses.Device;
import com.example.Fabric.Builder.Interface.Builder;
import javax.xml.parsers.ParserConfigurationException;

public class Fabric {
    
    public void createDevice(Builder builder) {
        if(DataBase.Data.get(DataBase.getIterator()).getBrand() != null) {
            builder.setBrand(DataBase.Data.get(DataBase.getIterator()).getBrand());
        }
        if(DataBase.Data.get(DataBase.getIterator()).getBattery() != null) {
            builder.setBattery((DataBase.Data.get(DataBase.getIterator()).getBattery()));
        }
        if(DataBase.Data.get(DataBase.getIterator()).getCamera() != null) {
            builder.setAmountOfCameras(DataBase.Data.get(DataBase.getIterator()).getCamera());
        }
        if(DataBase.Data.get(DataBase.getIterator()).getColor() != null) {
            builder.setColor(DataBase.Data.get(DataBase.getIterator()).getColor());
        }
        if(DataBase.Data.get(DataBase.getIterator()).getSize() != null) {
            builder.setSize(DataBase.Data.get(DataBase.getIterator()).getSize());
        }

    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        Fabric Main = new Fabric();
        DeviceBuilder builder = new DeviceBuilder();
        XMLParser parser = new XMLParser();
        parser.Parse("src\\res\\device.xml");

        Main.createDevice(builder);
        Device device = builder.getResult();
        System.out.println(device.toString());
    }

}
