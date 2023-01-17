package com.patterns.Singleton;

public class Main {
    public static void main(String[] args) {
        Singleton singleton = Singleton.getUnique();
        singleton.getDescription();
    }
}
