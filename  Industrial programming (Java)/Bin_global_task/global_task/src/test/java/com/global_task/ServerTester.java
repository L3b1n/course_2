package com.global_task;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.global_task.FileReaders.FileBuilder;
import com.global_task.FileReaders.JSONFileReader;
import com.global_task.FileReaders.TXTFileReader;
import com.global_task.FileReaders.XMLFileReader;
import com.global_task.contracts.Interface.InterfaceFileReader;

public class ServerTester {
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }
    
    @Test
    public void TestCreateDeleteFile() throws Throwable {
        Server.createFile("./src/main/result/com/global_task/test.txt");
        Server.deleteFile("./src/main/result/com/global_task/test.txt");
        Assert.assertEquals("File created: test.txt" + System.lineSeparator() + "File ./src/main/result/com/global_task/test.txt deleted successfully" + System.lineSeparator(), output.toString());
    }
    
    @Test
    public void TestCreateDeleteFileException() throws Throwable {
        Server.createFile("./src/main/result/com/global_task/test.txt");
        Server.createFile("./src/main/result/com/global_task/test.txt");
        Server.deleteFile("./src/main/result/com/global_task/test.txt");
        Assert.assertEquals("File created: test.txt" + System.lineSeparator() + "File already exists" + System.lineSeparator() + "File ./src/main/result/com/global_task/test.txt deleted successfully" + System.lineSeparator(), output.toString());
    }

    @Test
    public void TestCopyFileOnTxtFile() {
        try {
            TXTFileReader reader = new TXTFileReader("./src/main/tests/com/global_task/input.txt");
            ArrayList<ArrayList<String>> readFile1 = reader.Read();
            Server.copyFile("./src/main/tests/com/global_task/input.txt", "./src/main/result/com/global_task/test.txt");
            reader = new TXTFileReader("./src/main/result/com/global_task/test.txt");
            ArrayList<ArrayList<String>> readFile2 = reader.Read();
            Assert.assertEquals(readFile1.toString(), readFile2.toString());
            Server.deleteFile("./src/main/result/com/global_task/test.txt");
            Assert.assertEquals("File ./src/main/result/com/global_task/test.txt deleted successfully" + System.lineSeparator(), output.toString());
        } catch(Throwable e) {
            Assert.assertEquals("Error in TXT file read. Check selected file, actions and try again.", e.toString().substring(e.toString().indexOf(':') + 2));
        }
    }
    
    @Test
    public void TestCopyFileOnXmlFile() {
        try {
            XMLFileReader reader = new XMLFileReader("./src/main/tests/com/global_task/input.xml");
            ArrayList<ArrayList<String>> readFile1 = reader.Read();
            Server.copyFile("./src/main/tests/com/global_task/input.xml", "./src/main/result/com/global_task/test.xml");
            reader = new XMLFileReader("./src/main/result/com/global_task/test.xml");
            ArrayList<ArrayList<String>> readFile2 = reader.Read();
            Assert.assertEquals(readFile1.toString(), readFile2.toString());
            Server.deleteFile("./src/main/result/com/global_task/test.xml");
            Assert.assertEquals("File ./src/main/result/com/global_task/test.xml deleted successfully" + System.lineSeparator(), output.toString());
        } catch(Throwable e) {
            Assert.assertEquals("Error in XML file reading. Check selected file, actions and try again.", e.toString().substring(e.toString().indexOf(':') + 2));
        }
    }

    @Test
    public void TestCopyFileOnJsonFile() {
        try {
            JSONFileReader reader = new JSONFileReader("./src/main/tests/com/global_task/input.json");
            ArrayList<ArrayList<String>> readFile1 = reader.Read();
            Server.copyFile("./src/main/tests/com/global_task/input.json", "./src/main/result/com/global_task/test.json");
            reader = new JSONFileReader("./src/main/result/com/global_task/test.json");
            ArrayList<ArrayList<String>> readFile2 = reader.Read();
            Assert.assertEquals(readFile1.toString(), readFile2.toString());
            Server.deleteFile("./src/main/result/com/global_task/test.json");
            Assert.assertEquals("File ./src/main/result/com/global_task/test.json deleted successfully" + System.lineSeparator(), output.toString());
        } catch(Throwable e) {
            Assert.assertEquals("Error in JSON file reading. Check selected file, actions and try again.", e.toString().substring(e.toString().indexOf(':') + 2));
        }
    }

    @Test
    public void TestCopyFileOnZipFile() {
        try {
            FileBuilder builder = new FileBuilder("txt", "./src/main/tests/com/global_task/test.zip");
            builder.setZip(true);
            InterfaceFileReader reader = builder.getFileReader();
            ArrayList<ArrayList<String>> readFile1 = reader.Read();
            Server.copyFile("./src/main/tests/com/global_task/test.zip", "./src/main/result/com/global_task/test.zip");
            builder = new FileBuilder("txt", "./src/main/result/com/global_task/test.zip");
            builder.setZip(true);
            reader = builder.getFileReader();
            ArrayList<ArrayList<String>> readFile2 = reader.Read();
            Assert.assertEquals(readFile1.toString(), readFile2.toString());
            Server.deleteFile("./src/main/result/com/global_task/test.zip");
            Assert.assertEquals("File ./src/main/result/com/global_task/test.zip deleted successfully" + System.lineSeparator(), output.toString());
        } catch(Throwable e) {
            Assert.assertEquals("Error in TXT file read. Check selected file, actions and try again.", e.toString().substring(e.toString().indexOf(':') + 2));
        }
    }

    @Test
    public void TestCopyFileOnEncryptedFile() {
        try {
            FileBuilder builder = new FileBuilder("txt", "./src/main/tests/com/global_task/encrypted.txt");
            builder.setEncrypt("squirreldgwndlaj");
            InterfaceFileReader reader = builder.getFileReader();
            ArrayList<ArrayList<String>> readFile1 = reader.Read();
            Server.copyFile("./src/main/tests/com/global_task/encrypted.txt", "./src/main/result/com/global_task/test.txt");
            builder = new FileBuilder("txt", "./src/main/result/com/global_task/test.txt");
            builder.setEncrypt("squirreldgwndlaj");
            reader = builder.getFileReader();
            ArrayList<ArrayList<String>> readFile2 = reader.Read();
            Assert.assertEquals(readFile1.toString(), readFile2.toString());
            Server.deleteFile("./src/main/result/com/global_task/test.txt");
            Assert.assertEquals("File ./src/main/result/com/global_task/test.txt deleted successfully" + System.lineSeparator(), output.toString());
        } catch(Throwable e) {
            Assert.assertEquals("Error in TXT file read. Check selected file, actions and try again.", e.toString().substring(e.toString().indexOf(':') + 2));
        }
    }
}