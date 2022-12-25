package com.example.ObjectClasses;

import com.example.Type.Brand;
import com.example.Type.Color;

public class Device {
    private Brand brand;
    private Camera camera; 
    private Size size;
    private Color color;
    private Battery battery;

    public Device(Brand brand, Camera camera, Size size, Color color, Battery battery) {
        this.brand = brand;
        this.camera = camera;
        this.size = size;
        this.color = color;
        this.battery = battery;
    }

    public Device() {
        this.brand = null;
        this.camera = null;
        this.size = null;
        this.color = null;
        this.battery = null;
    }

    public Device(Device device) {
        this.brand = device.getBrand();
        this.camera = device.getCamera();
        this.size = device.getSize();
        this.color = device.getColor();
        this.battery = device.getBattery();
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public Size getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Device [brand = " + brand + ", camera = " + camera + ", size = " + size + ", color = " + color + ", battery = " + battery + "]";
    }

    public Battery getBattery() {
        return battery;
    }

    public void setBattery(Battery battery) {
        this.battery = battery;
    }
}
