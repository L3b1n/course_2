package com.example.ObjectClasses;

public class Size {
    private Double height;
    private Double lenght;
    private Double thickness;

    public Size(Double height, Double lenght, Double thickness) {
        this.height = height;
        this.lenght = lenght;
        this.thickness = thickness;
    }

    public Double getHeight() {
        return height;
    }
    
    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getLenght() {
        return lenght;
    }

    public void setLenght(Double lenght) {
        this.lenght = lenght;
    }

    public Double getThickness() {
        return thickness;
    }

    public void setThickness(Double thickness) {
        this.thickness = thickness;
    }

    @Override
    public String toString() {
        return "Size [height = " + height + ", lenght = " + lenght + ", thickness = " + thickness + "]";
    }
}
