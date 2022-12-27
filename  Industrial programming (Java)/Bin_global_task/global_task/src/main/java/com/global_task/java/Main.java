package com.global_task.java;

import com.global_task.java.FileReaders.FileBuilder;
import com.global_task.java.FileReaders.TXTFileReader;
import com.global_task.java.FileReaders.XMLFileReader;
import com.global_task.java.FileReaders.JSONFileReader;
import com.global_task.contracts.Interface.InterfaceFileReader;

public class Main {
    private static final String FirstKey = "squirreldgwndlaj";
    private static final String SecondKey = "abshgsfrenhlksmp";

    private static final String ZippedAndEncrypedInputFileName = "input.zip";
    private static final String ZippedAndEncrypedOutputFileName = "encrypted_output.txt";

    private static final String TxtInputFileName = "input.txt";
    private static final String TxtOutputFileName = "output.txt";

    private static final String JsonInputFileName = "input.json";
    private static final String JsonOutputFileName = "output.json";

    private static final String XmlInputFileName = "input.xml";
    private static final String XmlOutputFileName = "output.xml";
    
    public static void main(String[] args) throws Throwable {
        TXTFileReader tempTxt = new TXTFileReader(TxtInputFileName);
        tempTxt.getResult(TxtOutputFileName);

        FileBuilder builder = new FileBuilder("Txt", ZippedAndEncrypedInputFileName);
        builder.setEncrypt(FirstKey);
        builder.setEncrypt(SecondKey);
        builder.setZip(true);
        InterfaceFileReader readerTxt = builder.getFileReader();
        readerTxt.getResult(ZippedAndEncrypedOutputFileName); 
        
        JSONFileReader tempJson = new JSONFileReader(JsonInputFileName);
        tempJson.getResult(JsonOutputFileName);
        
        XMLFileReader tempXml = new XMLFileReader(XmlInputFileName);
        tempXml.getResult(XmlOutputFileName);
    }
}