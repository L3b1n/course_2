package com.global_task.FileReaders;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import com.global_task.contracts.Interface.InterfaceFileReader;

public class ZippedFileReader extends FileReader {
    public ZippedFileReader(InterfaceFileReader fileReader) {
        super(fileReader);
    }

    public static void WriteZipped(String inputFileName, String outputFileName) throws Throwable {
        ZipOutputStream writer = new ZipOutputStream(new FileOutputStream(outputFileName));
        FileInputStream reader = new FileInputStream(inputFileName);
        ZipEntry entry1 = new ZipEntry(inputFileName);
        writer.putNextEntry(entry1);
        byte[] buffer = new byte[reader.available()];
        reader.read(buffer);
        writer.write(buffer);
        writer.closeEntry();
        writer.close();
        reader.close();
    }

    @Override
    public void Write(ArrayList<ArrayList<String>> result, String outputFileName) throws Throwable {
        reader.Write(result, outputFileName);
    }

    @Override
    public ArrayList<ArrayList<String>> Read() throws Throwable {
        byte[] buffer = new byte[1024];
        ZipInputStream zipReader = new ZipInputStream(new FileInputStream(inputName));
        StringBuilder builder = new StringBuilder();
        while(zipReader.getNextEntry() != null) {
            int length;
            while((length = zipReader.read(buffer)) > 0) {
                String tempString = new String(buffer, 0, length, StandardCharsets.UTF_8);
                builder.append(tempString);
            }
        }
        zipReader.close();
        byte[] tempByte = builder.toString().getBytes(StandardCharsets.UTF_8);
        return reader.Transform(tempByte);
    }
    
    @Override
    public ArrayList<ArrayList<String>> Transform(byte[] readFile) throws Throwable {
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
