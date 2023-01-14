package com.global_task.FileReadersTests;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.global_task.Server;
import com.global_task.FileReaders.JSONFileReader;

public class JSONFileTester {
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    @Test
    public void TestRead() {
        try {
            JSONFileReader reader = new JSONFileReader("./src/main/tests/com/global_task/input.json");
            ArrayList<ArrayList<String>> readFile = reader.Read();
            Assert.assertEquals("[[[], [    {], [        \"expression1\": \"((12 - 10) + 124 / 567) + 1 - 2\",], [        \"expression2\": \"1234 / 500 + 543\",], [        \"expression3\": \"((3 + 3 + 3 + 3 + 3 + 3) / 3) * 3\"], [    },], [    {], [        \"expression1\": \"100 / 0\",], [        \"expression2\": \"((12 - 10) + 124 / 0) + 1 - 2\",], [        \"expression3\": \"4 - 1\"], [    },], [    {], [        \"expression1\": \"5 / 2\",], [        \"expression2\": \"3 / 0\",], [        \"expression3\": \"4 / 2\"], [    },], [    {], [        \"expression1\": \"12 * 2\",], [        \"expression2\": \"2 * 2\",], [        \"expression3\": \"3 * 2.5\"], [    },], [    {], [        \"expression1\": \"(1 + 1) / 2\",], [        \"expression2\": \"(1 + 1) / (1 + 1)\",], [        \"expression3\": \"12 / (2 + 2)\"], [    }], []]]", readFile.toString());
        } catch(Exception e) {
            Assert.assertEquals("Error in JSON file read. Check selected file, actions and try again.", e.toString().substring(e.toString().indexOf(':') + 2));
        }
    }
    
    @Test
    public void TestReadException() {
        try {
            JSONFileReader reader = new JSONFileReader("./src/main/tests/com/global_task/temp.json");
            ArrayList<ArrayList<String>> readFile = reader.Read();
            Assert.assertEquals("[[[], [    {], [        \"expression1\": \"((12 - 10) + 124 / 567) + 1 - 2\",], [        \"expression2\": \"1234 / 500 + 543\",], [        \"expression3\": \"((3 + 3 + 3 + 3 + 3 + 3) / 3) * 3\"], [    },], [    {], [        \"expression1\": \"100 / 0\",], [        \"expression2\": \"((12 - 10) + 124 / 0) + 1 - 2\",], [        \"expression3\": \"4 - 1\"], [    },], [    {], [        \"expression1\": \"5 / 2\",], [        \"expression2\": \"3 / 0\",], [        \"expression3\": \"4 / 2\"], [    },], [    {], [        \"expression1\": \"12 * 2\",], [        \"expression2\": \"2 * 2\",], [        \"expression3\": \"3 * 2.5\"], [    },], [    {], [        \"expression1\": \"(1 + 1) / 2\",], [        \"expression2\": \"(1 + 1) / (1 + 1)\",], [        \"expression3\": \"12 / (2 + 2)\"], [    }], []]]", readFile.toString());
        } catch(Exception e) {
            Assert.assertEquals("Error in JSON file reading. Check selected file, actions and try again.", e.toString().substring(e.toString().indexOf(':') + 2));
        }
    }
    
    @Test
    public void TestReadResult() {
        try {
            JSONFileReader reader = new JSONFileReader("./src/main/tests/com/global_task/input.json");
            ArrayList<ArrayList<String>> readFile = reader.ReadResult();
            Assert.assertEquals("[[((12 - 10) + 124 / 567) + 1 - 2, 1234 / 500 + 543, ((3 + 3 + 3 + 3 + 3 + 3) / 3) * 3], [100 / 0, ((12 - 10) + 124 / 0) + 1 - 2, 4 - 1], [5 / 2, 3 / 0, 4 / 2], [12 * 2, 2 * 2, 3 * 2.5], [(1 + 1) / 2, (1 + 1) / (1 + 1), 12 / (2 + 2)]]", readFile.toString());
        } catch(Exception e) {
            Assert.assertEquals("Error in JSON file calculating. Check selected file, actions and try again.", e.toString().substring(e.toString().indexOf(':') + 2));
        }
    }
    
    @Test
    public void TestReadResultException() {
        try {
            JSONFileReader reader = new JSONFileReader("./src/main/tests/com/global_task/temp.json");
            ArrayList<ArrayList<String>> readFile = reader.ReadResult();
            Assert.assertEquals("[[((12 - 10) + 124 / 567) + 1 - 2, 1234 / 500 + 543, ((3 + 3 + 3 + 3 + 3 + 3) / 3) * 3], [100 / 0, ((12 - 10) + 124 / 0) + 1 - 2, 4 - 1], [5 / 2, 3 / 0, 4 / 2], [12 * 2, 2 * 2, 3 * 2.5], [(1 + 1) / 2, (1 + 1) / (1 + 1), 12 / (2 + 2)]]", readFile.toString());
        } catch(Exception e) {
            Assert.assertEquals("Error in JSON file calculating. Check selected file, actions and try again.", e.toString().substring(e.toString().indexOf(':') + 2));
        }
    }
    
