package com.example.Fabric.Decorator.ClassesDecorators;

import com.example.Fabric.Decorator.Decorator;
import com.example.Fabric.Decorator.Interface.MenuInterface;
import com.example.Singleton.DataBase;

public class BrandDecorator extends Decorator {
    public BrandDecorator(MenuInterface interInterface) {
        super(interInterface);
    }

    public void setPart() {
        device.setBrand(DataBase.Data.get(DataBase.getIterator()).getBrand());
        super.setPart();
    }
}
