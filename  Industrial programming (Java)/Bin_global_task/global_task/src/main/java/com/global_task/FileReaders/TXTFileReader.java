package com.global_task.FileReaders;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
// import java.util.Scanner;

import com.global_task.StringParsers.Calculation;

public class TXTFileReader extends FileReaderInfo {
    public TXTFileReader(String fileName) {
        super(fileName);
    }

    // @Override
    public void Write(ArrayList<String> result, String outputFileName) throws IOException {
        FileWriter writer = new FileWriter(outputFileName);
        for(int i = 0; i < result.size(); i++) {
            writer.write(result.get(i));
        }
        writer.close();
    }
    
    // @Override
    public ArrayList<String> Read() throws IOException {
        FileReader fileReader = new FileReader(inputName);
        ArrayList<String> readFile = new ArrayList<>();
        Scanner reader = new Scanner(fileReader);
        while(reader.hasNextLine()) {
            readFile.add(reader.nextLine() + "\n");
        }
        reader.close();
        return readFile;
    }

    // @Override
    public ArrayList<String> Calculate(ArrayList<String> readFile) throws IOException {
        ArrayList<String> calculated = new ArrayList<>();
        for(String line : readFile) {
            String calculatedLine = Calculation.CalculationOfLine(line);
            calculated.add(calculatedLine);
        }
        return calculated;
    }
    
    // @Override
    public void getResult(String outputFileName) throws IOException {
        ArrayList<String> readFile = Read();
        ArrayList<String> result = Calculate(readFile);
        Write(result, outputFileName);
    }
}
