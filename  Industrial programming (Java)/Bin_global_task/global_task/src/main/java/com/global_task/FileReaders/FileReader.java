package com.global_task.FileReaders;

import java.util.ArrayList;

import com.global_task.contracts.Interface.InterfaceFileReader;

public abstract class FileReader implements InterfaceFileReader{
    protected String inputName;
    protected InterfaceFileReader reader;
    public FileReader(InterfaceFileReader reader) {
        this.reader = reader;
        inputName = reader.getFileName();
    }

    @Override
    public void WriteResult(ArrayList<ArrayList<String>> result, String outputFileName) throws Throwable {}

    @Override
    public String getFileName() {
        return this.inputName;
    }
}
