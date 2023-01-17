package com.global_task.FileReadersTests;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.global_task.FileReaders.FileBuilder;
import com.global_task.contracts.Interface.InterfaceFileReader;

public class CompressFileTester {
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }
    
    @Test
    public void TestGetResult() {
        try {
            FileBuilder builder = new FileBuilder("txt", "./src/main/tests/com/global_task/compressed.txt");
            builder.setCompressed(true);
            InterfaceFileReader reader = builder.getFileReader();
            reader.getResult("./src/main/results/com/global_task/test.txt");
            builder = new FileBuilder("txt", "./src/main/results/com/global_task/test.txt");
            reader  = builder.getFileReader();
            Assert.assertEquals("[[Result1 = 545.468], [Result2 = Error: devision by zero], [Result3 = 3.0]]", reader.Read().toString());
        } catch(Exception e) {
            Assert.assertEquals("Error in TXT file calculating. Check selected file, actions and try again.", e.toString().substring(e.toString().indexOf(':') + 2));
        }
    }
    
    @Test
    public void TestGetResultException() {
        try {
            FileBuilder builder = new FileBuilder("txt", "./src/main/results/com/global_task/encrypted.txt");
            InterfaceFileReader reader = builder.getFileReader();
            Assert.assertEquals("[[Result1 = 545.468], [Result2 = Error: devision by zero], [Result3 = 3.0]]", reader.Read().toString());
        } catch(Exception e) {
            Assert.assertEquals("Error in TXT file read. Check selected file, actions and try again.", e.toString().substring(e.toString().indexOf(':') + 2));
        }
    }
}
