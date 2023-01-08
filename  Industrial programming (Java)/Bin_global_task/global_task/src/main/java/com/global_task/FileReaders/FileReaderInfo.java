package com.global_task.FileReaders;

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
    public void Write(ArrayList<ArrayList<String>> result, String outputFileName) throws Exception {}

    @Override
    public void WriteResult(ArrayList<ArrayList<String>> result, String outputFileName) throws Exception {}

    @Override
    public ArrayList<ArrayList<String>> Read() throws Exception { 
        return null;
    }

    @Override
    public ArrayList<ArrayList<String>> Calculate(ArrayList<ArrayList<String>> readFile) throws Exception { 
        return null;
    }
    
    @Override
    public ArrayList<ArrayList<String>> Transform(byte[] tempByte) throws Exception {
        return null;
    }

    @Override
    public void getResult(String outputFileName) throws Exception {}
}
