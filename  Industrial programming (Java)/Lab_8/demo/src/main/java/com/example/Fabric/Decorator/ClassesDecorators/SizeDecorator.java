package com.example.Fabric.Decorator.ClassesDecorators;

import com.example.Fabric.Decorator.Decorator;
import com.example.Fabric.Decorator.Interface.MenuInterface;
import com.example.Singleton.DataBase;

public class SizeDecorator extends Decorator {
    public SizeDecorator(MenuInterface interInterface) {
        super(interInterface);
    }

    public void setPart() {
        device.setSize(DataBase.Data.get(DataBase.getIterator()).getSize());
        super.setPart();
    }
}
