package com.global_task;

// import java.io.*;
// import java.util.zip.*;

import com.global_task.FileReaders.FileBuilder;
import com.global_task.contracts.Interface.InterfaceFileReader;
  
public class Zipper {
    // public static void main(String[] args) throws Throwable {
    //     FileBuilder builder = new FileBuilder("json", "src/main/tests/com/global_task/encryptedinput.json");
    //     builder.setEncrypt("squirreldgwndlaj");
    //     // builder.setEncrypt(SecondKey);
    //     builder.setZip(false);
    //     InterfaceFileReader readerTxt = builder.getFileReader();
    //     ArrayList<ArrayList<String>> temp = readerTxt.Read();
    //     for(ArrayList<String> vec : temp) {
    //         for(String x : vec) {
    //             System.out.print(x);
    //         }
    //         System.out.println();
    //     }
    //     readerTxt.getResult("test.json");
    // } 
    // public static void main(String[] args) throws Throwable {
    //     String filename = "input.txt";
    //     try(ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("in.zip"));
    //         FileInputStream fis = new FileInputStream(filename);) {
    //         ZipEntry entry1 = new ZipEntry("src/main/tests/com/global_task/input.txt");
    //         zout.putNextEntry(entry1);
    //         byte[] buffer = new byte[fis.available()];
    //         fis.read(buffer);
    //         zout.write(buffer);
    //         zout.closeEntry();
    //     } catch(Exception ex) {
    //         System.out.println(ex.getMessage());
    //     } 
    // } 
    // private static final String FirstKey = "squirreldgwndlaj";
    // private static final String SecondKey = "abshgsfrenhlksmp";

    // private static final String ZippedAndEncrypedInputFileName = "src/main/tests/com/global_task/input.zip";
    // private static final String ZippedAndEncrypedOutputFileName = "src/main/tests/com/global_task/encrypted_output.txt";

    // private static final String TxtInputFileName = "src/main/tests/com/global_task/input.txt";
    // private static final String TxtOutputFileName = "src/main/tests/com/global_task/output.txt";

    // private static final String JsonInputFileName = "src/main/tests/com/global_task/input.json";
    // private static final String JsonOutputFileName = "src/main/tests/com/global_task/output.json";

    // private static final String XmlInputFileName = "src/main/tests/com/global_task/input.xml";
    // private static final String XmlOutputFileName = "src/main/tests/com/global_task/output.xml";
    
    // public static void main(String[] args) throws Throwable {
    //     TXTFileReader tempTxt = new TXTFileReader(TxtInputFileName);
    //     tempTxt.getResult(TxtOutputFileName);

    //     FileBuilder builder = new FileBuilder("Txt", ZippedAndEncrypedInputFileName);
    //     builder.setEncrypt(FirstKey);
    //     builder.setEncrypt(SecondKey);
    //     builder.setZip(true);
    //     InterfaceFileReader readerTxt = builder.getFileReader();
    //     readerTxt.getResult(ZippedAndEncrypedOutputFileName); 
        
    //     JSONFileReader tempJson = new JSONFileReader(JsonInputFileName);
    //     tempJson.getResult(JsonOutputFileName);
        
    //     XMLFileReader tempXml = new XMLFileReader(XmlInputFileName);
    //     tempXml.getResult(XmlOutputFileName);
    // }

    public static void main(String[] args) throws Throwable {
        FileBuilder builder = new FileBuilder("txt", "test.zip");
        builder.setEncrypt("squirreldgwndlaj");
        builder.setEncrypt("abshgsfrenhlksmp");
        builder.setZip(true);
        InterfaceFileReader reader = builder.getFileReader();
        System.out.println(reader.Read());
    }
    // public static void main(String[] args) {
    //     try(ZipInputStream zin = new ZipInputStream(new FileInputStream("src/main/tests/com/global_task/zippedinput.zip"))) {
    //         ZipEntry entry;
    //         String name;
    //         long size;
    //         while((entry=zin.getNextEntry()) != null){
    //             name = entry.getName();
    //             size = entry.getSize();
    //             System.out.printf("File name: %s \t File size: %d \n", name, size);

    //             FileOutputStream fout = new FileOutputStream("new" + name);
    //             for(int c = zin.read(); c != -1; c = zin.read()) {
    //                 fout.write(c);
    //             }
    //             fout.flush();
    //             zin.closeEntry();
    //             fout.close();
    //         }
    //     } catch(Exception ex) {
    //         System.out.println(ex.getMessage());
    //     } 
    // } 
}
