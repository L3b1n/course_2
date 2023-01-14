package com.global_task.FileReadersTests;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.global_task.Server;
import com.global_task.FileReaders.XMLFileReader;

public class XMLFileTester {
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    @Test
    public void TestRead() {
        try {
            XMLFileReader reader = new XMLFileReader("./src/main/tests/com/global_task/input.xml");
            ArrayList<ArrayList<String>> readFile = reader.Read();
            Assert.assertEquals("[[<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>], [<ExpressionList>], [    <expressions>], [        <expression1>((12 - 10) + 124 / 567) + 1 - 2</expression1>], [        <expression2>12 - 345</expression2>], [        <expression3>12</expression3>], [    </expressions>], [    <expressions>], [        <expression1>5 - 34</expression1>], [        <expression2>100 / 0</expression2>], [        <expression3>14 + 9</expression3>], [    </expressions>], [    <expressions>], [        <expression1>((2 - 34) * 100) / 150</expression1>], [        <expression2>12 - 3343</expression2>], [        <expression3>12</expression3>], [    </expressions>], [    <expressions>], [        <expression1>12 - 1</expression1>], [        <expression2>12 - 546</expression2>], [        <expression3>12</expression3>], [    </expressions>], [    <expressions>], [        <expression1>12 - 3</expression1>], [        <expression2>23 - 345</expression2>], [        <expression3>12</expression3>], [    </expressions>], [</ExpressionList>]]", readFile.toString());
        } catch(Exception e) {
            Assert.assertEquals("Error in XML file reading. Check selected file, actions and try again.", e.toString().substring(e.toString().indexOf(':') + 2));
        }
    }
    
    @Test
    public void TestReadException() {
        try {
            XMLFileReader reader = new XMLFileReader("./src/main/tests/com/global_task/temp.xml");
            ArrayList<ArrayList<String>> readFile = reader.Read();
            Assert.assertEquals("[[<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>], [<ExpressionList>], [    <expressions>], [        <expression1>((12 - 10) + 124 / 567) + 1 - 2</expression1>], [        <expression2>12 - 345</expression2>], [        <expression3>12</expression3>], [    </expressions>], [    <expressions>], [        <expression1>5 - 34</expression1>], [        <expression2>100 / 0</expression2>], [        <expression3>14 + 9</expression3>], [    </expressions>], [    <expressions>], [        <expression1>((2 - 34) * 100) / 150</expression1>], [        <expression2>12 - 3343</expression2>], [        <expression3>12</expression3>], [    </expressions>], [    <expressions>], [        <expression1>12 - 1</expression1>], [        <expression2>12 - 546</expression2>], [        <expression3>12</expression3>], [    </expressions>], [    <expressions>], [        <expression1>12 - 3</expression1>], [        <expression2>23 - 345</expression2>], [        <expression3>12</expression3>], [    </expressions>], [</ExpressionList>]]", readFile.toString());
        } catch(Exception e) {
            Assert.assertEquals("Error in XML file reading. Check selected file, actions and try again.", e.toString().substring(e.toString().indexOf(':') + 2));
        }
    }
    
    @Test
    public void TestReadResult() {
        try {
            XMLFileReader reader = new XMLFileReader("./src/main/tests/com/global_task/input.xml");
            ArrayList<ArrayList<String>> readFile = reader.ReadResult();
            Assert.assertEquals("[[((12 - 10) + 124 / 567) + 1 - 2, 12 - 345, 12], [5 - 34, 100 / 0, 14 + 9], [((2 - 34) * 100) / 150, 12 - 3343, 12], [12 - 1, 12 - 546, 12], [12 - 3, 23 - 345, 12]]", readFile.toString());
        } catch(Exception e) {
            Assert.assertEquals("Error in XML file calculating. Check selected file, actions and try again.", e.toString().substring(e.toString().indexOf(':') + 2));
        }
    }
    
    @Test
    public void TestReadResultException() {
        try {
            XMLFileReader reader = new XMLFileReader("./src/main/tests/com/global_task/temp.xml");
            ArrayList<ArrayList<String>> readFile = reader.ReadResult();
            Assert.assertEquals("[[((12 - 10) + 124 / 567) + 1 - 2, 12 - 345, 12], [5 - 34, 100 / 0, 14 + 9], [((2 - 34) * 100) / 150, 12 - 3343, 12], [12 - 1, 12 - 546, 12], [12 - 3, 23 - 345, 12]]", readFile.toString());
        } catch(Exception e) {
            Assert.assertEquals("Error in XML file calculating. Check selected file, actions and try again.", e.toString().substring(e.toString().indexOf(':') + 2));
        }
    }
    
