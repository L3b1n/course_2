package com.example.Fabric.Decorator.ClassesDecorators;

import com.example.Fabric.Decorator.Decorator;
import com.example.Fabric.Decorator.Interface.MenuInterface;
import com.example.Singleton.DataBase;

public class ColorDecorator extends Decorator {
    public ColorDecorator(MenuInterface interInterface) {
        super(interInterface);
    }

    public void setPart() {
        device.setColor(DataBase.Data.get(DataBase.getIterator()).getColor());
        super.setPart();
    }
}
