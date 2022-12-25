package com.example.Fabric.Builder.Interface;

import com.example.Type.Brand;
import com.example.Type.Color;
import com.example.ObjectClasses.Size;
import com.example.ObjectClasses.Camera;
import com.example.ObjectClasses.Battery;

public interface Builder {
    void setBrand(Brand brand);
    void setAmountOfCameras(Camera camera);
    void setColor(Color color);
    void setSize(Size size);
    void setBattery(Battery battery);
}