    @Test
    public void TestWrite() {
        try {
            XMLFileReader reader1 = new XMLFileReader("./src/main/tests/com/global_task/input.xml");
            ArrayList<ArrayList<String>> readFile1 = reader1.Read();
            reader1.Write(readFile1, "./src/main/result/com/global_task/test.xml");
            XMLFileReader reader2 = new XMLFileReader("./src/main/result/com/global_task/test.xml");
            ArrayList<ArrayList<String>> readFile2 = reader2.Read();
            Assert.assertEquals(readFile1.toString(), readFile2.toString());
            Server.deleteFile("./src/main/result/com/global_task/test.xml");
            Assert.assertEquals("File ./src/main/result/com/global_task/test.xml deleted successfully" + System.lineSeparator(), output.toString());
        } catch(Exception e) {
            Assert.assertEquals("Error in XML file calculating. Check selected file, actions and try again.", e.toString().substring(e.toString().indexOf(':') + 2));
        }
    }
    
    @Test
    public void TestWriteException() {
        try {
            XMLFileReader reader1 = new XMLFileReader("./src/main/tests/com/global_task/temp.xml");
            ArrayList<ArrayList<String>> readFile1 = reader1.Read();
            reader1.Write(readFile1, "./src/main/result/com/global_task/test.xml");
            XMLFileReader reader2 = new XMLFileReader("./src/main/result/com/global_task/test.xml");
            ArrayList<ArrayList<String>> readFile2 = reader2.Read();
            Assert.assertEquals(readFile1.toString(), readFile2.toString());
            Server.deleteFile("./src/main/result/com/global_task/test.xml");
            Assert.assertEquals("File ./src/main/result/com/global_task/test.xml deleted successfully" + System.lineSeparator(), output.toString());
        } catch(Exception e) {
            Assert.assertEquals("Error in XML file reading. Check selected file, actions and try again.", e.toString().substring(e.toString().indexOf(':') + 2));
        }
    }
    
    @Test
    public void TestWriteResult() {
        try {
            XMLFileReader reader1 = new XMLFileReader("./src/main/tests/com/global_task/input.xml");
            ArrayList<ArrayList<String>> readFile1 = reader1.ReadResult();
            reader1.WriteResult(readFile1, "./src/main/result/com/global_task/test.xml");
            XMLFileReader reader2 = new XMLFileReader("./src/main/result/com/global_task/test.xml");
            ArrayList<ArrayList<String>> readFile2 = reader2.ReadResult();
            Assert.assertEquals("[[((12 - 10) + 124 / 567) + 1 - 2, 12 - 345, 12], [5 - 34, 100 / 0, 14 + 9], [((2 - 34) * 100) / 150, 12 - 3343, 12], [12 - 1, 12 - 546, 12]]", readFile2.toString());
            Server.deleteFile("./src/main/result/com/global_task/test.xml");
            Assert.assertEquals("File ./src/main/result/com/global_task/test.xml deleted successfully" + System.lineSeparator(), output.toString());
        } catch(Exception e) {
            Assert.assertEquals("Error in XML file calculating. Check selected file, actions and try again.", e.toString().substring(e.toString().indexOf(':') + 2));
        }
    }
    
    @Test
    public void TestWriteResultException() {
        try {
            XMLFileReader reader1 = new XMLFileReader("./src/main/tests/com/global_task/temp.xml");
            ArrayList<ArrayList<String>> readFile1 = reader1.ReadResult();
            reader1.WriteResult(readFile1, "./src/main/result/com/global_task/test.xml");
            XMLFileReader reader2 = new XMLFileReader("./src/main/result/com/global_task/test.xml");
            ArrayList<ArrayList<String>> readFile2 = reader2.ReadResult();
            Assert.assertEquals("[[((12 - 10) + 124 / 567) + 1 - 2, 12 - 345, 12], [5 - 34, 100 / 0, 14 + 9], [((2 - 34) * 100) / 150, 12 - 3343, 12], [12 - 1, 12 - 546, 12]]", readFile2.toString());
            Server.deleteFile("./src/main/result/com/global_task/test.xml");
            Assert.assertEquals("File ./src/main/result/com/global_task/test.xml deleted successfully" + System.lineSeparator(), output.toString());
        } catch(Exception e) {
            Assert.assertEquals("Error in XML file calculating. Check selected file, actions and try again.", e.toString().substring(e.toString().indexOf(':') + 2));
        }
    }
    