    @Test
    public void TestWrite() {
        try {
            JSONFileReader reader1 = new JSONFileReader("./src/main/tests/com/global_task/input.json");
            ArrayList<ArrayList<String>> readFile1 = reader1.Read();
            reader1.Write(readFile1, "./src/main/result/com/global_task/test.json");
            JSONFileReader reader2 = new JSONFileReader("./src/main/result/com/global_task/test.json");
            ArrayList<ArrayList<String>> readFile2 = reader2.Read();
            Assert.assertEquals(readFile1.toString(), readFile2.toString());
            Server.deleteFile("./src/main/result/com/global_task/test.json");
            Assert.assertEquals("File ./src/main/result/com/global_task/test.json deleted successfully" + System.lineSeparator(), output.toString());
        } catch(Exception e) {
            Assert.assertEquals("Error in JSON file calculating. Check selected file, actions and try again.", e.toString().substring(e.toString().indexOf(':') + 2));
        }
    }
    
    @Test
    public void TestWriteException() {
        try {
            JSONFileReader reader1 = new JSONFileReader("./src/main/tests/com/global_task/temp.json");
            ArrayList<ArrayList<String>> readFile1 = reader1.Read();
            reader1.Write(readFile1, "./src/main/result/com/global_task/test.json");
            JSONFileReader reader2 = new JSONFileReader("./src/main/result/com/global_task/test.json");
            ArrayList<ArrayList<String>> readFile2 = reader2.Read();
            Assert.assertEquals(readFile1.toString(), readFile2.toString());
            Server.deleteFile("./src/main/result/com/global_task/test.json");
            Assert.assertEquals("File ./src/main/result/com/global_task/test.json deleted successfully" + System.lineSeparator(), output.toString());
        } catch(Exception e) {
            Assert.assertEquals("Error in JSON file reading. Check selected file, actions and try again.", e.toString().substring(e.toString().indexOf(':') + 2));
        }
    }
    
    @Test
    public void TestWriteResult() {
        try {
            JSONFileReader reader1 = new JSONFileReader("./src/main/tests/com/global_task/input.json");
            ArrayList<ArrayList<String>> readFile1 = reader1.ReadResult();
            reader1.WriteResult(readFile1, "./src/main/result/com/global_task/test.json");
            JSONFileReader reader2 = new JSONFileReader("./src/main/result/com/global_task/test.json");
            ArrayList<ArrayList<String>> readFile2 = reader2.Read();
            Assert.assertEquals("[[[], [	{\"Result_1\":\"((12 - 10) + 124 \\/ 567) + 1 - 2\",\"Result_2\":\"1234 \\/ 500 + 543\",\"Result_3\":\"((3 + 3 + 3 + 3 + 3 + 3) \\/ 3) * 3\"},], [	{\"Result_1\":\"100 \\/ 0\",\"Result_2\":\"((12 - 10) + 124 \\/ 0) + 1 - 2\",\"Result_3\":\"4 - 1\"},], [	{\"Result_1\":\"5 \\/ 2\",\"Result_2\":\"3 \\/ 0\",\"Result_3\":\"4 \\/ 2\"},], [	{\"Result_1\":\"12 * 2\",\"Result_2\":\"2 * 2\",\"Result_3\":\"3 * 2.5\"},], [	{\"Result_1\":\"(1 + 1) \\/ 2\",\"Result_2\":\"(1 + 1) \\/ (1 + 1)\",\"Result_3\":\"12 \\/ (2 + 2)\"}], []]]", readFile2.toString());
            Server.deleteFile("./src/main/result/com/global_task/test.json");
            Assert.assertEquals("File ./src/main/result/com/global_task/test.json deleted successfully" + System.lineSeparator(), output.toString());
        } catch(Exception e) {
            Assert.assertEquals("Error in JSON file calculating. Check selected file, actions and try again.", e.toString().substring(e.toString().indexOf(':') + 2));
        }
    }
    
