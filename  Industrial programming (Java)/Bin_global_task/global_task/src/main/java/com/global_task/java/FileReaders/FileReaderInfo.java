package com.global_task.java.FileReaders;

import java.util.ArrayList;

import com.global_task.contracts.Interface.InterfaceFileReader;

public class FileReaderInfo implements InterfaceFileReader {
    protected String inputName;
    public FileReaderInfo(String inputFileName) {
        this.inputName = inputFileName;
    }

    @Override
    public String getFileName() {
        return this.inputName;
    }

    @Override
    public void Write(ArrayList<ArrayList<String>> result, String outputFileName) throws Throwable {}

    @Override
    public ArrayList<ArrayList<String>> Read() throws Throwable { 
        return null;
    }

    @Override
    public ArrayList<ArrayList<String>> Calculate(ArrayList<ArrayList<String>> readFile) throws Throwable { 
        return null;
    }

    @Override
    public ArrayList<ArrayList<String>> Transform(ArrayList<ArrayList<String>> readFile) throws Throwable {
        return readFile;
    }

    @Override
    public void getResult(String outputFileName) throws Throwable {}

}
