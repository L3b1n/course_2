package com.example.Fabric.Decorator;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import com.example.Fabric.Decorator.ClassesDecorators.AmountOfCamerasDecorator;
import com.example.Fabric.Decorator.ClassesDecorators.BatteryDecorator;
import com.example.Fabric.Decorator.ClassesDecorators.BrandDecorator;
import com.example.Fabric.Decorator.ClassesDecorators.ColorDecorator;
import com.example.Fabric.Decorator.ClassesDecorators.MainComponents;
import com.example.Fabric.Decorator.ClassesDecorators.SizeDecorator;
import com.example.ObjectClasses.Device;
import com.example.XMLReader.XMLParser;
import com.example.Singleton.DataBase;

public class DecFabric {
    public Decorator createDevice() {
        MainComponents components = new MainComponents();

        BrandDecorator brandDecorator = null;
        ColorDecorator colorDecorator = null;
        BatteryDecorator batteryDecorator = null;
        SizeDecorator sizeDecorator = null;
        AmountOfCamerasDecorator amountOfCamerasDecorator = null;

        if(DataBase.Data.get(DataBase.getIterator()).getBrand() != null) {
            brandDecorator = new BrandDecorator(components);
        }
        if(DataBase.Data.get(DataBase.getIterator()).getColor() != null) {
            colorDecorator = new ColorDecorator(brandDecorator);
        }
        if(DataBase.Data.get(DataBase.getIterator()).getBattery() != null) {
            batteryDecorator = new BatteryDecorator(colorDecorator);
        }
        if(DataBase.Data.get(DataBase.getIterator()).getSize() != null) {
            sizeDecorator = new SizeDecorator(batteryDecorator);
        }
        if(DataBase.Data.get(DataBase.getIterator()).getCamera() != null) {
            amountOfCamerasDecorator = new AmountOfCamerasDecorator(sizeDecorator);
        }
        return new Decorator(amountOfCamerasDecorator);
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        XMLParser parser = new XMLParser();
        parser.Parse("src\\res\\device.xml");
        DecFabric fabric = new DecFabric();
        Decorator dec = fabric.createDevice();

        dec.setPart();
        Device device = Decorator.getDevice();
        System.out.println(device.toString());
    }
}
