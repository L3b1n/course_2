package com.global_task.FileReadersTests;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.global_task.Server;
import com.global_task.FileReaders.TXTFileReader;

public class TXTFileTester {
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    @Test
    public void TestRead() {
        try {
            TXTFileReader reader = new TXTFileReader("./src/main/tests/com/global_task/input.txt");
            ArrayList<ArrayList<String>> readFile = reader.Read();
            Assert.assertEquals("[[1234 / 500 + 543], [100 / 0], [4 - 1]]", readFile.toString());
        } catch(Exception e) {
            Assert.assertEquals("Error in TXT file read. Check selected file, actions and try again.", e.toString().substring(e.toString().indexOf(':') + 2));
        }
    }
    
    @Test
    public void TestReadException() {
        try {
            TXTFileReader reader = new TXTFileReader("./src/main/tests/com/global_task/temp.txt");
            ArrayList<ArrayList<String>> readFile = reader.Read();
            Assert.assertEquals("[[1234 / 500 + 543], [100 / 0], [4 - 1]]", readFile.toString());
        } catch(Exception e) {
            Assert.assertEquals("Error in TXT file read. Check selected file, actions and try again.", e.toString().substring(e.toString().indexOf(':') + 2));
        }
    }
    
    @Test
    public void TestReadResult() {
        try {
            TXTFileReader reader = new TXTFileReader("./src/main/tests/com/global_task/input.txt");
            ArrayList<ArrayList<String>> readFile = reader.ReadResult();
            Assert.assertEquals("[[1234 / 500 + 543], [100 / 0], [4 - 1]]", readFile.toString());
        } catch(Exception e) {
            Assert.assertEquals("Error in TXT file calculating. Check selected file, actions and try again.", e.toString().substring(e.toString().indexOf(':') + 2));
        }
    }
    
    @Test
    public void TestReadResultException() {
        try {
            TXTFileReader reader = new TXTFileReader("./src/main/tests/com/global_task/temp.txt");
            ArrayList<ArrayList<String>> readFile = reader.ReadResult();
            Assert.assertEquals("[[1234 / 500 + 543], [100 / 0], [4 - 1]]", readFile.toString());
        } catch(Exception e) {
            Assert.assertEquals("Error in TXT file calculating. Check selected file, actions and try again.", e.toString().substring(e.toString().indexOf(':') + 2));
        }
    }
    
    @Test
    public void TestWrite() {
        try {
            TXTFileReader reader1 = new TXTFileReader("./src/main/tests/com/global_task/input.txt");
            ArrayList<ArrayList<String>> readFile1 = reader1.Read();
            reader1.Write(readFile1, "./src/main/result/com/global_task/test.txt");
            TXTFileReader reader2 = new TXTFileReader("./src/main/result/com/global_task/test.txt");
            ArrayList<ArrayList<String>> readFile2 = reader2.Read();
            Assert.assertEquals(readFile1.toString(), readFile2.toString());
            Server.deleteFile("./src/main/result/com/global_task/test.txt");
            Assert.assertEquals("File ./src/main/result/com/global_task/test.txt deleted successfully" + System.lineSeparator(), output.toString());
        } catch(Exception e) {
            Assert.assertEquals("Error in TXT file calculating. Check selected file, actions and try again.", e.toString().substring(e.toString().indexOf(':') + 2));
        }
    }
    
    @Test
    public void TestWriteException() {
        try {
            TXTFileReader reader1 = new TXTFileReader("./src/main/tests/com/global_task/temp.txt");
            ArrayList<ArrayList<String>> readFile1 = reader1.Read();
            reader1.Write(readFile1, "./src/main/result/com/global_task/test.txt");
            TXTFileReader reader2 = new TXTFileReader("./src/main/result/com/global_task/test.txt");
            ArrayList<ArrayList<String>> readFile2 = reader2.Read();
            Assert.assertEquals(readFile1.toString(), readFile2.toString());
            Server.deleteFile("./src/main/result/com/global_task/test.txt");
            Assert.assertEquals("File ./src/main/result/com/global_task/test.txt deleted successfully" + System.lineSeparator(), output.toString());
        } catch(Exception e) {
            Assert.assertEquals("Error in TXT file read. Check selected file, actions and try again.", e.toString().substring(e.toString().indexOf(':') + 2));
        }
    }
    