    @Test
    public void TestWriteResultException() {
        try {
            JSONFileReader reader1 = new JSONFileReader("./src/main/tests/com/global_task/temp.json");
            ArrayList<ArrayList<String>> readFile1 = reader1.ReadResult();
            reader1.WriteResult(readFile1, "./src/main/result/com/global_task/test.json");
            JSONFileReader reader2 = new JSONFileReader("./src/main/result/com/global_task/test.json");
            ArrayList<ArrayList<String>> readFile2 = reader2.ReadResult();
            Assert.assertEquals("[[[], [	{\"Result_1\":\"((12 - 10) + 124 \\/ 567) + 1 - 2\",\"Result_2\":\"1234 \\/ 500 + 543\",\"Result_3\":\"((3 + 3 + 3 + 3 + 3 + 3) \\/ 3) * 3\"},], [	{\"Result_1\":\"100 \\/ 0\",\"Result_2\":\"((12 - 10) + 124 \\/ 0) + 1 - 2\",\"Result_3\":\"4 - 1\"},], [	{\"Result_1\":\"5 \\/ 2\",\"Result_2\":\"3 \\/ 0\",\"Result_3\":\"4 \\/ 2\"},], [	{\"Result_1\":\"12 * 2\",\"Result_2\":\"2 * 2\",\"Result_3\":\"3 * 2.5\"},], [	{\"Result_1\":\"(1 + 1) \\/ 2\",\"Result_2\":\"(1 + 1) \\/ (1 + 1)\",\"Result_3\":\"12 \\/ (2 + 2)\"}], []]]", readFile2.toString());
            Server.deleteFile("./src/main/result/com/global_task/test.json");
            Assert.assertEquals("File ./src/main/result/com/global_task/test.json deleted successfully" + System.lineSeparator(), output.toString());
        } catch(Exception e) {
            Assert.assertEquals("Error in JSON file calculating. Check selected file, actions and try again.", e.toString().substring(e.toString().indexOf(':') + 2));
        }
    }
    
    @Test
    public void TestCalculate() {
        try {
            JSONFileReader reader = new JSONFileReader("./src/main/tests/com/global_task/input.json");
            ArrayList<ArrayList<String>> readFile = reader.Calculate(reader.ReadResult());
            Assert.assertEquals("[[1.2186948853615522, 545.468, 18.0], [Error: devision by zero, Error: devision by zero, 3.0], [2.5, Error: devision by zero, 2.0], [24.0, 4.0, 7.5], [1.0, 1.0, 3.0]]", readFile.toString());
        } catch(Exception e) {
            Assert.assertEquals("Error in JSON file calculating. Check selected file, actions and try again.", e.toString().substring(e.toString().indexOf(':') + 2));
        }
    }
    
    @Test
    public void TestCalculateException() {
        try {
            JSONFileReader reader = new JSONFileReader("./src/main/tests/com/global_task/temp.json");
            ArrayList<ArrayList<String>> readFile = reader.Calculate(reader.ReadResult());
            Assert.assertEquals("[[1.2186948853615522, 545.468, 18.0], [Error: devision by zero, Error: devision by zero, 3.0], [2.5, Error: devision by zero, 2.0], [24.0, 4.0, 7.5], [1.0, 1.0, 3.0]]", readFile.toString());
        } catch(Exception e) {
            Assert.assertEquals("Error in JSON file calculating. Check selected file, actions and try again.", e.toString().substring(e.toString().indexOf(':') + 2));
        }
    }
    
    @Test
    public void TestGetResult() {
        try {
            JSONFileReader reader = new JSONFileReader("./src/main/tests/com/global_task/input.json");
            reader.getResult("./src/main/result/com/global_task/output.json");
            reader = new JSONFileReader("./src/main/result/com/global_task/output.json");
            Assert.assertEquals("[[[], [	{\"Result_1\":\"1.2186948853615522\",\"Result_2\":\"545.468\",\"Result_3\":\"18.0\"},], [	{\"Result_1\":\"Error: devision by zero\",\"Result_2\":\"Error: devision by zero\",\"Result_3\":\"3.0\"},], [	{\"Result_1\":\"2.5\",\"Result_2\":\"Error: devision by zero\",\"Result_3\":\"2.0\"},], [	{\"Result_1\":\"24.0\",\"Result_2\":\"4.0\",\"Result_3\":\"7.5\"},], [	{\"Result_1\":\"1.0\",\"Result_2\":\"1.0\",\"Result_3\":\"3.0\"}], []]]", reader.Read().toString());
        } catch(Exception e) {
            Assert.assertEquals("Error in JSON file calculating. Check selected file, actions and try again.", e.toString().substring(e.toString().indexOf(':') + 2));
        }
    }
    
    @Test
    public void TestGetResultException() {
        try {
            JSONFileReader reader = new JSONFileReader("./src/main/tests/com/global_task/temp.json");
            reader.getResult("./src/main/result/com/global_task/output.json");
            reader = new JSONFileReader("./src/main/result/com/global_task/output.json");
            Assert.assertEquals("[[[], [	{\"Result_1\":\"1.2186948853615522\",\"Result_2\":\"545.468\",\"Result_3\":\"18.0\"},], [	{\"Result_1\":\"Error: devision by zero\",\"Result_2\":\"Error: devision by zero\",\"Result_3\":\"3.0\"},], [	{\"Result_1\":\"2.5\",\"Result_2\":\"Error: devision by zero\",\"Result_3\":\"2.0\"},], [	{\"Result_1\":\"24.0\",\"Result_2\":\"4.0\",\"Result_3\":\"7.5\"},], [	{\"Result_1\":\"1.0\",\"Result_2\":\"1.0\",\"Result_3\":\"3.0\"}], []]]", reader.Read().toString());
        } catch(Exception e) {
            Assert.assertEquals("Error in JSON file calculating. Check selected file, actions and try again.", e.toString().substring(e.toString().indexOf(':') + 2));
        }
    }
}