package com.example.Fabric.Builder;

import com.example.Type.Brand;
import com.example.Type.Color;
import com.example.ObjectClasses.Size;
import com.example.ObjectClasses.Device;
import com.example.ObjectClasses.Camera;
import com.example.ObjectClasses.Battery;
import com.example.Fabric.Builder.Interface.Builder;

public class DeviceBuilder implements Builder {
    private Brand brand;
    private Camera camera;
    private Color color;
    private Battery battery;
    private Size size;

    @Override
    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @Override
    public void setAmountOfCameras(Camera camera) {
        this.camera = camera;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public void setBattery(Battery battery) {
        this.battery = battery;
    }

    public Device getResult() {
        return new Device(brand, camera, size, color, battery);
    }
}
