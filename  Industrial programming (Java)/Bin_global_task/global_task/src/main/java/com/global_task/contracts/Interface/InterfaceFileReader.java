package com.global_task.contracts.Interface;

import java.util.ArrayList;

public interface InterfaceFileReader {
    String getFileName();
    void Write(ArrayList<ArrayList<String>> result, String outputFileName) throws Throwable;
    void WriteResult(ArrayList<ArrayList<String>> result, String outputFileName) throws Throwable;
    ArrayList<ArrayList<String>> Read() throws Throwable;
    ArrayList<ArrayList<String>> Transform(byte[] tempByte) throws Throwable;
    ArrayList<ArrayList<String>> Calculate(ArrayList<ArrayList<String>> readFile) throws Throwable;
    void getResult(String outputFileName) throws Throwable;
}
