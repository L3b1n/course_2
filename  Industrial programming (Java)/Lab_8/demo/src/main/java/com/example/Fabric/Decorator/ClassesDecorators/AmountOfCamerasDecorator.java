package com.example.Fabric.Decorator.ClassesDecorators;

import com.example.Fabric.Decorator.Decorator;
import com.example.Fabric.Decorator.Interface.MenuInterface;
import com.example.Singleton.DataBase;

public class AmountOfCamerasDecorator extends Decorator {
    public AmountOfCamerasDecorator(MenuInterface interInterface) {
        super(interInterface);
    }

    public void setPart() {
        device.setCamera(DataBase.Data.get(DataBase.getIterator()).getCamera());
        super.setPart();
    }
}
