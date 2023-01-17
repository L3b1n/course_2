package com.patterns.Singleton;

public class Singleton {
    private static Singleton unique;
    private Singleton() {}

    public static synchronized Singleton getUnique() {
        if(unique == null) {
            unique = new Singleton();
        }
        return unique;
    }

    public void getDescription() {
        System.out.println("\tSingleton conection");
    }
}
