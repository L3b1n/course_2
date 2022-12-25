package com.example.ObjectClasses;

public class Battery {
    private Integer capacity;
    private String ID;
    
    public Battery(Integer capacity, String iD) {
        this.capacity = capacity;
        ID = iD;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getID() {
        return ID;
    }

    public void setID(String iD) {
        ID = iD;
    }

    @Override
    public String toString() {
        return "Battery [capacity = " + capacity + ", ID = " + ID + "]";
    }
}
