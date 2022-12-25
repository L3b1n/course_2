package com.example.Fabric.Decorator;

import com.example.Fabric.Decorator.Interface.MenuInterface;
import com.example.ObjectClasses.Device;

public class Decorator implements MenuInterface {
    protected MenuInterface interInterface;
    protected static Device device = new Device();

    public void setInterInterface(MenuInterface interInterface) {
        this.interInterface = interInterface;
    }

    public static void setDevice(Device device) {
        Decorator.device = device;
    }

    public static Device getDevice() {
        return device;
    }

    public Decorator(MenuInterface interInterface) {
        this.interInterface = interInterface;
    }

    @Override
    public void setPart() {
        this.interInterface.setPart();
    }
}
