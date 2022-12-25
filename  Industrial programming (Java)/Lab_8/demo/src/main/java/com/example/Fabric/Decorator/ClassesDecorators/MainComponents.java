package com.example.Fabric.Decorator.ClassesDecorators;

import com.example.Fabric.Decorator.Interface.MenuInterface;
import com.example.Singleton.DataBase;

public class MainComponents implements MenuInterface {
    @Override
    public void setPart() {
        DataBase.IncreaseIterator();
    }
}