    @Test
    public void TestWriteResult() {
        try {
            TXTFileReader reader1 = new TXTFileReader("./src/main/tests/com/global_task/input.txt");
            ArrayList<ArrayList<String>> readFile1 = reader1.ReadResult();
            reader1.WriteResult(readFile1, "./src/main/result/com/global_task/test.txt");
            TXTFileReader reader2 = new TXTFileReader("./src/main/result/com/global_task/test.txt");
            ArrayList<ArrayList<String>> readFile2 = reader2.ReadResult();
            Assert.assertEquals("[[Result1 = 1234 / 500 + 543], [Result2 = 100 / 0], [Result3 = 4 - 1]]", readFile2.toString());
            Server.deleteFile("./src/main/result/com/global_task/test.txt");
            Assert.assertEquals("File ./src/main/result/com/global_task/test.txt deleted successfully" + System.lineSeparator(), output.toString());
        } catch(Exception e) {
            Assert.assertEquals("Error in TXT file calculating. Check selected file, actions and try again.", e.toString().substring(e.toString().indexOf(':') + 2));
        }
    }
    
    @Test
    public void TestWriteResultException() {
        try {
            TXTFileReader reader1 = new TXTFileReader("./src/main/tests/com/global_task/temp.txt");
            ArrayList<ArrayList<String>> readFile1 = reader1.ReadResult();
            reader1.WriteResult(readFile1, "./src/main/result/com/global_task/test.txt");
            TXTFileReader reader2 = new TXTFileReader("./src/main/result/com/global_task/test.txt");
            ArrayList<ArrayList<String>> readFile2 = reader2.ReadResult();
            Assert.assertEquals("[[Result1 = 1234 / 500 + 543], [Result2 = 100 / 0], [Result3 = 4 - 1]]", readFile2.toString());
            Server.deleteFile("./src/main/result/com/global_task/test.txt");
            Assert.assertEquals("File ./src/main/result/com/global_task/test.txt deleted successfully" + System.lineSeparator(), output.toString());
        } catch(Exception e) {
            Assert.assertEquals("Error in TXT file calculating. Check selected file, actions and try again.", e.toString().substring(e.toString().indexOf(':') + 2));
        }
    }
    
    @Test
    public void TestCalculate() {
        try {
            TXTFileReader reader = new TXTFileReader("./src/main/tests/com/global_task/input.txt");
            ArrayList<ArrayList<String>> readFile = reader.Calculate(reader.ReadResult());
            Assert.assertEquals("[[545.468], [Error: devision by zero], [3.0]]", readFile.toString());
        } catch(Exception e) {
            Assert.assertEquals("Error in TXT file calculating. Check selected file, actions and try again.", e.toString().substring(e.toString().indexOf(':') + 2));
        }
    }
    
    @Test
    public void TestCalculateException() {
        try {
            TXTFileReader reader = new TXTFileReader("./src/main/tests/com/global_task/temp.txt");
            ArrayList<ArrayList<String>> readFile = reader.Calculate(reader.ReadResult());
            Assert.assertEquals("[[545.468], [Error: devision by zero], [3.0]]", readFile.toString());
        } catch(Exception e) {
            Assert.assertEquals("Error in TXT file calculating. Check selected file, actions and try again.", e.toString().substring(e.toString().indexOf(':') + 2));
        }
    }
    
    @Test
    public void TestGetResult() {
        try {
            TXTFileReader reader = new TXTFileReader("./src/main/tests/com/global_task/input.txt");
            reader.getResult("./src/main/result/com/global_task/output.txt");
            reader = new TXTFileReader("./src/main/result/com/global_task/output.txt");
            Assert.assertEquals("[[Result1 = 545.468], [Result2 = Error: devision by zero], [Result3 = 3.0]]", reader.Read().toString());
        } catch(Exception e) {
            Assert.assertEquals("Error in TXT file calculating. Check selected file, actions and try again.", e.toString().substring(e.toString().indexOf(':') + 2));
        }
    }
    
    @Test
    public void TestGetResultException() {
        try {
            TXTFileReader reader = new TXTFileReader("./src/main/tests/com/global_task/temp.txt");
            reader.getResult("./src/main/result/com/global_task/output.txt");
            reader = new TXTFileReader("./src/main/result/com/global_task/output.txt");
            Assert.assertEquals("[[Result1 = 545.468], [Result2 = Error: devision by zero], [Result3 = 3.0]]", reader.Read().toString());
        } catch(Exception e) {
            Assert.assertEquals("Error in TXT file calculating. Check selected file, actions and try again.", e.toString().substring(e.toString().indexOf(':') + 2));
        }
    }
}