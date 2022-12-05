package com.lab_6;

import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        ArrayList<Person> txt = new ArrayList<>(TXT.ReadFromFileTXT("in_file.txt"));
        TXT.WriteInFileTXT(txt);

        // ArrayList<Person> json = new ArrayList<>(JSON.ReadFromJSON("in_file.json"));
        // JSON.WriteInFileJSON(json);
        
        ArrayList<Person> xml = new ArrayList<>(XML.ReadFromFileXML("in_file.xml"));
        XML.WriteInFileXML(xml);

        // Print using lambda
        System.out.println("--- Method 1 ---");
        for(Person p : txt) { System.out.println(p.getFirstName() + " " + p.getLastName() + " " + p.getAge()); }
        System.out.println("\n");

        // System.out.println("--- Method 2 ---");
        // txt.forEach(n -> System.out.println(n.getFirstName() + " " + n.getLastName() + " " + n.getAge()));
        // System.out.println("\n");

        // Print using iterator
        System.out.println("--- Method 3 ---");
        Iterator<Person> iter = txt.iterator();
        while(iter.hasNext()) {
            Person t = iter.next();
            System.out.println(t.getFirstName() + " " + t.getLastName() + " " + t.getAge());
        }
    }
}
