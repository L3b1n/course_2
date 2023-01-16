package com.rest.rest_api.mathLogic.FileReaders;

import java.util.ArrayList;

import com.rest.rest_api.mathLogic.contracts.Interface.InterfaceFileReader;

public class FileReaderInfo implements InterfaceFileReader {
    protected static String inputName;
    public FileReaderInfo(String inputFileName) {
        FileReaderInfo.inputName = inputFileName;
    }

    @Override
    public String getFileName() {
        return FileReaderInfo.inputName;
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
    public ArrayList<ArrayList<String>> ReadResult() throws Exception {
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
    public ArrayList<ArrayList<String>> TransformResult(byte[] tempByte) throws Exception {
        return null;
    }

    @Override
    public void getResult(String outputFileName) throws Exception {}
}
