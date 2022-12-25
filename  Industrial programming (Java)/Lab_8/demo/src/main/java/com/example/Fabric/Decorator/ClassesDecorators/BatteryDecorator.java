package com.example.Fabric.Decorator.ClassesDecorators;

import com.example.Fabric.Decorator.Decorator;
import com.example.Fabric.Decorator.Interface.MenuInterface;
import com.example.Singleton.DataBase;

public class BatteryDecorator extends Decorator {
    public BatteryDecorator(MenuInterface interInterface) {
        super(interInterface);
    }
    
    public void setPart() {
        device.setBattery(DataBase.Data.get(DataBase.getIterator()).getBattery());
        super.setPart();
    }
}
