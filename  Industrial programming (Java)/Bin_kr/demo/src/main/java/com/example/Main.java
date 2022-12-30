package com.example;

public class Main {
    public static void main(String[] args) throws Throwable {
        ClassBD Main = new ClassBD();
        Main.ReadFileInList("test.txt");
        Main.ReadFileInMap("test.txt");
        // Main.deleteName();
        Main.Sort();
        Main.WriteFileInXML();
    }
}
