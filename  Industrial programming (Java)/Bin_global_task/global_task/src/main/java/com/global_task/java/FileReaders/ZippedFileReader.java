package com.global_task.java.FileReaders;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.zip.ZipInputStream;

import com.global_task.contracts.Interface.InterfaceFileReader;

public class ZippedFileReader extends FileReader {
    public ZippedFileReader(InterfaceFileReader fileReader) {
        super(fileReader);
    }

    @Override
    public void Write(ArrayList<ArrayList<String>> result, String outputFileName) throws Throwable {
        reader.Write(result, outputFileName);
    }

    @Override
    public ArrayList<ArrayList<String>> Read() throws Throwable {
        byte[] buffer = new byte[1024];
        ArrayList<ArrayList<String>> readFile = new ArrayList<>();
        ZipInputStream zipReader = new ZipInputStream(new FileInputStream(inputName));
        while(zipReader.getNextEntry() != null) {
            int length;
            while((length = zipReader.read(buffer)) > 0) {
                ArrayList<String> temp = new ArrayList<>();
                temp.add(new String(buffer, 0, length, StandardCharsets.UTF_8));
                readFile.add(temp);
            }
        }
        zipReader.close();
        return reader.Transform(readFile);
    }
    
    @Override
    public ArrayList<ArrayList<String>> Transform(ArrayList<ArrayList<String>> readFile) throws Throwable {
        return reader.Transform(readFile);
    }

    @Override
    public ArrayList<ArrayList<String>> Calculate(ArrayList<ArrayList<String>> readFile) throws Throwable {
        return reader.Calculate(readFile);
    }

    @Override
    public void getResult(String outputFileName) throws Throwable {
        ArrayList<ArrayList<String>> readFile = Read();
        ArrayList<ArrayList<String>> result = Calculate(readFile);
        Write(result, outputFileName);
    }
}
