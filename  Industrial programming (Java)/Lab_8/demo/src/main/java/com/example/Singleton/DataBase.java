package com.example.Singleton;

import java.util.List;

import com.example.ObjectClasses.Device;

public class DataBase {
    private static DataBase instance;
    public static List<Device> Data;
    private static Integer iterator;  

    private DataBase(List<Device> list) {
        Data = list;
        iterator = Data.size();
    }

    public static DataBase getInstance(List<Device> list) {
        if(instance == null) {
            instance = new DataBase(list);
        }
        return instance;
    } 

    public static void IncreaseIterator() {
        if(iterator == null) {
            throw new NullPointerException();
        }
        if(iterator > Data.size() - 2) {
            iterator = 0;
        }
        iterator++;
    }

    public static void DecreaseIterator() {
        if(iterator == null) {
            throw new NullPointerException();
        }
        iterator--;
    }

    public static Integer getIterator() {
        return iterator;
    }
}

