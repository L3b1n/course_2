package com.example;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main 
{
    public static boolean isTrue(String text) 
    {
        Pattern pattern = Pattern.compile("^[^z0-9\\.-]+\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}");
        Matcher matcher = pattern.matcher(text);
        if(matcher.find()){ return true;} 
        else{ return false;}
    }

    public static void main(String[] args) throws Exception 
    {
        FileReader input = new FileReader("input.txt");
        FileWriter output = new FileWriter("output.txt");
        Scanner scan = new Scanner(input);
        String temp = new String();
        while(scan.hasNextLine()){ temp = scan.nextLine(); output.write(temp + " : " + isTrue(temp) + "\n");}
        input.close();
        output.close();
        scan.close();
    }
}
