package com.lab_6;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TXT {
    private static Scanner scanner;

    public static ArrayList<Person> ReadFromFileTXT(String filename) {
        ArrayList<Person> people = new ArrayList<>();
        try(FileReader reader = new FileReader(filename)) {
            scanner = new Scanner(reader);
            while(scanner.hasNextLine()) {
                people.add(new Person(scanner.nextLine(), scanner.nextLine(), scanner.nextLine()));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return people;
    }

    public static void WriteInFileTXT(ArrayList<Person> people) {
        try(FileWriter writer = new FileWriter("out_file.txt")) {
            for(int i = 0; i < people.size(); ++i) {
                writer.write(people.get(i).getFirstName() + " " + people.get(i).getLastName() + " " + people.get(i).getAge() + "\n");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}