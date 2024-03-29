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

    public static void WriteZipped(String inputFileName, String outputFileName) throws Exception {
        try {
            ZipOutputStream writer = new ZipOutputStream(new FileOutputStream(outputFileName));
            FileInputStream reader = new FileInputStream(inputFileName);
            ZipEntry entry = new ZipEntry(inputFileName);
            writer.putNextEntry(entry);
            byte[] buffer = new byte[reader.available()];
            reader.read(buffer);
            writer.write(buffer);
            writer.closeEntry();
            writer.close();
            reader.close();
        } catch(Exception e) {
            throw new Exception("Error in file zip. Check selected file, actions and try again.", e);
        }
    }

    @Override
    public void Write(ArrayList<ArrayList<String>> result, String outputFileName) throws Exception {
        reader.Write(result, outputFileName);
    }

    @Override
    public void WriteResult(ArrayList<ArrayList<String>> result, String outputFileName) throws Exception {
        reader.WriteResult(result, outputFileName);
    }

    @Override
    public ArrayList<ArrayList<String>> Read() throws Exception {
        try {
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
        } catch(Exception e) {
            throw new Exception("Error in file unzip. Check selected file, actions and try again.", e);
        }
    }

    @Override
    public ArrayList<ArrayList<String>> ReadResult() throws Exception {
        try {
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
            return reader.TransformResult(tempByte);
        } catch(Exception e) {
            throw new Exception("Error in file unzip. Check selected file, actions and try again.", e);
        }
    }
    
    @Override
    public ArrayList<ArrayList<String>> Transform(byte[] readFile) throws Exception {
        return reader.Transform(readFile);
    }
    
    @Override
    public ArrayList<ArrayList<String>> TransformResult(byte[] readFile) throws Exception {
        return reader.TransformResult(readFile);
    }
    
    @Override
    public ArrayList<ArrayList<String>> Calculate(ArrayList<ArrayList<String>> readFile) throws Exception {
        return reader.Calculate(readFile);
    }
    
    @Override
    public void getResult(String outputFileName) throws Exception {
        ArrayList<ArrayList<String>> readFile = ReadResult();
        ArrayList<ArrayList<String>> result = Calculate(readFile);
        WriteResult(result, outputFileName);
    }
}
