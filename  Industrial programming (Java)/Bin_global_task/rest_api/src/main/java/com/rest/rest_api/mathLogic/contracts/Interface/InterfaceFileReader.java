package com.rest.rest_api.mathLogic.contracts.Interface;

import java.util.ArrayList;

public interface InterfaceFileReader {
    String getFileName();
    void Write(ArrayList<ArrayList<String>> result, String outputFileName) throws Exception;
    void WriteResult(ArrayList<ArrayList<String>> result, String outputFileName) throws Exception;
    ArrayList<ArrayList<String>> Read() throws Exception;
    ArrayList<ArrayList<String>> ReadResult() throws Exception;
    ArrayList<ArrayList<String>> Transform(byte[] tempByte) throws Exception;
    ArrayList<ArrayList<String>> TransformResult(byte[] tempByte) throws Exception;
    ArrayList<ArrayList<String>> Calculate(ArrayList<ArrayList<String>> readFile) throws Exception;
    void getResult(String outputFileName) throws Exception;
}
