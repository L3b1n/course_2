package com.example;

import java.io.FileWriter;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import com.example.Fabric.Builder.DeviceBuilder;
import com.example.Fabric.Builder.Fabric;
import com.example.Fabric.Decorator.DecFabric;
import com.example.Fabric.Decorator.Decorator;
import com.example.ObjectClasses.Device;
import com.example.XMLReader.XMLParser;
import com.example.Singleton.DataBase;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        XMLParser parser = new XMLParser();
        parser.Parse("src/res/device.xml");
        FileWriter writer = new FileWriter("src/res/output.txt");

        writer.write("Builder part:\n\n");

        Fabric Main = new Fabric();
        DeviceBuilder builder = new DeviceBuilder();

        Device[] device = new Device[DataBase.Data.size()];

        for(Device dev : device) {
            Main.createDevice(builder);
            dev = new Device(builder.getResult());
            DataBase.IncreaseIterator();
            writer.write(dev.toString() + "\n");
        }

        writer.write("\nDecorator part:\n\n");

        DecFabric fabric = new DecFabric();
        Decorator dec;
       
        Device[] devices = new Device[DataBase.Data.size()];

        for(Device dev : devices) {
            dec = fabric.createDevice(); 
            dec.setPart();
            dev = Decorator.getDevice();
            writer.write(dev.toString() + "\n");
        }
        writer.close();
    }
}