    @Test
    public void TestCalculate() {
        try {
            XMLFileReader reader = new XMLFileReader("./src/main/tests/com/global_task/input.xml");
            ArrayList<ArrayList<String>> readFile = reader.Calculate(reader.ReadResult());
            Assert.assertEquals("[[1.2186948853615522, -333.0, 12.0], [-29.0, Error: devision by zero, 23.0], [-21.333333333333332, -3331.0, 12.0], [11.0, -534.0, 12.0], [9.0, -322.0, 12.0]]", readFile.toString());
        } catch(Exception e) {
            Assert.assertEquals("Error in XML file calculating. Check selected file, actions and try again.", e.toString().substring(e.toString().indexOf(':') + 2));
        }
    }
    
    @Test
    public void TestCalculateException() {
        try {
            XMLFileReader reader = new XMLFileReader("./src/main/tests/com/global_task/temp.xml");
            ArrayList<ArrayList<String>> readFile = reader.Calculate(reader.ReadResult());
            Assert.assertEquals("[[1.2186948853615522, -333.0, 12.0], [-29.0, Error: devision by zero, 23.0], [-21.333333333333332, -3331.0, 12.0], [11.0, -534.0, 12.0], [9.0, -322.0, 12.0]]", readFile.toString());
        } catch(Exception e) {
            Assert.assertEquals("Error in XML file calculating. Check selected file, actions and try again.", e.toString().substring(e.toString().indexOf(':') + 2));
        }
    }
    
    @Test
    public void TestGetResult() {
        try {
            XMLFileReader reader = new XMLFileReader("./src/main/tests/com/global_task/input.xml");
            reader.getResult("./src/main/result/com/global_task/output.xml");
            reader = new XMLFileReader("./src/main/result/com/global_task/output.xml");
            Assert.assertEquals("[[<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>], [<ExpressionList>], [    <expressions>], [        <Result1>1.2186948853615522</Result1>], [        <Result2>-333.0</Result2>], [        <Result3>12.0</Result3>], [    </expressions>], [    <expressions>], [        <Result1>-29.0</Result1>], [        <Result2>Error: devision by zero</Result2>], [        <Result3>23.0</Result3>], [    </expressions>], [    <expressions>], [        <Result1>-21.333333333333332</Result1>], [        <Result2>-3331.0</Result2>], [        <Result3>12.0</Result3>], [    </expressions>], [    <expressions>], [        <Result1>11.0</Result1>], [        <Result2>-534.0</Result2>], [        <Result3>12.0</Result3>], [    </expressions>], [</ExpressionList>]]", reader.Read().toString());
        } catch(Exception e) {
            Assert.assertEquals("Error in XML file calculating. Check selected file, actions and try again.", e.toString().substring(e.toString().indexOf(':') + 2));
        }
    }
    
    @Test
    public void TestGetResultException() {
        try {
            XMLFileReader reader = new XMLFileReader("./src/main/tests/com/global_task/temp.xml");
            reader.getResult("./src/main/result/com/global_task/output.xml");
            reader = new XMLFileReader("./src/main/result/com/global_task/output.xml");
            Assert.assertEquals("[[<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>], [<ExpressionList>], [    <expressions>], [        <Result1>1.2186948853615522</Result1>], [        <Result2>-333.0</Result2>], [        <Result3>12.0</Result3>], [    </expressions>], [    <expressions>], [        <Result1>-29.0</Result1>], [        <Result2>Error: devision by zero</Result2>], [        <Result3>23.0</Result3>], [    </expressions>], [    <expressions>], [        <Result1>-21.333333333333332</Result1>], [        <Result2>-3331.0</Result2>], [        <Result3>12.0</Result3>], [    </expressions>], [    <expressions>], [        <Result1>11.0</Result1>], [        <Result2>-534.0</Result2>], [        <Result3>12.0</Result3>], [    </expressions>], [</ExpressionList>]]", reader.Read().toString());
        } catch(Exception e) {
            Assert.assertEquals("Error in XML file calculating. Check selected file, actions and try again.", e.toString().substring(e.toString().indexOf(':') + 2));
        }
    }
}