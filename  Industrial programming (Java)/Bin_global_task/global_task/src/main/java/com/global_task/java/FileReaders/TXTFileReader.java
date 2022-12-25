package com.global_task.java.FileReaders;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import com.global_task.java.StringParsers.Calculation;

public class TXTFileReader extends FileReaderInfo {
    public TXTFileReader(String fileName) {
        super(fileName);
    }

    @Override
    public ArrayList<ArrayList<String>> Read() throws Throwable {
        ArrayList<ArrayList<String>> readFile = new ArrayList<>();
        Scanner reader = new Scanner(new FileReader(inputName));
        while(reader.hasNextLine()) {
            ArrayList<String> temp = new ArrayList<>();
            temp.add(reader.nextLine() + "\n");
            readFile.add(temp);
        }
        reader.close();
        return readFile;
    }

    @Override
    public void Write(ArrayList<ArrayList<String>> result, String outputFileName) throws Throwable {
        FileWriter writer = new FileWriter(outputFileName);
        for(int i = 0; i < result.size(); i++) {
            writer.write("Result" + Integer.toString(i + 1) + " = " + result.get(i).get(0) + "\n");
        }
        writer.close();
    }

    @Override
    public ArrayList<ArrayList<String>> Calculate(ArrayList<ArrayList<String>> readFile) throws Throwable {
        ArrayList<ArrayList<String>> calculated = new ArrayList<>();
        for(ArrayList<String> lines : readFile) {
            for(String line : lines) {
                ArrayList<String> calculatedLine = new ArrayList<>();
                calculatedLine.add(Calculation.CalculationOfLine(line));
                calculated.add(calculatedLine);
            }
        }
        return calculated;
    }
    
    @Override
    public void getResult(String outputFileName) throws Throwable {
        ArrayList<ArrayList<String>> readFile = Read();
        ArrayList<ArrayList<String>> result = Calculate(readFile);
        Write(result, outputFileName);
    }
}