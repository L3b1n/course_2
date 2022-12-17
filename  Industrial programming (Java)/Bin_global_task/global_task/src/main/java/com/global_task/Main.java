package com.global_task;

import java.io.IOException;

import com.global_task.FileReaders.TXTFileReader;

public class Main {
    // private static final String FirstKey = "test";
    // private static final String SecondKey = "test";

    private static final String TxtInputFileName = "input.txt";
    private static final String TxtOutputFileName = "output.txt";

    // private static final String JsonInputFileName = "input.json";
    // private static final String JsonOutputFileName = "output.json";

    // private static final String XmlInputFileName = "input.xml";
    // private static final String XmlOutputFileName = "output.xml";
    
    public static void main(String[] args) throws IOException {
        TXTFileReader temp = new TXTFileReader(TxtInputFileName);
        temp.getResult(TxtOutputFileName);
    }
}