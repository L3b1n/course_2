package com.global_task.FileReaders;

import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.global_task.StringParsers.Calculation;

public class TXTFileReader extends FileReaderInfo {
    public TXTFileReader(String fileName) {
        super(fileName);
    }

    @Override
    public void Write(ArrayList<ArrayList<String>> result, String outputFileName) throws Exception {
        try {
            FileWriter writer = new FileWriter(outputFileName);
            for(int i = 0; i < result.size(); i++) {
                writer.write(result.get(i).get(0));
                if(i != result.size() - 1) {
                    writer.write("\n");
                }
            }
            writer.close();
        } catch(Exception e) {
            throw new Exception("Error in TXT file write. Check selected file, actions and try again.", e);
        }
    }

    @Override
    public void WriteResult(ArrayList<ArrayList<String>> result, String outputFileName) throws Exception {
        try {
            FileWriter writer = new FileWriter(outputFileName);
            for(int i = 0; i < result.size(); i++) {
                writer.write("Result" + Integer.toString(i + 1) + " = " + result.get(i).get(0) + "\n");
            }
            writer.close();
        } catch(Exception e) {
            throw new Exception("Error in TXT file calculating. Check selected file, actions and try again.", e);
        }
    }

    @Override
    public ArrayList<ArrayList<String>> Read() throws Exception {
        try {
            ArrayList<ArrayList<String>> readFile = new ArrayList<>();
            Scanner reader = new Scanner(new FileReader(inputName));
            while(reader.hasNextLine()) {
                ArrayList<String> temp = new ArrayList<>();
                temp.add(reader.nextLine());
                readFile.add(temp);
            }
            reader.close();
            return readFile;
        } catch(Exception e) {
            throw new Exception("Error in TXT file read. Check selected file, actions and try again.", e);
        }
    }

    @Override
    public ArrayList<ArrayList<String>> ReadResult() throws Exception {
        try {
            ArrayList<ArrayList<String>> readFile = new ArrayList<>();
            Scanner reader = new Scanner(new FileReader(inputName));
            while(reader.hasNextLine()) {
                ArrayList<String> temp = new ArrayList<>();
                temp.add(reader.nextLine());
                readFile.add(temp);
            }
            reader.close();
            return readFile;
        } catch(Exception e) {
            throw new Exception("Error in TXT file calculating. Check selected file, actions and try again.", e);
        }
    }

    @Override
    public ArrayList<ArrayList<String>> Transform(byte[] tempByte) throws Exception {
        try {
            String tempString = new String(tempByte, StandardCharsets.UTF_8);
            StringTokenizer stringTokenizer = new StringTokenizer(tempString, "\n");
            ArrayList<ArrayList<String>> readFile = new ArrayList<>();
            while(stringTokenizer.hasMoreTokens()) {
                ArrayList<String> temp = new ArrayList<>(); 
                temp.add(stringTokenizer.nextToken());
                readFile.add(temp);
            }
            return readFile;
        } catch(Exception e) {
            throw new Exception("Error in TXT file calculating. Check selected file, actions and try again.", e);
        }
    }

    @Override
    public ArrayList<ArrayList<String>> TransformResult(byte[] tempByte) throws Exception {
        try {
            String tempString = new String(tempByte, StandardCharsets.UTF_8);
            StringTokenizer stringTokenizer = new StringTokenizer(tempString, "\n");
            ArrayList<ArrayList<String>> readFile = new ArrayList<>();
            while(stringTokenizer.hasMoreTokens()) {
                ArrayList<String> temp = new ArrayList<>(); 
                temp.add(stringTokenizer.nextToken());
                readFile.add(temp);
            }
            return readFile;
        } catch(Exception e) {
            throw new Exception("Error in TXT file calculating. Check selected file, actions and try again.", e);
        }
    }

    @Override
    public ArrayList<ArrayList<String>> Calculate(ArrayList<ArrayList<String>> readFile) throws Exception {
        ArrayList<ArrayList<String>> calculated = new ArrayList<>();
        for(ArrayList<String> lines : readFile) {
            for(String line : lines) {
                String tempCalculation = Calculation.CalculationOfLine(line);
                if(!tempCalculation.equals(line)) {
                    ArrayList<String> calculatedLine = new ArrayList<>();
                    calculatedLine.add(tempCalculation);
                    calculated.add(calculatedLine);
                }
            }
        }
        if(calculated.size() == 0) {
            throw new Exception("Error in TXT file calculating. There aren't any math expressions. Check selected file, actions and try again.");
        }
        return calculated;
        
    }
    
    @Override
    public void getResult(String outputFileName) throws Exception {
        ArrayList<ArrayList<String>> readFile = ReadResult();
        ArrayList<ArrayList<String>> result = Calculate(readFile);
        WriteResult(result, outputFileName);
    }
}
